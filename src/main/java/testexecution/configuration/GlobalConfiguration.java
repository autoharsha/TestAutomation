package testexecution.configuration;

import testexecution.platforms.Resolution;

public class GlobalConfiguration {

    public static final FrameworkConfig FRAMEWORK_CONFIG = EnvironmentSetUp.getEnvironment();

    public static final WebDriverExecPathConfig DRIVER_EXEC_PATH_CONFIG = EnvironmentSetUp.getWebDriverPath();
    public static final ExecutionMode EXECUTION_MODE = setupExecutionMode();

    private static ExecutionMode setupExecutionMode() {
        if (FRAMEWORK_CONFIG.gridConfig().isGridEnabled()) {
            return ExecutionMode.REMOTE;
        } else {
            return ExecutionMode.LOCAL;
        }
    }

    public static String getResolution() {
        if (FRAMEWORK_CONFIG.getResolution() == null || FRAMEWORK_CONFIG.getResolution().isEmpty()) {
            return "1920,1080";
        } else {
            Resolution device = Resolution.getLabel(FRAMEWORK_CONFIG.getResolution());
            return device == null ? "1920,1080" : device.getWidth() + "," + device.getHeight();
        }
    }
}
