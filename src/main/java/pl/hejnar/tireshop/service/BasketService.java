package pl.hejnar.tireshop.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.BasketItem;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BasketService {

    void productInSession(HttpSession ses, Model model);

    boolean checkIdItem (HttpSession ses, Long id);

    void removeProduct(HttpSession ses, Long id);

    void changeQuantity(HttpSession ses, String type,Long id);
}
