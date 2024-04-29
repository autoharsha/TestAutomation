package testexecution.configuration;

import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;
import java.util.Objects;

public class EnvironmentSetUp {

    private static final String ENVIRONMENT_FILE = "environmentFile";
    private static final String WEB_DRIVER_PATH = "webDriverExecPathConfig";
    private static final String LOCAL = "local";
    private static final String ENV = "env";

    static {
        setupConfigurations();
    }

    private static void setupConfigurations() {
        if (Objects.isNull(ConfigFactory.getProperty(ENV))) {
            synchronized (EnvironmentSetUp.class) {
                configureEnvironment();
                configueWebDriverPath();
            }
        }
    }

    private static void configureEnvironment() {
        if (Objects.isNull(System.getProperty(ENVIRONMENT_FILE))) {
            System.setProperty(ENVIRONMENT_FILE, LOCAL);
        }
        FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
        Environments environment = fetchEnvironment(config);
        ConfigFactory.setProperty(ENV, environment.getEnvName());
    }

    private static void configueWebDriverPath() {
        if (Objects.isNull(System.getProperty(WEB_DRIVER_PATH))) {
            System.setProperty(WEB_DRIVER_PATH, LOCAL);
        }
        WebDriverExecPathConfig config = ConfigFactory.create(WebDriverExecPathConfig.class);
    }

    private static Environments fetchEnvironment(FrameworkConfig config) {
        return Arrays.stream(Environments.values()).filter(env -> env.getEnvName().equalsIgnoreCase(config.environment()))
                .findFirst()
                .orElse(Environments.QA);
    }

    public static FrameworkConfig getEnvironment() {
        return ConfigFactory.create(FrameworkConfig.class);
    }

    public static RunConfiguration getRunConfiguration() {
        return ConfigFactory.create(RunConfiguration.class);
    }

    public static WebDriverExecPathConfig getWebDriverPath() {
        return ConfigFactory.create(WebDriverExecPathConfig.class);
    }
}
