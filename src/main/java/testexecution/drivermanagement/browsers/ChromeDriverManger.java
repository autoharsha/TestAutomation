package testexecution.drivermanagement.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testexecution.configuration.GlobalConfiguration;
import testexecution.drivermanagement.DriverManager;

public class ChromeDriverManger extends DriverManager {
    @Override
    protected void createDriver() {
        System.setProperty("webdriver.chrome.driver", GlobalConfiguration.DRIVER_EXEC_PATH_CONFIG.chromeDriverPath());
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        if (GlobalConfiguration.FRAMEWORK_CONFIG.headlessExecution().equalsIgnoreCase("true")) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().minimize();
        driverThreadLocal.set(driver);

    }
}
