package pl.hejnar.tireshop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.hejnar.tireshop.entity.Role;
import pl.hejnar.tireshop.entity.User;

import pl.hejnar.tireshop.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class ApplicationUserService implements UserDetailsService {

    private UserServiceImpl userService;

    @Autowired
    public void setUserRepository(UserServiceImpl userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null || !user.isEnabled()){
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role: user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
