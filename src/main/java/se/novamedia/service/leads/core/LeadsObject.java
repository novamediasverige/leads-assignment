package se.novamedia.service.leads.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeadsObject {

    @JsonProperty("id")
    public final long id;

    @JsonProperty("firstName")
    public final String firstName;

    @JsonProperty("lastName")
    public final String lastName;

    @JsonProperty("ssn")
    public final String ssn;

    @JsonProperty("email")
    public final String email;

    public LeadsObject(long id, String firstName, String lastName, String ssn, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
    }
}