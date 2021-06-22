package se.novamedia.service.leads.jdbi;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import se.novamedia.service.leads.LeadsApplication;
import se.novamedia.service.leads.LeadsConfiguration;
import se.novamedia.service.leads.api.LeadCreationRequest;

import java.util.List;
import java.util.Map;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class LeadsDaoIT {

    private static final String CONFIG_LOCATION = "leads-stub-test.yml";

    private static final DropwizardAppExtension<LeadsConfiguration> EXT =
        new DropwizardAppExtension<>(LeadsApplication.class, resourceFilePath(CONFIG_LOCATION));
    private Jdbi database;
    private LeadsDao leadsDao;

    @BeforeEach
    void beforeEach() throws Exception {
        LeadsApplication application = EXT.getApplication();
        application.run("db", "migrate", resourceFilePath(CONFIG_LOCATION));
        database = application.getDatabase();
        leadsDao = database.onDemand(LeadsDao.class);
    }

    @Test
    void insertLead() {
        LeadCreationRequest request = new LeadCreationRequest("John", "Doe", "191212121212", "email@test.com");

        leadsDao.insertLead(request);

        assertThat(leadsTable()).hasSize(1);
    }

    private List<Map<String, Object>> leadsTable() {
        return database.open()
            .createQuery("select * from leads")
            .mapToMap().list();
    }
}
