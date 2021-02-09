package pl.hejnar.tireshop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.Role;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.exception.UserCannotBeSavedException;
import pl.hejnar.tireshop.repository.RoleRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

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
    public void save(User user, Model model, RedirectAttributes redAttr) throws UserCannotBeSavedException  {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        boolean check = false;

        if(userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("errorEmail", "errorEmail");
            check = true;
        }

        if(userRepository.findByUsername(user.getUsername()) != null){
            model.addAttribute("errorUsername", "errorUserName");
            check = true;
        }


        if(!check){
            userRepository.save(user);
            redAttr.addFlashAttribute("someChange", "save");
            redAttr.addFlashAttribute("successfullyRegistered", "success");
        }else {
            model.addAttribute("someChange", "error");
            throw new UserCannotBeSavedException("User cannot be saved");
        }

    }
}
