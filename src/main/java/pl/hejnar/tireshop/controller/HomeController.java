package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.service.RedirectToPageService;
import pl.hejnar.tireshop.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserServiceImpl userService;
    private final RedirectToPageService redirectToPageService;

    public HomeController(UserServiceImpl userService, RedirectToPageService redirectToPageService) {
        this.userService = userService;
        this.redirectToPageService = redirectToPageService;
    }

    @ModelAttribute("user")
    public User newUser (){
        return new User();
    }

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @PostMapping("/")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redAttr, HttpSession ses){
        boolean check = userService.checkUser(user, redAttr);

        if(result.hasErrors()){
            redAttr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redAttr.addFlashAttribute("user", user);
            redAttr.addFlashAttribute("someChange", true);
            return redirectToPageService.goToPage(ses);
        }

        if(!check) {
            userService.save(user, redAttr);
        }

        return redirectToPageService.goToPage(ses);
    }

    @GetMapping("/loginError")
    public String login(RedirectAttributes redirectAttributes, HttpSession ses){
        redirectAttributes.addFlashAttribute("loginError", true);
        return redirectToPageService.goToPage(ses);
    }
}
