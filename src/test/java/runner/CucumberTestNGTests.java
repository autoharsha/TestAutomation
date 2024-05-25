package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        //monochrome = true,
        // dryRun = true,
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        tags = ("@UI")
)
public class CucumberTestNGTests extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
