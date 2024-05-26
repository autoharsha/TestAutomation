package testexecution.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

@Config.Sources({"file:${webDriverExecPathConfig}"})
public interface WebDriverExecPathConfig extends Config, Reloadable {

    @DefaultValue("C:\\Automation\\executables\\chromedriver.exe")
    String chromeDriverPath();

    @DefaultValue("C:\\Automation\\executables\\chromedriver.exe")
    String firefoxDriverPath();
}
