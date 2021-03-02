package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import pl.hejnar.tireshop.entity.*;
import pl.hejnar.tireshop.repository.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ShopProductRepository shopProductRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderAddressRepository orderAddressRepository;

    public TransactionServiceImpl(BasketItemRepository basketItemRepository, UserRepository userRepository, OrderRepository orderRepository, DeliveryRepository deliveryRepository, PaymentMethodRepository paymentMethodRepository, ShopProductRepository shopProductRepository, OrderProductRepository orderProductRepository, OrderAddressRepository orderAddressRepository) {
        this.basketItemRepository = basketItemRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.shopProductRepository = shopProductRepository;
        this.orderProductRepository = orderProductRepository;
        this.orderAddressRepository = orderAddressRepository;
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
    public void saveOrder(String delivery, String paymentMethod, Principal principal, HttpSession ses, Address address) {
        List<BasketItem> basketItemList = new ArrayList<>();
        List<OrderProduct> orderProducts = new ArrayList<>();
        Delivery del = deliveryRepository. findByName(delivery);
        PaymentMethod payMet = paymentMethodRepository.findByType(paymentMethod);
        BigDecimal totalPrice = (BigDecimal)ses.getAttribute("totalPrice");
        User user = userRepository.findByUsername(principal.getName());
        Boolean check = true;

        if(principal.getName() != null){
            basketItemList = basketItemRepository.findBasketItemsByUser(user);
        }

        Order order = new Order()
                .setTotalPrice(totalPrice)
                .setDelivery(del)
                .setPaymentMethod(payMet)
                .setUser(user);

        orderRepository.save(order);

        for(BasketItem basketItem: basketItemList){
            basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
            if(basketItem.getProduct().getQuantity() < basketItem.getQuantity()){
                ses.setAttribute("errorItem", true);
                check = false;
                break;
            }
        }

        if(check){
            for(BasketItem basketItem: basketItemList){
                basketItem = basketItemRepository.findBasketItemById(basketItem.getId());
                Product product = basketItem.getProduct();
                OrderProduct orderProduct = new OrderProduct(product.getImg(), product.getType(), product.getBrand(), product.getModel(), product.getSize(), product.getPrice(), basketItem.getQuantity(), user, order);
                orderProducts.add(orderProduct);
                orderProductRepository.save(orderProduct);
                shopProductRepository.updateQuantity(basketItem.getQuantity(), basketItem.getProduct().getId());
                basketItemRepository.delete(basketItem);
            }

            OrderAddress orderAddress = new OrderAddress()
                    .setCity(address.getCity())
                    .setApartmentNumber(address.getApartmentNumber())
                    .setStreet(address.getStreet())
                    .setPostalCode(address.getPostalCode())
                    .setNumberOfBuilding(address.getNumberOfBuilding());

            orderAddressRepository.save(orderAddress);
            order.setOrderAddress(orderAddress);
            order.setOrderProducts(orderProducts);
            ses.removeAttribute("basket");
            orderRepository.save(order);
        }
    }
}
