package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.BasketItemRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Service
public class FeaturesServiceImpl implements FeaturesService {

    private final UserRepository userRepository;
    private final BasketItemRepository basketItemRepository;

    public FeaturesServiceImpl(UserRepository userRepository, BasketItemRepository basketItemRepository) {
        this.userRepository = userRepository;
        this.basketItemRepository = basketItemRepository;
    }

    @Override
    public String goToPage(HttpSession ses) {
        if(ses.getAttribute("currentPage") != null){
            String page = (String) ses.getAttribute("currentPage");
            if(page.equals("tire")){
                return "redirect:/shop/tire";
            }else if(page.equals("wheelRim")){
                return "redirect:/shop/wheel-rim";
            }else if(page.equals("basket")){
                return "redirect:/basket";
            } else {
                return "redirect:/";
            }
        }else {
            return "redirect:/";
        }
    }

    @Override
    public void saveProductToUser(HttpSession ses, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<BasketItem> basketItemsWithUser = basketItemRepository.findBasketItemsByUser(user);
        if (ses.getAttribute("basket") != null) {
            List<BasketItem> basketItems = (List<BasketItem>) ses.getAttribute("basket");

            int quantity = 0;

            if(basketItemsWithUser.size() >=1){
                for (BasketItem basketItem : basketItems) {
                    boolean check = false;
                    for(BasketItem basketItem1: basketItemsWithUser){
                        if (basketItem.getProduct().getId().equals(basketItem1.getProduct().getId())) {
                            check = true;
                            quantity = Math.min(basketItem.getQuantity() + basketItem1.getQuantity(), basketItem1.getProduct().getQuantity());
                            basketItemRepository.deleteItemFromBasket(basketItem.getId());
                            basketItemRepository.updateBasketItem(quantity, basketItem1.getId());
                            break;
                        }
                    }
                    if(!check){
                        basketItemRepository.saveUser(user, basketItem.getId());
                    }
                }
            }else {
                for(BasketItem basketItem: basketItems){
                    basketItemRepository.saveUser(user, basketItem.getId());
                }
            }
            ses.setAttribute("basket", basketItemRepository.findBasketItemsByUser(user));
        }else if (basketItemsWithUser.size() >=1){
            ses.setAttribute("basket", basketItemsWithUser);
        }

        ses.setAttribute("userLoggedIn", true);
    }
}
