package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class HomeController {

    private UserServiceImpl userService;

    public HomeController(UserServiceImpl userService) {
        this.userService = userService;
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
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, RedirectAttributes redAttr){
        boolean check = userService.checkUser(user, model);

        if(result.hasErrors()){
            model.addAttribute("someChange", "error");
            return "home";
        }

        if(!check){
            userService.save(user, redAttr);
            return "redirect:/";
        }else {
            return "/home";
        }
    }
}
