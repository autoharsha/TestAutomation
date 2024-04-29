package testexecution.configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:environment/${env}.properties"})
public interface RunConfiguration extends Config {
    String url();

    String dbServerName();

    String dbSchemaName();

    String dbUserName();

    String dbPassword();
}
