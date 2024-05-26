package testexecution.configuration;

import org.aeonbits.owner.Config;


//classpath:folder/${env}.properties
@Config.Sources({"file:${environmentFile}"})
public interface FrameworkConfig extends Config {
    @DefaultValue("QA")
    String environment();

    @DefaultValue("LOCAL_GRID")
    GridConfiguration gridConfig();

    @DefaultValue("true")
    String headlessExecution();

    @DefaultValue("2")
    String threadCount();

    @DefaultValue("Chrome")
    String platform();

    @DefaultValue("default")
    String getResolution();
}
