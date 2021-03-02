package pl.hejnar.tireshop.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.Address;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface TransactionService {

    void totalPrice(HttpSession ses);

    void saveOrder(String delivery, String paymentMethod, Principal principal, HttpSession ses, Address address);
}
