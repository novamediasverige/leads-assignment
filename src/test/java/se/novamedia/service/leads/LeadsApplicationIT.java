package se.novamedia.service.leads;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(DropwizardExtensionsSupport.class)
class LeadsApplicationIT {

    private static final String CONFIG_LOCATION = "leads-stub-test.yml";

    private static final DropwizardAppExtension<LeadsConfiguration> EXT =
        new DropwizardAppExtension<>(LeadsApplication.class, resourceFilePath(CONFIG_LOCATION));

    @BeforeEach
    void beforeEach() throws Exception {
        EXT.getApplication().run("db", "migrate", resourceFilePath(CONFIG_LOCATION));
    }

    @Test
    void createLead() {
        Client client = EXT.client();
        String uri = String.format("http://localhost:%d/leads/lead", EXT.getLocalPort());

        Response response = client.target(uri).request()
            .post(Entity.json(ImmutableMap.of(
                "firstName", "Jane",
                "lastName", "Doe",
                "ssn", "191212121212",
                "email", "jane.doe@test.com")));

        assertAll(
            () -> assertThat(response.getStatus()).isEqualTo(201),
            () -> assertThat(response.getLocation()).hasToString("http://localhost:3000/leads/lead/1000000")
        );
    }
}
