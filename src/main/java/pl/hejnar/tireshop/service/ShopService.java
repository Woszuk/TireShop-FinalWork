package pl.hejnar.tireshop.service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ShopService {
    void addToBasket(int quantityToBuy, Long id, HttpSession ses, Principal principal);
}
