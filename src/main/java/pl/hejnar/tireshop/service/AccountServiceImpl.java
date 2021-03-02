package pl.hejnar.tireshop.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.AddressRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AccountServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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

    @Override
    public void saveAddress(Address address, Principal principal) {
        addressRepository.save(address);
        if(principal.getName() != null){
            userRepository.updateAddressToUser(address, userRepository.findByUsername(principal.getName()).getId());
        }
    }

    @Override
    public void changeDetailsForUser(User changeUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        Authentication newAuth = new UsernamePasswordAuthenticationToken(changeUser.getUsername(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String repeatPassword, PasswordEncoder encoder, Principal principal, Model model) {
        if(principal.getName()!= null){
            User user = userRepository.findByUsername(principal.getName());
            if(newPassword.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")){
                if(!(newPassword.equals(repeatPassword))){
                    model.addAttribute("different", true);
                    return false;
                }
            }else {
                model.addAttribute("validPassword", true);
                return false;
            }

            if(!(encoder.matches(oldPassword, user.getPassword()))){
                model.addAttribute("incorrectPassword", true);
                return false;
            }
        }
        return true;
    }
}
