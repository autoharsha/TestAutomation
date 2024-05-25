package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testcontext.TestingContext;

public class RegistrationPage extends WebBasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;

    private RegistrationPage(TestingContext context) {
        super(context);
    }

    @Override
    protected String getPageName() {
        return "Registration Page";
    }

    @Override
    protected String getPageDescription() {
        return "Registration Page";
    }

    public static RegistrationPage using(TestingContext context) {
        return new RegistrationPage(context);
    }

    public void launchApplication(String url) {
        testingContext.getDriver().get(url);
    }


    public RegistrationPage enterUserDetails(String firstName, String lastName) {
        getElement().writeText(firstNameInput, firstName);
        getElement().writeText(lastNameInput, lastName);
        return this;
    }

    public RegistrationPage enterUserCredentials(String email, String password) {
        getElement().writeText(emailInput, email);
        getElement().writeText(passwordInput, password);
        return this;
    }

    public RegistrationPage enterAddress(String street, String city, String zip) {
        getElement().writeText(streetInput, street);
        getElement().writeText(cityInput, city);
        getElement().writeText(zipInput, zip);
        return this;
    }

    public void register() {
        getElement().clickElement(registerButton);
    }
}
