package pl.hejnar.tireshop.fixtures;

import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.Delivery;
import pl.hejnar.tireshop.repository.DeliveryRepository;

import java.math.BigDecimal;

@Component
public class FixtureDelivery {

    private final DeliveryRepository deliveryRepository;

    public FixtureDelivery(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void addDeliveryToDatabase(){
        Delivery delivery = new Delivery().setName("DPD").setPrice(new BigDecimal(18));
        Delivery delivery2 = new Delivery().setName("UPS").setPrice(new BigDecimal(20));
        Delivery delivery3 = new Delivery().setName("DHL").setPrice(new BigDecimal(16));

        deliveryRepository.save(delivery);
        deliveryRepository.save(delivery2);
        deliveryRepository.save(delivery3);
    }
}
