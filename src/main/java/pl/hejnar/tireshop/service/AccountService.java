package pl.hejnar.tireshop.service;

import org.springframework.ui.Model;
import pl.hejnar.tireshop.entity.User;

import java.security.Principal;

public interface AccountService {

    boolean checkData(Model model, User user, Principal principal);
}
