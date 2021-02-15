package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.service.BasketServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
public class BasketController {

    private final BasketServiceImpl basketService;

    public BasketController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @ModelAttribute("user")
    public User newUser (){
        return new User();
    }

    @GetMapping("/basket")
    public String basket(HttpSession ses, Model model){
        ses.setAttribute("currentPage", "basket");
        if(ses.getAttribute("scrollTo") != null){
            ses.removeAttribute("scrollTo");
        }

        basketService.productInSession(ses,model);
        return "basket";
    }

    @GetMapping("/basket/item/remove")
    public String deleteItem (HttpSession ses, @RequestParam(required = false) Long id, @RequestParam(required = false) Double scrollTo, RedirectAttributes redAttr){
        if(id == null || scrollTo == null){
            return "redirect:/basket";
        }

        redAttr.addFlashAttribute("scrollTo", scrollTo);
        boolean check = basketService.checkIdItem(ses, id);

        if(check){
            basketService.removeProduct(ses,id);
        }

        return "redirect:/basket";
    }

    @GetMapping("/basket/item/quantity")
    public String changeQuantity(HttpSession ses, @RequestParam(required = false) String type, @RequestParam(required = false) Long id, @RequestParam(required = false) Double scrollTo, RedirectAttributes redAttr){
        if(type == null || id == null || scrollTo == null){
            return "redirect:/basket";
        }
        boolean check = basketService.checkIdItem(ses, id);

        if(check){
            basketService.changeQuantity(ses, type, id);
        }

        return "redirect:/basket";
    }

}
