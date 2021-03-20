package pl.hejnar.tireshop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.User;

import java.security.Principal;

public interface AccountService {

    boolean checkData(Model model, User user, Principal principal);

    void saveAddress (Address address, Principal principal);

    void changeDetailsForUser(User changeUser);

    boolean changePassword(String oldPassword, String newPassword, String repeatPassword, PasswordEncoder encoder, Principal principal, Model model);

    void userLockChange(String username);

    void changeAdmin(String username);
}
