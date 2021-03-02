package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.BasketItemRepository;
import pl.hejnar.tireshop.repository.ShopProductRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopProductRepository shopProductRepository;
    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;

    public ShopServiceImpl(ShopProductRepository shopProductRepository, BasketItemRepository basketItemRepository, UserRepository userRepository) {
        this.shopProductRepository = shopProductRepository;
        this.basketItemRepository = basketItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addToBasket(int quantityToBuy, Long id, HttpSession ses, Principal principal) {
        List<BasketItem> basketItemList;
        boolean check = false;

        if(ses.getAttribute("basket") == null){
            basketItemList = new ArrayList<>();
        }else {
            basketItemList = (List<BasketItem>) ses.getAttribute("basket");
        }

        for(BasketItem basketItem: basketItemList){
            if(basketItem.getProduct().getId().equals(id)) {
                BasketItem basketItemEquals = basketItemRepository.findBasketItemById(basketItem.getId());
                basketItem.setQuantity(Math.min(basketItem.getQuantity() + quantityToBuy, basketItemEquals.getProduct().getQuantity()));
                basketItemRepository.updateBasketItem(basketItem.getQuantity(), basketItem.getId());
                check = true;
                break;
            }
        }

        if(!check){
            BasketItem basketItem = new BasketItem().setQuantity(quantityToBuy).setProduct(shopProductRepository.getOne(id));
            basketItemList.add(basketItem);
            try{
                User user = userRepository.findByUsername(principal.getName());
                basketItem.setUser(user);
            }catch (NullPointerException e){
                e.getMessage();
            }
            basketItemRepository.save(basketItem);
        }

        ses.setAttribute("basket", basketItemList);
    }
}
