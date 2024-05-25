package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testcontext.TestingContext;

public class RegistrationConfirmationPage extends WebBasePage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    private RegistrationConfirmationPage(TestingContext context) {
        super(context);
    }

    @Override
    protected String getPageName() {
        return "Registration Confirmation PagePage";
    }

    @Override
    protected String getPageDescription() {
        return "Registration Confirmation Page";
    }

    public static RegistrationConfirmationPage using(TestingContext context) {
        return new RegistrationConfirmationPage(context);
    }

    public String getFirstName() {
        return getElement().getText(firstNameElement);
    }

    public void goToFlightsSearch() {
        getElement().clickElement(goToFlightsSearchButton);
    }
}
