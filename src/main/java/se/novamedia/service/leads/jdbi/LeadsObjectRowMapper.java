package se.novamedia.service.leads.jdbi;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import se.novamedia.service.leads.core.LeadsObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadsObjectRowMapper implements RowMapper<LeadsObject> {

    @Override
    public LeadsObject map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LeadsObject(rs.getLong("id"), rs.getString("firstName"),
                rs.getString("lastName"), rs.getString("ssn"), rs.getString("email"));
    }
}
