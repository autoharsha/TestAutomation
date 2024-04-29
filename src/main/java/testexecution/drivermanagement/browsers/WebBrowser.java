package testexecution.drivermanagement.browsers;

import testexecution.configuration.ExecutionMode;
import testexecution.configuration.GlobalConfiguration;
import testexecution.drivermanagement.DriverManager;
import testexecution.platforms.Browser;
import testexecution.platforms.Platform;

public class WebBrowser {
    private Browser browser;

    public WebBrowser(Platform type) {
        this.browser = (Browser) type;
    }

    public DriverManager getDriverManager() {
        if (GlobalConfiguration.EXECUTION_MODE == ExecutionMode.REMOTE) {
            return new RemoteWebDriverManager(browser.getName());
        } else {
            switch (browser) {
                case CHROME:
                    return new ChromeDriverManger();
                default:
                    throw new IllegalStateException("Unexpected value" + browser);
            }
        }
    }
}
