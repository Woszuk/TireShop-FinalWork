package pl.hejnar.tireshop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.Role;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.RoleRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user, RedirectAttributes redAttr) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        userRepository.save(user);
        redAttr.addFlashAttribute("someChange", true);
        redAttr.addFlashAttribute("successfullyRegistered", true);
    }

    @Override
    public boolean checkUserAndPassword(User user, RedirectAttributes redAttr, String repeatPassword) {
        boolean check = false;
        if(userRepository.findByEmail(user.getEmail()) != null) {
            redAttr.addFlashAttribute("errorEmail", true);
            check = true;
        }

        if(userRepository.findByUsername(user.getUsername()) != null){
            redAttr.addFlashAttribute("errorUsername", true);
            check = true;
        }

        if(!(user.getPassword().equals(repeatPassword))){
            redAttr.addFlashAttribute("errorPassword", true);
            check = true;
        }

        redAttr.addFlashAttribute("someChange", true);
        redAttr.addFlashAttribute("user", user);

        return check;
    }
}
