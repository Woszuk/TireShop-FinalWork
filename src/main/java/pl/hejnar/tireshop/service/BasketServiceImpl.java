package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.repository.BasketItemRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketItemRepository basketItemRepository;

    public BasketServiceImpl(BasketItemRepository basketItemRepository, UserRepository userRepository) {
        this.basketItemRepository = basketItemRepository;
    }


    @Override
    public void productInSession(HttpSession ses, Model model) {
        if (ses.getAttribute("basket") != null){
            List<BasketItem> basketItemList = (List<BasketItem>) ses.getAttribute("basket");
            List<BasketItem> basketItems = new ArrayList<>();
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (BasketItem basketItem: basketItemList){
                basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
                if(basketItem.getQuantity() > basketItem.getProduct().getQuantity()){
                    basketItem.setQuantity(basketItem.getProduct().getQuantity());
                }
                if(basketItem.getProduct().getQuantity() >=1){
                    totalPrice = totalPrice.add(basketItem.getProduct().getPrice().multiply(new BigDecimal(basketItem.getQuantity())));
                    basketItems.add(basketItem);
                }
                if(basketItem.getProduct().getQuantity() <=0){
                    basketItemRepository.delete(basketItem);
                }
            }

            ses.setAttribute("totalPrice", totalPrice);
            if(basketItems.size()>=1){
                model.addAttribute("basketItems", basketItems);
                ses.setAttribute("basket", basketItems);
            }else {
                if(ses.getAttribute("basket") != null){
                    ses.removeAttribute("basket");
                }
            }


        }
    }

    @Override
    public boolean checkIdItem(HttpSession ses, Long id) {
        boolean check = false;
        if(ses.getAttribute("basket") != null){
            List<BasketItem> basketItems = (List<BasketItem>) ses.getAttribute("basket");

            for(BasketItem basketItem: basketItems){
                if (basketItem.getId().equals(id)) {
                    check = true;
                    break;
                }
            }
        }

        return check;
    }

    @Override
    public void removeProduct(HttpSession ses, Long id) {
        if(ses.getAttribute("basket") != null){
            List<BasketItem> basketItems = (List<BasketItem>) ses.getAttribute("basket");

            basketItems.removeIf(basketItem -> basketItem.getId().equals(id));

            if(basketItems.size() >= 1){
                ses.setAttribute("basket", basketItems);
            }else {
                ses.removeAttribute("basket");
            }

            basketItemRepository.deleteItemFromBasket(id);
        }
    }

    @Override
    public void changeQuantity(HttpSession ses, String type, Long id) {
        BasketItem basketItem = basketItemRepository.getOne(id);
        int quantity = basketItem.getQuantity();
        if(type.equals("minus")){
            basketItemRepository.updateBasketItem(--quantity, id);
        }else if(type.equals("plus")){
            basketItemRepository.updateBasketItem(++quantity, id);
        }
    }
}