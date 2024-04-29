package pages;


import org.openqa.selenium.WebDriver;
import testcontext.TestingContext;

public abstract class BasePage {
    protected WebDriver driver;
    protected TestingContext testingContext;
    Boolean flag = false;

    public BasePage(TestingContext context) {
        this.testingContext = context;
        this.driver = context.getDriver();
        setupLocalProperties();
    }

    protected abstract String getPageName();

    protected abstract String getPageDescription();

    protected abstract void setupLocalProperties();
    
}