package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.entity.ShopProduct;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.BasketItemRepository;
import pl.hejnar.tireshop.repository.ShopProductRepository;
import pl.hejnar.tireshop.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Override
    public void editData(ShopProduct shopProduct, MultipartFile file, Long id) {
        ShopProduct shopProductInDB = shopProductRepository.getOne(id);
        shopProduct.setType(shopProductInDB.getType());
        shopProduct.setId(id);

        String name = uploadFile(file, shopProduct);

        if(name != null){
            shopProduct.setImg(name);
        }else {
            shopProduct.setImg(shopProductInDB.getImg());
        }

        shopProductRepository.save(shopProduct);
    }

    @Override
    public void addNewProduct(ShopProduct shopProduct, MultipartFile file, HttpSession ses){
        if(ses.getAttribute("currentPage").equals("tire")){
            shopProduct.setType("tire");
        }else {
            shopProduct.setType("wheel-rim");
        }
        String name = uploadFile(file, shopProduct);
        shopProduct.setImg(name);

        shopProductRepository.save(shopProduct);
    }

    private String uploadFile(MultipartFile file, ShopProduct shopProduct) {
        String name = null;
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                Path path;
                if(shopProduct.getType().equals("tire")){
                    path = Paths.get("E:\\CodersLab\\TireShop - FinalWork\\src\\main\\resources\\static\\images\\tire\\" + file.getOriginalFilename());
                }else {
                    path = Paths.get("E:\\CodersLab\\TireShop - FinalWork\\src\\main\\resources\\static\\images\\wheel-rims\\" + file.getOriginalFilename());
                }

                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            name = file.getOriginalFilename();
        }

        return name;
    }
}
