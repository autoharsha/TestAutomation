package webpageactions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseHandler {

    //private Logger logger = LogManager.getLogger(this.getClass());

    protected WebDriver driver;
    protected static long config_wait_timeout = 0L;
    protected static long config_verify_interval = 0L;
    WebDriverWait wait;

    public BaseHandler(WebDriver driver) {
        this.driver = driver;
        setupConfigValue();
    }

    private void setupConfigValue() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void setWebDriverWait(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is not visible \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is not visible \n " + e);
        }
    }

    public void setWebDriverWaitList(List<WebElement> listOfElement) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(listOfElement));
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(listOfElement.get(0))));
        } catch (WebDriverException e) {
            //logger.error("Element " + listOfElement + " is not visible \n " + e.getMessage());
            throw new WebDriverException("Element " + listOfElement + " is not visible \n " + e);
        }

    }

    protected void waitForElementTobeLoaded(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));

    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void waitforinvisibllityofelement(WebElement element) {
        try {

            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is visible \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is visible \n " + e);
        }
    }

    public void waitforinvisiblelityofallelements(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } catch (WebDriverException e) {
            //logger.error("Element " + elements + " is visible \n " + e.getMessage());
            throw new WebDriverException("Element " + elements + " is visible \n " + e);
        }
    }

    public void waitForElementTextTobePresentTextbox(WebElement element) {
        (new WebDriverWait(driver, Duration.ofSeconds(30)))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver webDriver) {
                        return element.getAttribute("value").length() != 0;
                    }
                });
    }

    public String waitForTextToBePresentInElement(WebElement element, int maxTimeout) {
        int timeout = 0;
        String returnText = null;
        while (timeout != maxTimeout) {
            try {
                driver.navigate().refresh();
                returnText = element.getText().trim();
                if (!returnText.isEmpty()) {
                    break;
                } else {
                    timeout = timeout + 2;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }
        }
        return returnText;
    }

    public void waitForInVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementTobeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForXpathToLoad(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitForXpathToLoadUntilElementsClickable(WebElement xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    public void waitForXpathToLoadUntillElementsSelected(WebElement xpath) {
        wait.until(ExpectedConditions.elementToBeSelected(xpath));
    }

    public void waitForXpathToLoadUntilVisibilityOfElement(WebElement xpath) {
        wait.until(ExpectedConditions.visibilityOf(xpath));
    }

    public void waitForClassNameToLoad(String className) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
    }

    public void waitForClassNameToLoad(WebElement className) {
        wait.until(ExpectedConditions.visibilityOf(className));
    }

    public void waitForPartialLinkText(String partialLinkText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(partialLinkText)));
    }

    public void waitForIdToLoad(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public void waitForIdToLoad(WebElement id) {
        wait.until(ExpectedConditions.visibilityOf(id));
    }

    public void waitForNameToLoad(String name) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));

    }

    public void waitForLinkTextToLoad(String linkText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
    }

    public void waitForCssSelecterToLoad(String cssSelector) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    public void waitForPageToLoad() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

    }

    public void refereshWebPage() {
        waitForPageToLoad();
        driver.navigate().refresh();
    }

    public void waitForTileToLoad(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.titleContains(pageTitle));

    }
}
