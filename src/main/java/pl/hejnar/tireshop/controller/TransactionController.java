package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.*;
import pl.hejnar.tireshop.service.AccountService;
import pl.hejnar.tireshop.service.FeaturesService;
import pl.hejnar.tireshop.service.TransactionService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final BasketItemRepository basketItemRepository;
    private final FeaturesService featuresService;
    private final AccountService accountService;
    private final AddressRepository addressRepository;

    public TransactionController(TransactionService transactionService, UserRepository userRepository, DeliveryRepository deliveryRepository, PaymentMethodRepository paymentMethodRepository, BasketItemRepository basketItemRepository, FeaturesService featuresService, AccountService accountService, AddressRepository addressRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.basketItemRepository = basketItemRepository;
        this.featuresService = featuresService;
        this.accountService = accountService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/transaction")
    public String transaction (HttpSession ses, Model model, Principal principal){
        transactionService.totalPrice(ses);

        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }

        if(ses.getAttribute("basket") == null){
            return "redirect:/basket";
        }

        if(!model.containsAttribute("address")){
            model.addAttribute("address", new Address());
        }

        if(principal.getName() != null){
            User loggedUser = userRepository.findByUsername(principal.getName());
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("basket", ses.getAttribute("basket"));
        }

        model.addAttribute("deliveries", deliveryRepository.findAll());
        model.addAttribute("paymentMethods", paymentMethodRepository.findAll());
        return "transaction";
    }

    @PostMapping("/transaction")
    public String transaction(@ModelAttribute("address") @Valid Address address, BindingResult result, @RequestParam String paymentMethod, @RequestParam String delivery, @RequestParam(required = false) String phoneNumber, RedirectAttributes redAttr, Principal principal, HttpSession ses) {
        if(address.getStreet() != null && result.hasErrors()){
            if(phoneNumber != null && !phoneNumber.matches("(\\+48\\s?)?([0-9]{3}\\s?){2}[0-9]{3}")){
                redAttr.addFlashAttribute("phoneNumberError", phoneNumber);
            }
            redAttr.addFlashAttribute("org.springframework.validation.BindingResult.address", result);
            redAttr.addFlashAttribute("address", address);
            return "redirect:/transaction";
        }

        if(phoneNumber != null && !phoneNumber.matches("(\\+48\\s?)?([0-9]{3}\\s?){2}[0-9]{3}")){
            redAttr.addFlashAttribute("address", address);
            redAttr.addFlashAttribute("phoneNumberError", phoneNumber);
            return "redirect:/transaction";
        }

        if(address.getStreet() != null){
            accountService.saveAddress(address, principal);
        }

        if(phoneNumber != null){
            userRepository.updatePhoneNumber(phoneNumber, userRepository.findByUsername(principal.getName()).getId());
        }

        if(address.getStreet() == null){
            address = addressRepository.getOne(userRepository.findByUsername(principal.getName()).getAddress().getId());
        }

        transactionService.saveOrder(delivery, paymentMethod, principal, ses, address);

        if(ses.getAttribute("errorItem") != null) {
            return "redirect:/basket";
        }
        return "redirect:/account/orders";
    }
}
