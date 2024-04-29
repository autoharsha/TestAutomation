package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GreenKartPaymentPage;
import testcontext.CucumberTestContext;

public class AddVegetablesToCartSteps {

    private CucumberTestContext context;

    public AddVegetablesToCartSteps(CucumberTestContext context) {
        this.context = context;
    }

    @Given("Lauch GreenKart Browser with URL {string}")
    public void lauch_green_kart_browser_with_url(String url) {
        GreenKartPaymentPage.using(context).launchApplication(url);
    }

    @When("Add vegetables To Cart")
    public void add_vegetables_to_cart(DataTable dataTable) {
        GreenKartPaymentPage.using(context).addItemsToCart(dataTable.asList());
    }

    @When("Click on Proceed To checkout")
    public void click_on_proceed_to_checkout() {
        GreenKartPaymentPage.using(context).checkOut();
    }

    @When("Validate the Items selected")
    public void validate_the_items_selected() {
        GreenKartPaymentPage.using(context).itemsAdded();
    }

    @When("Apply Promo code {string}")
    public void apply_promo_code(String string) {
        GreenKartPaymentPage.using(context).appyCoupen();

    }

    @Then("Proceed with Payment")
    public void proceed_with_payment() {
        GreenKartPaymentPage.using(context).paymentprocess();
    }
}
