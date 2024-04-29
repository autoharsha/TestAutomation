package testcontext;

import org.openqa.selenium.WebDriver;

public interface TestingContext {
    WebDriver getDriver();

    void SetDriver(WebDriver driver);
}
