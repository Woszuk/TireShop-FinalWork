package pl.hejnar.tireshop.fixtures;

import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.Role;
import pl.hejnar.tireshop.repository.RoleRepository;

@Component
public class FixtureRole {

    private RoleRepository roleRepository;

    public FixtureRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRoleToDatabase () {
        Role role = new Role().setName("ROLE_ADMIN");
        Role role2 = new Role().setName("ROLE_USER");

        roleRepository.save(role);
        roleRepository.save(role2);
    }
}
