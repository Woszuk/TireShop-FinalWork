package pl.hejnar.tireshop.service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface TransactionService {

    void totalPrice(HttpSession ses);

    void saveOrder(String delivery, String paymentMethod, Principal principal, HttpSession ses);
}
