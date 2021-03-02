package pl.hejnar.tireshop.fixtures;

import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.Product;
import pl.hejnar.tireshop.entity.ShopProduct;
import pl.hejnar.tireshop.repository.ShopProductRepository;

import java.math.BigDecimal;

@Component
public class FixtureProduct {

    private final ShopProductRepository shopProductRepository;

    public FixtureProduct(ShopProductRepository shopProductRepository) {
        this.shopProductRepository = shopProductRepository;
    }

    public void addProductToDatabase () {
        ShopProduct product = new ShopProduct("sqn-15-anzio-turn-polar.png", "wheel-rim", "Anzio", "Turn Polar", "5x100 15\"", new BigDecimal(315), 3);
        ShopProduct product2 = new ShopProduct("alutec-grip.png", "wheel-rim", "Atulec", "Grip", "5x114.3 16\"", new BigDecimal(319), 9);
        ShopProduct product3 = new ShopProduct("ronal-r46-grey.png", "wheel-rim", "Ronal", "R46 Grey", "4x100 15\"", new BigDecimal(299), 0);
        ShopProduct product4 = new ShopProduct("debica-frigo.png", "tire", "Dębica", "Frigo 2", "205/55 R16", new BigDecimal(205), 3);
        ShopProduct product5 = new ShopProduct("michelin-alpin-6.png", "tire", "Michellin", "Alpin 6", "205/55 R16", new BigDecimal(430), 0);
        ShopProduct product6 = new ShopProduct("pirelli-sottozero-serie-3.png", "tire", "Pirelli", "SottoZero Serie 3", "225/55 R17", new BigDecimal(497), 4);

        shopProductRepository.save(product);
        shopProductRepository.save(product2);
        shopProductRepository.save(product3);
        shopProductRepository.save(product4);
        shopProductRepository.save(product5);
        shopProductRepository.save(product6);
    }
}
