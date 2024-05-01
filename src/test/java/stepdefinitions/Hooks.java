package stepdefinitions;


import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Reporter;
import testcontext.CucumberTestContext;
import testexecution.drivermanagement.DriverManager;
import testexecution.drivermanagement.DriverManagerFactory;
import testexecution.platforms.Browser;

public class Hooks {
    private CucumberTestContext content;
    private DriverManager driverManager;
    private WebDriver webDriver;

    public Hooks(CucumberTestContext content) {
        this.content = content;
    }

    @Before
    public void setup(Scenario scenario) {
        if (scenario.getSourceTagNames().stream().noneMatch(n -> n.equalsIgnoreCase("@API"))) {
            createDriver();
        }
    }

    private void createDriver() {
        String platform = "";
        try {
            platform = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Platform");
        } catch (NullPointerException e) {

        }
        if (platform != null) {
            if (platform.equalsIgnoreCase("chrome") || platform.equalsIgnoreCase("edge") || platform.equalsIgnoreCase("firefox") || platform.equalsIgnoreCase("safari")) {
                driverManager = new DriverManagerFactory().getManager(Browser.getValue(platform));
                webDriver = driverManager.getDriver();
            } else {
                throw new WebDriverException("Driver not handled");
            }
        }
        content.setDriver(webDriver);
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {

    }

    @AfterStep
    public void afterStep() {

    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (content.getDriver() != null) {
            content.quitDriver();
        }
    }

    @After(order = 1)
    public void captureError(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
    }


}
