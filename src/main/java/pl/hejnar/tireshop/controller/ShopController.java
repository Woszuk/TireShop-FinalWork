package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.ProductRepository;
import pl.hejnar.tireshop.service.RedirectToPageService;
import pl.hejnar.tireshop.service.ShopServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ProductRepository productRepository;
    private RedirectToPageService redirectToPageService;
    private ShopServiceImpl shopService;

    public ShopController(ProductRepository productRepository, RedirectToPageService redirectToPageService, ShopServiceImpl shopService) {
        this.productRepository = productRepository;
        this.redirectToPageService = redirectToPageService;
        this.shopService = shopService;
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
        return redirectToPageService.goToPage(ses);
    }

    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam int quantityToBuy, @RequestParam Long productId, @RequestParam Double scrollTo, HttpSession ses){
        ses.setAttribute("scrollTo", scrollTo);
        shopService.addToBasket(quantityToBuy, productId, ses);
        return redirectToPageService.goToPage(ses);
    }
}
