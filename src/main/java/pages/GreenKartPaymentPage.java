package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testcontext.TestingContext;

import java.util.List;
import java.util.stream.Collectors;

public class GreenKartPaymentPage extends WebBasePage {
    @FindBy(xpath = "//div[@class='product-action']/button")
    private List<WebElement> btnAdd;
    @FindBy(css = "h4.product-name")
    private List<WebElement> productList;
    @FindBy(xpath = "//a[@class='cart-icon']/img")
    private WebElement imgCart;
    @FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")
    private WebElement btncheckout;
    @FindBy(xpath = "//input[@class='promoCode']")
    private WebElement txtPromo;
    @FindBy(css = ".promoBtn")
    private WebElement btnPromo;
    @FindBy(css = "span.promoInfo")
    private WebElement promoMesg;
    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement btnPlaceOrder;
    @FindBy(css = ".product-name p")
    private List<WebElement> colProductName;
    @FindBy(css = ".products select")
    private WebElement ddpChooseCountry;
    @FindBy(xpath = "//input[@class='chkAgree']")
    private WebElement chkTandC;
    @FindBy(xpath = "//button[text()='Proceed']")
    private WebElement btnProceed;

    @Override

    protected String getPageName() {
        return "GreenKart";
    }

    @Override
    protected String getPageDescription() {
        return "Payment Process";
    }

    private GreenKartPaymentPage(TestingContext context) {
        super(context);
    }

    public static GreenKartPaymentPage using(TestingContext context) {
        return new GreenKartPaymentPage(context);
    }

    public void launchApplication(String url) {
        testingContext.getDriver().get(url);
    }

    public void addItemsToCart(List<String> items) {
        int j = 0;
        for (int i = 0; i < productList.size(); i++) {
            //Split and trim for string operations
            String[] currentvalue = productList.get(i).getText().split("-");
            if (items.contains(currentvalue[0].trim())) {
                getActionHandler().moveToElementAndClick(btnAdd.get(i));
                if (j == items.size()) {
                    break;
                }
                j++;
            }
        }
    }

    public void checkOut() {
        getElement().clickElement(imgCart, btncheckout);
        getElement().clickElement(btncheckout);
    }

    public List<String> itemsAdded() {
        return colProductName.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void appyCoupen() {
        getElement().writeText(txtPromo, "rahulshettyacademy");
        getElement().clickElement(btnPromo, promoMesg);
    }

    public void paymentprocess() {
        getElement().clickElement(btnPlaceOrder);
        getElement().selectByIndex(ddpChooseCountry, 5);
        getElement().clickElement(btnProceed);
    }
}
