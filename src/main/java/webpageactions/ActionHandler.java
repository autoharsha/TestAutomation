package webpageactions;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import testcontext.TestingContext;

import java.util.List;

@Slf4j
public class ActionHandler extends BaseHandler {

    private TestingContext testingContext;

    public ActionHandler(TestingContext context) {
        super(context.getDriver());
        this.testingContext = context;
    }

    /***
     * Method is used to performs double click on the specified element
     * @param element
     * 				 Element to be double click
     * @param waitForElement
     * 				 Element to be waiting for after the double click
     */
    public void doubleClick(WebElement element, WebElement... waitForElement) {
        try {
            Actions actions = new Actions(driver);
            if (element != null) {
                actions.doubleClick(element).build().perform();
            }
        } catch (WebDriverException e) {
            log.error("Unable to double click on the element: " + element + "\n" + e.getMessage());
            throw new WebDriverException("Unable to double click on the element: " + element + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }


    public void moveToElementsAndClick(List<WebElement> elements, WebElement... waitForElement) {
        try {
            if (elements.size() > 0) {
                for (WebElement element : elements) {
                    Actions actions = new Actions(driver);
                    if (element != null) {
                        actions.moveToElement(element).build().perform();
                        element.click();
                    }
                }
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to double click on the elements: " + elements + "\n" + e.getMessage());
            throw new WebDriverException("Unable to double click on the elements: " + elements + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    /***
     * Method is used to moved to the element specified and click on it
     * @param element
     *              Element to be moved and click on it
     * @param waitForElement
     *              Element to be waiting for after a click
     */
    public void moveToElementAndClick(WebElement element, WebElement... waitForElement) {
        try {
            if (element != null) {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).build().perform();
                element.click();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to move to element and click on the element: " + element + "\n" + e.getMessage());
            throw new WebDriverException("Unable to move to element and click on the element: " + element + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void moveToElement(WebElement element, WebElement... waitForElement) {
        try {
            if (element != null) {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).build().perform();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to move to element: " + element + "\n" + e.getMessage());

            throw new WebDriverException("Unable to move to element: " + element + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void keyboardAction(Keys key, WebElement... waitForElement) {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(key).build().perform();
        } catch (WebDriverException e) {
            //logger.error("Unable do the " + key + " key actions \n" + e.getMessage());
            throw new WebDriverException("Unable do the " + key + " key actions \n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void waitForSomeTime(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void rightClick(WebElement element, WebElement... waitForElement) {
        try {
            Actions actions = new Actions(driver);
            if (element != null) {
                actions.contextClick(element).build().perform();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to right click on the element: " + element + "\n" + e.getMessage());
            throw new WebDriverException("Unable to right click on the element: " + element + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }

    }

    public void moveToElementandSendkeys(WebElement element, String keys, WebElement... waitForElement) {
        try {
            if (element != null) {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().sendKeys(keys).build().perform();
                //actions.sendKeys(Keys.chord(Keys.SHIFT + "w")).build().perform();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to move to element: " + element + "\n" + e.getMessage());

            throw new WebDriverException("Unable to move to element: " + element + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void sendKeys(String textToEnter, WebElement... waitForElement) {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(textToEnter).build().perform();
        } catch (WebDriverException e) {
            //logger.error("Unable to enter text on the element: " + "\n " + e.getMessage());
            throw new WebDriverException("Unable to enter text on the element: " + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }
}
