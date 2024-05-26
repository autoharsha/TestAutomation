package testexecution.drivermanagement.browsers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import testexecution.configuration.GlobalConfiguration;
import testexecution.drivermanagement.DriverManager;
import testexecution.platforms.Browser;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class RemoteWebDriverManager extends DriverManager {
    private final Browser browser;
    private final boolean headless;
    private URL hubUrl;
    private String resolution = "--windows-size";

    public RemoteWebDriverManager(String browser) {
        this.browser = Browser.getValue(browser);
        headless = Boolean.parseBoolean(GlobalConfiguration.FRAMEWORK_CONFIG.headlessExecution());
        resolution = resolution + GlobalConfiguration.getResolution();
        try {
            hubUrl = new URL(GlobalConfiguration.FRAMEWORK_CONFIG.gridConfig().getHubUrl());
        } catch (MalformedURLException e) {
            log.error("issue connecting to Selenium GRID :" + e);
        }
    }

    @Override
    protected void createDriver() {
        switch (browser) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments(resolution);
                if (Boolean.TRUE.equals(headless)) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(hubUrl, chromeOptions);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments(resolution);
                if (Boolean.TRUE.equals(headless)) {
                    edgeOptions.addArguments("--headless=new");
                }
                edgeOptions.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(hubUrl, edgeOptions);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(resolution);
                if (Boolean.TRUE.equals(headless)) {
                    firefoxOptions.addArguments("--headless=new");
                }
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(hubUrl, firefoxOptions);
            }
            case SAFARI -> {
                SafariOptions safariOptions = new SafariOptions();
                driver = new RemoteWebDriver(hubUrl, safariOptions);
                String[] resolution = GlobalConfiguration.getResolution().split(",");
                Dimension dim = new Dimension(Integer.parseInt(resolution[0]), Integer.parseInt(resolution[1]));
                driver.manage().window().setSize(dim);

            }
        }
        driverThreadLocal.set(driver);
    }
}
