package testexecution.drivermanagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class DriverManager {

    protected RemoteWebDriver driver;
    public static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driverThreadLocal.get();
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    protected abstract void createDriver();


}
