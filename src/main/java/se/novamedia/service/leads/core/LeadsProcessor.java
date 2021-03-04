package se.novamedia.service.leads.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.novamedia.service.leads.LeadsApplication;
import se.novamedia.service.leads.api.LeadCreationRequest;
import se.novamedia.service.leads.jdbi.LeadsDao;

public class LeadsProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(LeadsProcessor.class);

    private final LeadsApplication application;

    public LeadsProcessor(LeadsApplication application) {
        this.application = application;
    }

    public long storeLeadCandidate(LeadCreationRequest leadCreationRequest) {
        LeadsDao leadsDao = application.getDatabase().onDemand(LeadsDao.class);
        long leadId = leadsDao.insertLead(leadCreationRequest);
        LOG.info("Created lead with id {}", leadId);
        return leadId;
    }
}

