package pl.hejnar.tireshop.fixtures;

import org.springframework.stereotype.Component;
import pl.hejnar.tireshop.entity.PaymentMethod;
import pl.hejnar.tireshop.repository.PaymentMethodRepository;

@Component
public class FixturePaymentMethod {

    private final PaymentMethodRepository paymentMethodRepository;

    public FixturePaymentMethod(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void addPaymentMethodsToDatabase(){
        PaymentMethod paymentMethod = new PaymentMethod().setType("transfer").setDescription("Przelew tradycyjny");
        PaymentMethod paymentMethod2 = new PaymentMethod().setType("online").setDescription("Szybka płatność online");
        PaymentMethod paymentMethod3 = new PaymentMethod().setType("cash").setDescription("Gotówka przy odbiorze");

        paymentMethodRepository.save(paymentMethod);
        paymentMethodRepository.save(paymentMethod2);
        paymentMethodRepository.save(paymentMethod3);
    }
}
