package testcontext;

import org.openqa.selenium.WebDriver;

public class CucumberTestContext implements TestingContext {

    private WebDriver webDriver;

    @Override
    public WebDriver getDriver() {
        return webDriver;
    }

    @Override
    public void SetDriver(WebDriver driver) {
        this.webDriver = webDriver;
    }

    public void setDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
    }
}
