package se.novamedia.service.leads.resource;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.jdbi.v3.core.Jdbi;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import se.novamedia.service.leads.LeadsApplication;
import se.novamedia.service.leads.api.LeadCreationRequest;
import se.novamedia.service.leads.jdbi.LeadsDao;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeadResourceTest {

    private LeadsDao leadsDao = mock(LeadsDao.class);
    private LeadsApplication leadApplication = mock(LeadsApplication.class);

    @Rule
    public ResourceTestRule resourceTestRule = ResourceTestRule.builder().addResource(new LeadResource(leadApplication)).build();

    @Before
    public void setUp() {
        Jdbi database = mock(Jdbi.class);
        when(leadApplication.getDatabase()).thenReturn(database);
        when(database.onDemand(LeadsDao.class)).thenReturn(leadsDao);
    }

    @Test
    public void shouldCreateNewLead() {
        LeadCreationRequest request = new LeadCreationRequest("John", "Doe", "197001010000", "john.doe@foo.com");
        given(leadsDao.insertLead(request)).willReturn(Long.valueOf(1));

        Response response = resourceTestRule
            .target("leads/lead")
            .request()
            .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        then(response.getStatus()).isEqualTo(200);
        then(response.readEntity(String.class)).isEqualTo("1");
        then(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
    }
}
