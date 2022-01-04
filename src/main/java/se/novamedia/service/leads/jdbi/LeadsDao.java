package se.novamedia.service.leads.jdbi;

import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import se.novamedia.service.leads.api.LeadCreationRequest;

public interface LeadsDao {

    @SqlUpdate("INSERT INTO leads (lead_id, ssn, first_name, last_name, email, created_at) VALUES (nextval('lead_id_seq'),:ssn, :firstName, :lastName, :email, now())")
    @GetGeneratedKeys("lead_id")
    long insertLead(@BindFields LeadCreationRequest request);
}
