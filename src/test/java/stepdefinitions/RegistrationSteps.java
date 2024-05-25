package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.RegistrationConfirmationPage;
import pages.RegistrationPage;
import testcontext.CucumberTestContext;

public class RegistrationSteps {

    private CucumberTestContext context;

    public RegistrationSteps(CucumberTestContext context) {
        this.context = context;
    }

    @Given("Launch Registration form with URL {string}")
    public void launchRegistrationFormWithURL(String url) {
        RegistrationPage.using(context).launchApplication(url);
    }

    @When("user enter all details for registration")
    public void userEnterAllDetailsForRegistration() {
        RegistrationPage.using(context).enterUserDetails("auto", "mate")
                .enterUserCredentials("auto@gmail.com", "test@123")
                .enterAddress("123 main street", "hyderabad", "500037")
                .register();
    }

    @Then("verify confirmation message is displayed")
    public void verifyConfirmationMessageIsDisplayed() {
        Assert.assertEquals(RegistrationConfirmationPage.using(context).getFirstName(), "auto");
    }
}
