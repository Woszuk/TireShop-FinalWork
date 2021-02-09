package pl.hejnar.tireshop.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.exception.UserCannotBeSavedException;

public interface UserService {

    User findByUsername (String username);

    void save(User user, Model model, RedirectAttributes redAttr) throws UserCannotBeSavedException;
}
