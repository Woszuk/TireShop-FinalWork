package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.service.FeaturesService;
import pl.hejnar.tireshop.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserServiceImpl userService;
    private final FeaturesService featuresService;

    public HomeController(UserServiceImpl userService, FeaturesService featuresService) {
        this.userService = userService;
        this.featuresService = featuresService;
    }

    @ModelAttribute("user")
    public User newUser() {
        return new User();
    }

    @GetMapping("/")
    public String home(Model model, HttpSession ses) {
        ses.setAttribute("currentPage", "home");
        return "home";
    }

    @PostMapping("/")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redAttr, HttpSession ses) {
        boolean check = userService.checkUser(user, redAttr);

        if (result.hasErrors()) {
            redAttr.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redAttr.addFlashAttribute("user", user);
            redAttr.addFlashAttribute("someChange", true);
            return featuresService.goToPage(ses);
        }

        if (!check) {
            userService.save(user, redAttr);
        }

        return featuresService.goToPage(ses);
    }

    @GetMapping("/login")
    public String login(RedirectAttributes redirectAttributes, HttpSession ses) {
        redirectAttributes.addFlashAttribute("login", true);
        return featuresService.goToPage(ses);
    }


    @GetMapping("/loginError")
    public String loginError(RedirectAttributes redirectAttributes, HttpSession ses) {
        redirectAttributes.addFlashAttribute("login", true);
        redirectAttributes.addFlashAttribute("loginError", true);
        return featuresService.goToPage(ses);
    }
}
