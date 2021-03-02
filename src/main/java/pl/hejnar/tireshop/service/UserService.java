package pl.hejnar.tireshop.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.User;

public interface UserService {

    User findByUsername (String username);

    void save(User user, RedirectAttributes redAttr);

    boolean checkUserAndPassword (User user, RedirectAttributes redAttr, String repeatPassword);
}
