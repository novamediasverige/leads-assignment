package se.novamedia.service.leads.resource;

import se.novamedia.service.leads.LeadsApplication;
import se.novamedia.service.leads.api.LeadCreationRequest;
import se.novamedia.service.leads.core.LeadsProcessor;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/leads/lead")
@Produces(MediaType.APPLICATION_JSON)
public class LeadResource {

    LeadsApplication application;
    LeadsProcessor leadsProcessor;

    public LeadResource(LeadsApplication application) {
        this.application = application;
        this.leadsProcessor = new LeadsProcessor(application);
    }

    @POST
    public long createLeadCandidate(@Valid LeadCreationRequest leadCreationRequest) {
        return leadsProcessor.storeLeadCandidate(leadCreationRequest);
    }
}
