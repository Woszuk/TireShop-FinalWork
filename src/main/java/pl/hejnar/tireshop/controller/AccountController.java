package pl.hejnar.tireshop.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.UserRepository;
import pl.hejnar.tireshop.service.AccountService;
import pl.hejnar.tireshop.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AccountService accountService;

    public AccountController(UserRepository userRepository, UserService userService, AccountService accountService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.accountService = accountService;
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
    public String accountDetails() {
        return "account";
    }

    @GetMapping("/change/details")
    public String changeDetails(Model model) {
        return "change-account-details";
    }

    @PostMapping("/change/details")
    public String changeDetails(@ModelAttribute("loggedUser") @Valid User changeUser, BindingResult result, Model model, Principal principal) {
        boolean check = accountService.checkData(model, changeUser, principal);
        if (result.hasErrors()) {
            return "change-account-details";
        }

        if (!check) {
            userRepository.save(changeUser);
            return "redirect:/account/details";
        }

        return "change-account-details";
    }
}
