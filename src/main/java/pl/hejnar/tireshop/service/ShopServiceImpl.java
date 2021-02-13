package pl.hejnar.tireshop.service;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.repository.BasketItemRepository;
import pl.hejnar.tireshop.repository.ProductRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ProductRepository productRepository;
    private BasketItemRepository basketItemRepository;

    public ShopServiceImpl(ProductRepository productRepository, BasketItemRepository basketItemRepository) {
        this.productRepository = productRepository;
        this.basketItemRepository = basketItemRepository;
    }

    @Override
    public void addToBasket(int quantityToBuy, Long id, HttpSession ses) {
        List<BasketItem> basketItemList;
        boolean check = false;

        if(ses.getAttribute("basket") == null){
            basketItemList = new ArrayList<>();
        }else {
            basketItemList = (List<BasketItem>) ses.getAttribute("basket");
        }

        for(BasketItem basketItem: basketItemList){
            if(basketItem.getProduct().getId().equals(id)) {
                basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
                basketItem.setQuantity(Math.min(basketItem.getQuantity() + quantityToBuy, basketItem.getProduct().getQuantity()));
                basketItemRepository.updateBasketItem(basketItem.getQuantity(), basketItem.getId());
                check = true;
                break;
            }
        }

        if(!check){
            BasketItem basketItem = new BasketItem().setQuantity(quantityToBuy).setProduct(productRepository.getOne(id));
            basketItemList.add(basketItem);
            basketItemRepository.save(basketItem);
        }

        ses.setAttribute("basket", basketItemList);
    }
}
