package pl.hejnar.tireshop.service;

import org.springframework.web.multipart.MultipartFile;
import pl.hejnar.tireshop.entity.ShopProduct;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ShopService {
    void addToBasket(int quantityToBuy, Long id, HttpSession ses, Principal principal);

    void editData(ShopProduct shopProduct, MultipartFile file, Long id);
}
