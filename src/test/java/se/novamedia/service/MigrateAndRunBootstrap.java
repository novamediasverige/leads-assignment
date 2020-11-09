package se.novamedia.service;

import se.novamedia.service.leads.LeadsApplication;

public class MigrateAndRunBootstrap {

    public static final String CONFIG = "src/main/resources/se/novamedia/microservice/config/environment/local.yml";

    public static void main(String[] args) throws Exception {
        System.setProperty("environment", "local");
        LeadsApplication.main(new String[]{"db", "migrate", CONFIG});
        LeadsApplication.main(new String[]{"server", CONFIG});
    }
}
