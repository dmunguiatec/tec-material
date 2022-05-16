package edu.tec.ic6821.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private static final String PROPERTIES = "/application.properties";
    private static final String PROP_DB_URL = "db.url";
    private static final String PROP_DB_USR = "db.usr";
    private static final String PROP_DB_PWD = "db.pwd";

    private final Properties properties;

    public ApplicationProperties() {

        this.properties = new Properties();

        try (InputStream in = ApplicationProperties.class.getResourceAsStream(PROPERTIES)) {

            this.properties.load(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDbUrl() {
        return this.properties.getProperty(PROP_DB_URL);
    }

    public String getDbUsr() {
        return this.properties.getProperty(PROP_DB_USR);
    }

    public String getDbPwd() {
        return this.properties.getProperty(PROP_DB_PWD);
    }
}
