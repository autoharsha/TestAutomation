package stepdefinitions;


import io.cucumber.java.*;
import testcontext.CucumberTestContext;

public class Hooks {
    private CucumberTestContext content;
    private String reportKey;

    public Hooks(CucumberTestContext content) {
        this.content = content;
    }

    @Before
    public void setup(Scenario scenario) {
        
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
