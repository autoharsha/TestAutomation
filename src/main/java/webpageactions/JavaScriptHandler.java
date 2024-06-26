package webpageactions;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import testcontext.TestingContext;

@Slf4j
public class JavaScriptHandler extends BaseHandler {

    public JavaScriptHandler(TestingContext context) {
        super(context.getDriver());
    }

    public void scrollIntoView(WebElement element, WebElement... waitForElement) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
        javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);

        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void javaScriptClick(WebElement element, WebElement... waitForElement) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
        javaScriptExecutor.executeScript("arguments[0].click();", element);

        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void scrollToElementAndClick(WebElement element, WebElement... waitForElement) {
        try {
            if (element != null) {
                JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
                javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
                javaScriptExecutor.executeScript("arguments[0].click();", element);
            }
        } catch (WebDriverException e) {
            log.error("Unable to enter text on the element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to enter text on the element: " + element + "\n " + e);
        }

        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void mouseHoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {

                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);

            } else {
                System.out.println("Element was not visible to hover " + "\n");

            }
        } catch (StaleElementReferenceException e) {
            System.out.println(
                    "Element with " + HoverElement + "is not attached to the page document" + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + HoverElement + " was not found in DOM" + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while hovering" + e.getStackTrace());
        }
    }

    private boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed() || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        } catch (StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: red; border: 5px solid yellow;");

    }
}
