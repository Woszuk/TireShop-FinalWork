package pl.hejnar.tireshop.fixtures;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Fixture {
    private final FixtureRole fixtureRole;
    private final FixtureUserAdmin fixtureUserAdmin;
    private final FixtureProduct fixtureProduct;

    public Fixture(FixtureRole fixtureRole, FixtureUserAdmin fixtureUserAdmin, FixtureProduct fixtureProduct) {
        this.fixtureRole = fixtureRole;
        this.fixtureUserAdmin = fixtureUserAdmin;
        this.fixtureProduct = fixtureProduct;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void databases(){
        fixtureRole.addRoleToDatabase();
        fixtureUserAdmin.addUserToDatabase();
        fixtureProduct.addProductToDatabase();
    }
}
