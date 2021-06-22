package se.novamedia.service.leads.jdbi;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import se.novamedia.service.leads.api.LeadCreationRequest;
import se.novamedia.service.leads.core.LeadsObject;

import java.util.ArrayList;

public interface LeadsDao {

    @SqlUpdate("INSERT INTO leads (lead_id, ssn, first_name, last_name, email, created_at) VALUES (lead_id_seq.nextval,:ssn, :firstName, :lastName, :email, now())")
    @GetGeneratedKeys("lead_id")
    long insertLead(@BindFields LeadCreationRequest request);

    @SqlQuery("SELECT * FROM leads WHERE created_at BETWEEN :from AND :to")
    @RegisterRowMapper(LeadsObjectRowMapper.class)
    ArrayList<LeadsObject> getLeadsByDate(@Bind("from") String from, @Bind("to") String to);
}
