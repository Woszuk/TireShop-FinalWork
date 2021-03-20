package pl.hejnar.tireshop.fixtures;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.Role;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.RoleRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class FixtureUserAdmin {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public FixtureUserAdmin(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUserToDatabase(){
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_ADMIN");
        Role role2 = roleRepository.findByName("ROLE_USER");

        roles.add(role);
        roles.add(role2);

        User user = new User()
                .setFirstName("Adrian")
                .setLastName("Hejnar")
                .setEmail("adrianhejnar@gmail.com")
                .setUsername("Woszuk")
                .setPassword(passwordEncoder.encode("haslo"))
                .setRoles(roles);

        userRepository.save(user);
    }
}
