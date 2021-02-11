package pl.hejnar.tireshop.fixtures;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Fixture {
    private FixtureRole fixtureRole;
    private FixtureUserAdmin fixtureUserAdmin;

    public Fixture(FixtureRole fixtureRole, FixtureUserAdmin fixtureUserAdmin) {
        this.fixtureRole = fixtureRole;
        this.fixtureUserAdmin = fixtureUserAdmin;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void databases(){
        fixtureRole.addRoleToDatabase();
        fixtureUserAdmin.addUserToDatabase();
    }
}
