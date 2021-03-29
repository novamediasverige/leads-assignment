package se.novamedia.service.leads;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import se.novamedia.service.leads.resource.LeadResource;

public class LeadsApplication extends Application<LeadsConfiguration> {

    private Jdbi jdbi;

    public static void main(String[] args) throws Exception {
        new LeadsApplication().run(args);
    }

    @Override
    public String getName() {
        return "leads-stub";
    }

    @Override
    public void initialize(Bootstrap<LeadsConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(LeadsConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(LeadsConfiguration configuration, Environment environment) throws Exception {
        jdbi = create(environment, configuration.getDataSourceFactory(), getName());
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new H2DatabasePlugin());

        environment.jersey().register(new LeadResource(this));
    }

    public static Jdbi create(Environment environment, DataSourceFactory dataSourceFactory, String name) {
        return new JdbiFactory().build(environment, dataSourceFactory, name);
    }

    public Jdbi getDatabase() {
        return jdbi;
    }
}
