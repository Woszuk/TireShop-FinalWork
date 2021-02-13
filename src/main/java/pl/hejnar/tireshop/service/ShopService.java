package pl.hejnar.tireshop.service;

import javax.servlet.http.HttpSession;

public interface ShopService {
    void addToBasket(int quantityToBuy, Long id, HttpSession ses);
}
