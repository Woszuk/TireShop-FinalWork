package pl.hejnar.tireshop.fixtures;

import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.Product;
import pl.hejnar.tireshop.repository.ProductRepository;

import java.math.BigDecimal;

@Component
public class FixtureProduct {

    private ProductRepository productRepository;

    public FixtureProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductToDatabase () {
        Product product = new Product()
                .setType("wheel-rim")
                .setImg("sqn-15-anzio-turn-polar.png")
                .setBrand("Anzio")
                .setModel("Turn Polar")
                .setSize("5x100 15\"")
                .setPrice(new BigDecimal(315))
                .setQuantity(3);

        Product product2 = new Product()
                .setType("wheel-rim")
                .setImg("alutec-grip.png")
                .setBrand("Atulec")
                .setModel("Grip")
                .setSize("5x114.3 16\"")
                .setPrice(new BigDecimal(319))
                .setQuantity(9);

        Product product3 = new Product()
                .setType("wheel-rim")
                .setImg("ronal-r46-grey.png")
                .setBrand("Ronal")
                .setModel("R46 Grey")
                .setSize("4x100 15\"")
                .setPrice(new BigDecimal(299))
                .setQuantity(0);

        Product product4 = new Product()
                .setType("tire")
                .setImg("debica-frigo.png")
                .setBrand("Dębica")
                .setModel("Frigo 2")
                .setSize("205/55 R16")
                .setPrice(new BigDecimal(205))
                .setQuantity(3);

        Product product5 = new Product()
                .setType("tire")
                .setImg("michelin-alpin-6.png")
                .setBrand("Michellin")
                .setModel("Alpin 6")
                .setSize("205/55 R16")
                .setPrice(new BigDecimal(430))
                .setQuantity(0);

        Product product6 = new Product()
                .setType("tire")
                .setImg("pirelli-sottozero-serie-3.png")
                .setBrand("Pirelli")
                .setModel("SottoZero Serie 3")
                .setSize("225/55 R17")
                .setPrice(new BigDecimal(497))
                .setQuantity(4);

        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
    }
}
