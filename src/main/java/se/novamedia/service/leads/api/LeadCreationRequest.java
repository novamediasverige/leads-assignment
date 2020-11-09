package se.novamedia.service.leads.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadCreationRequest {
    @JsonProperty("firstName")
    public final String firstName;

    @JsonProperty("lastName")
    public final String lastName;

    @JsonProperty("ssn")
    public final String ssn;

    @JsonProperty("email")
    public final String email;

    @JsonCreator
    public LeadCreationRequest(@JsonProperty("firstName") String firstName,
                               @JsonProperty("lastName") String lastName,
                               @JsonProperty("ssn") String ssn,
                               @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
    }
}
