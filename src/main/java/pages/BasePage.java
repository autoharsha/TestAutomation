package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import testcontext.TestingContext;

public abstract class BasePage {
    protected WebDriver driver;
    protected TestingContext testingContext;
    
    public BasePage(TestingContext context) {
        this.testingContext = context;
        this.driver = context.getDriver();
        PageFactory.initElements(this.driver, this);
        setupLocalProperties();
    }

    protected abstract String getPageName();

    protected abstract String getPageDescription();

    protected abstract void setupLocalProperties();

}