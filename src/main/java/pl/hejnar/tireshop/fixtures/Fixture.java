package pl.hejnar.tireshop.fixtures;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Fixture {
    private final FixtureRole fixtureRole;
    private final FixtureUserAdmin fixtureUserAdmin;
    private final FixtureProduct fixtureProduct;
    private final FixtureDelivery fixtureDelivery;
    private final FixturePaymentMethod fixturePaymentMethod;

    public Fixture(FixtureRole fixtureRole, FixtureUserAdmin fixtureUserAdmin, FixtureProduct fixtureProduct, FixtureDelivery fixtureDelivery, FixturePaymentMethod fixturePaymentMethod) {
        this.fixtureRole = fixtureRole;
        this.fixtureUserAdmin = fixtureUserAdmin;
        this.fixtureProduct = fixtureProduct;
        this.fixtureDelivery = fixtureDelivery;
        this.fixturePaymentMethod = fixturePaymentMethod;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void databases(){
        fixtureRole.addRoleToDatabase();
        fixtureUserAdmin.addUserToDatabase();
        fixtureProduct.addProductToDatabase();
        fixtureDelivery.addDeliveryToDatabase();
        fixturePaymentMethod.addPaymentMethodsToDatabase();
    }
}
