package pl.hejnar.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("loggedUser")
    public User loggedUser(Principal principal){
        User user = null;
        if(principal.getName() != null){
            user = userRepository.findByUsername(principal.getName());
        }
        return user;
    }

    @GetMapping("/data")
    public String accountData(Principal principal) {
        if (principal.getName() != null){
            User user = userRepository.findByUsername(principal.getName());
        }
        return "account";
    }
}
