package se.novamedia.service.leads.resource;

import se.novamedia.service.leads.LeadsApplication;
import se.novamedia.service.leads.api.LeadCreationRequest;
import se.novamedia.service.leads.core.LeadsObject;
import se.novamedia.service.leads.core.LeadsProcessor;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
public class LeadResource {

    LeadsApplication application;
    LeadsProcessor leadsProcessor;

    public LeadResource(LeadsApplication application) {
        this.application = application;
        this.leadsProcessor = new LeadsProcessor(application);
    }

    @Path("/leads/lead")
    @POST
    public Response createLeadCandidate(@Valid LeadCreationRequest leadCreationRequest, @Context UriInfo uriInfo) {
        long leadId = leadsProcessor.storeLeadCandidate(leadCreationRequest);
        URI leadUri = uriInfo.getAbsolutePathBuilder().path("{leadId}").build(leadId);
        return Response.created(leadUri).build();
    }

    @Path("/leads/getLeadsByDate")
    @GET
    public Response getLeadsByDate(@Context UriInfo uriInfo, @QueryParam("from") String from, @QueryParam("to") String to) {
        ArrayList<LeadsObject> leads = leadsProcessor.getLeadsByDate(from, to);
        return Response.ok(leads).build();
    }
}
