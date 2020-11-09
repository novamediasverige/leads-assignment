package se.novamedia.service.leads;

import io.dropwizard.db.DataSourceFactory;

public class DefaultDataSourceFactory extends DataSourceFactory {
    public static final int MIN_CONNECTION_POOL_SIZE = 4;
    public static final int INITIAL_CONNECTION_POOL_SIZE = 4;

    DefaultDataSourceFactory() {
        this.setMinSize(MIN_CONNECTION_POOL_SIZE);
        this.setInitialSize(INITIAL_CONNECTION_POOL_SIZE);
    }
}
