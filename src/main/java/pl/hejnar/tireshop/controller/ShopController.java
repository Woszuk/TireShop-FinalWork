package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.ProductRepository;
import pl.hejnar.tireshop.service.FeaturesService;
import pl.hejnar.tireshop.service.ShopServiceImpl;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductRepository productRepository;
    private final ShopServiceImpl shopService;
    private final FeaturesService featuresService;

    public ShopController(ProductRepository productRepository, ShopServiceImpl shopService, FeaturesService featuresService) {
        this.productRepository = productRepository;
        this.shopService = shopService;
        this.featuresService = featuresService;
    }

    @ModelAttribute("user")
    public User newUser (){
        return new User();
    }

    @GetMapping("/tire")
    public String tireShop(Model model, HttpSession ses){
        ses.setAttribute("currentPage", "tire");
        model.addAttribute("tires", productRepository.findAllByType("tire"));
        return "tire";
    }

    @GetMapping("/wheel-rim")
    public String wheelRimShop(Model model, HttpSession ses){
        ses.setAttribute("currentPage", "wheelRim");
        model.addAttribute("wheelRims", productRepository.findAllByType("wheel-rim"));
        return "wheel-rim";
    }

    @GetMapping("/addToBasket")
    public String addToBasket(HttpSession ses){
        return featuresService.goToPage(ses);
    }

    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam int quantityToBuy, @RequestParam Long productId, @RequestParam Double scrollTo, HttpSession ses, RedirectAttributes redAttr, Principal principal){
        redAttr.addFlashAttribute("scrollTo", scrollTo);
        shopService.addToBasket(quantityToBuy, productId, ses, principal);
        return featuresService.goToPage(ses);
    }
}
