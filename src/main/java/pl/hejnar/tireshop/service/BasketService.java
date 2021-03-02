package pl.hejnar.tireshop.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface BasketService {

    void productInSession(HttpSession ses, Model model);

    boolean checkIdItem (HttpSession ses, Long id);

    void removeProduct(HttpSession ses, Long id);

    void changeQuantity(HttpSession ses, String type, Long id);
}
