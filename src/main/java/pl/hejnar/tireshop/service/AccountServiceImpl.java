package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkData(Model model, User user, Principal principal) {
        boolean check = false;
        if(principal.getName() != null){
            if(userRepository.findByEmail(user.getEmail()) != null && !(userRepository.findByEmail(user.getEmail()).getId().equals(user.getId()))) {
                model.addAttribute("errorEmail", true);
                check = true;
            }

            if(userRepository.findByUsername(user.getUsername()) != null && !(userRepository.findByUsername(user.getUsername()).getId().equals(user.getId()))){
                model.addAttribute("errorUsername", true);
                check = true;
            }
        }
        return check;
    }
}
