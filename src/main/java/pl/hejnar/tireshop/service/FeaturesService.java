package pl.hejnar.tireshop.service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface FeaturesService {

    String goToPage(HttpSession ses);

    void saveProductToUser(HttpSession ses, Principal principal);
}
