package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import pl.hejnar.tireshop.entity.*;
import pl.hejnar.tireshop.repository.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ProductRepository productRepository;

    public TransactionServiceImpl(BasketItemRepository basketItemRepository, UserRepository userRepository, OrderRepository orderRepository, DeliveryRepository deliveryRepository, PaymentMethodRepository paymentMethodRepository, ProductRepository productRepository) {
        this.basketItemRepository = basketItemRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void totalPrice(HttpSession ses) {
        if(ses.getAttribute("basket") != null){
            List<BasketItem> basketItemList = (List<BasketItem>) ses.getAttribute("basket");
            BigDecimal totalPrice = BigDecimal.ZERO;

            for(BasketItem basketItem: basketItemList){
                basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
                totalPrice = totalPrice.add(basketItem.getProduct().getPrice().multiply(new BigDecimal(basketItem.getQuantity())));
            }

            ses.setAttribute("totalPrice", totalPrice);
        }
    }

    @Override
    public void saveOrder(String delivery, String paymentMethod, Principal principal, HttpSession ses) {
        List<BasketItem> basketItemList = new ArrayList<>();
        Map<BigDecimal, Product> productBigDecimalMap = new HashMap<>();
        Delivery del = deliveryRepository. findByName(delivery);
        PaymentMethod payMet = paymentMethodRepository.findByType(paymentMethod);
        BigDecimal totalPrice = (BigDecimal)ses.getAttribute("totalPrice");
        User user = userRepository.findByUsername(principal.getName());

        if(principal.getName() != null){
            basketItemList = basketItemRepository.findBasketItemsByUser(user);
        }

        Order order = new Order()
                .setTotalPrice(totalPrice)
                .setDelivery(del)
                .setPaymentMethod(payMet)
                .setUser(user);

        for(BasketItem basketItem: basketItemList){
            basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
            productBigDecimalMap.put(basketItem.getProduct().getPrice(), basketItem.getProduct());
            productRepository.updateQuantity(basketItem.getQuantity(), basketItem.getProduct().getId());
            basketItemRepository.delete(basketItem);
        }

        order.setProducts(productBigDecimalMap);

        ses.removeAttribute("basket");
        orderRepository.save(order);
    }
}
