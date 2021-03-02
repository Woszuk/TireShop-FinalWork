package pl.hejnar.tireshop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.OrderRepository;
import pl.hejnar.tireshop.repository.UserRepository;
import pl.hejnar.tireshop.service.AccountService;
import pl.hejnar.tireshop.service.FeaturesService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final FeaturesService featuresService;
    private final PasswordEncoder encoder;
    private final OrderRepository orderRepository;


    public AccountController(UserRepository userRepository, AccountService accountService, FeaturesService featuresService, PasswordEncoder encoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.featuresService = featuresService;
        this.encoder = encoder;
        this.orderRepository = orderRepository;
    }

    @ModelAttribute("loggedUser")
    public User loggedUser(Principal principal){
        User user = new User();
        if(principal.getName() != null){
            user = userRepository.findByUsername(principal.getName());
        }
        return user;
    }

    @GetMapping("/details")
    public String accountDetails(HttpSession ses, Principal principal) {
        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }
        return "account";
    }

    @GetMapping("/change/details")
    public String changeDetails(HttpSession ses, Principal principal ) {
        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }
        return "change-account-details";
    }

    @PostMapping("/change/details")
    public String changeDetails(@ModelAttribute("loggedUser") @Valid User changeUser, BindingResult result, Model model, Principal principal) {
        boolean check = accountService.checkData(model, changeUser, principal);
        if (result.hasErrors()) {
            return "change-account-details";
        }

        if (!check) {
            accountService.changeDetailsForUser(changeUser);
            userRepository.save(changeUser);
            return "redirect:/account/details";
        }

        return "change-account-details";
    }

    @GetMapping("/change/address")
    public String changeAddress(Model model, HttpSession ses, Principal principal){
        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }
        Address address = new Address();
        if(principal.getName() != null){
            User user = userRepository.findByUsername(principal.getName());
            if(user.getAddress() != null){
                address = userRepository.findByUsername(principal.getName()).getAddress();
            }
        }
        model.addAttribute("address", address);
        return "change-account-address";
    }

    @PostMapping("/change/address")
    public String changeAddress(@ModelAttribute("address") @Valid Address address, BindingResult result, Principal principal, @RequestParam("addressId") long addressId){
        if(result.hasErrors()){
            return "change-account-address";
        }
        address.setId(addressId);
        accountService.saveAddress(address, principal);

        return "redirect:/account/details";
    }

    @GetMapping("/change/password")
    public String changePassword(HttpSession ses, Principal principal){
        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }
        return "change-account-password";
    }

    @PostMapping("/change/password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("repeatPassword") String repeatPassword, Principal principal, Model model){
        boolean check = accountService.changePassword(oldPassword, newPassword, repeatPassword, encoder, principal, model);
        if(check){
            if(principal.getName() != null){
                User user = userRepository.findByUsername(principal.getName()).setPassword(encoder.encode(newPassword));
                userRepository.save(user);
                model.addAttribute("success", true);
            }
        }
        return "change-account-password";
    }

    @GetMapping("/orders")
    public String orders(Model model, Principal principal, HttpSession ses) {
        if(ses.getAttribute("userLoggedIn") == null){
            featuresService.saveProductToUser(ses,principal);
        }
        model.addAttribute("orders", orderRepository.findAllByUser(userRepository.findByUsername(principal.getName())));
        return "orders";
    }
}
