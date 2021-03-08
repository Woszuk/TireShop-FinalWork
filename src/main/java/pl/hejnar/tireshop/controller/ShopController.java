package pl.hejnar.tireshop.controller;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.cache.spi.support.StorageAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hejnar.tireshop.entity.ShopProduct;
import pl.hejnar.tireshop.entity.User;
import pl.hejnar.tireshop.repository.ShopProductRepository;
import pl.hejnar.tireshop.service.FeaturesService;
import pl.hejnar.tireshop.service.ShopServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopProductRepository shopProductRepository;
    private final ShopServiceImpl shopService;
    private final FeaturesService featuresService;

    public ShopController(ShopProductRepository shopProductRepository, ShopServiceImpl shopService, FeaturesService featuresService) {
        this.shopProductRepository = shopProductRepository;
        this.shopService = shopService;
        this.featuresService = featuresService;
    }

    @ModelAttribute("user")
    public User newUser (){
        return new User();
    }

    @GetMapping("/tire")
    public String tireShop(Model model, HttpSession ses){
        ses.setAttribute("currentPage", "tire");
        model.addAttribute("tires", shopProductRepository.findAllByType("tire"));
        return "tire";
    }

    @GetMapping("/wheel-rim")
    public String wheelRimShop(Model model, HttpSession ses){
        ses.setAttribute("currentPage", "wheelRim");
        model.addAttribute("wheelRims", shopProductRepository.findAllByType("wheel-rim"));
        return "wheel-rim";
    }

    @GetMapping("/addToBasket")
    public String addToBasket(HttpSession ses){
        return featuresService.goToPage(ses);
    }

    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam int quantityToBuy, @RequestParam Long productId, @RequestParam Double scrollTo, HttpSession ses, RedirectAttributes redAttr, Principal principal){
        redAttr.addFlashAttribute("scrollTo", scrollTo);
        shopService.addToBasket(quantityToBuy, productId, ses, principal);
        return featuresService.goToPage(ses);
    }

    @GetMapping("/edit")
    public String editProduct(Model model, HttpSession ses) {
        model.addAttribute("shopProduct", new ShopProduct());
        return featuresService.goToPage(ses);
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("shopProduct") ShopProduct shopProduct, @RequestParam("file")MultipartFile file, @RequestParam("productId") long id, HttpSession ses){
        shopService.editData(shopProduct, file, id);
        return featuresService.goToPage(ses);
    }
}
