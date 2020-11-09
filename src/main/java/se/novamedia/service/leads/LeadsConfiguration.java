package se.novamedia.service.leads;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LeadsConfiguration extends Configuration {

    @Valid
    @NotNull
    private DefaultDataSourceFactory database = new DefaultDataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DefaultDataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return this.database;
    }
}
