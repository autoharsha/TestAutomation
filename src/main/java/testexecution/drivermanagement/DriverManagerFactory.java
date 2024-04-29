package testexecution.drivermanagement;

import testexecution.drivermanagement.browsers.WebBrowser;
import testexecution.platforms.Browser;
import testexecution.platforms.Platform;

public class DriverManagerFactory {
    public DriverManager getManager(Platform type) {
        if (type instanceof Browser) {
            return new WebBrowser(type).getDriverManager();
        } else {
            return new WebBrowser(Browser.CHROME).getDriverManager();
        }
    }
}
