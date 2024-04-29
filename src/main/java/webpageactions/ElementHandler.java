package webpageactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import testcontext.TestingContext;

import java.util.ArrayList;
import java.util.List;

public class ElementHandler extends BaseHandler {

    // private //logger //logger = LogManager.get//logger(this.getClass());
    private TestingContext testingContext;

    public ElementHandler(TestingContext context) {
        super(context.getDriver());
        testingContext = context;
    }

    /***
     * Method is used to click on the element
     *
     * @param element        Element which needs to be clicked
     * @param waitForElement Wait for Element to be present after the click action
     */
    public void clickElement(WebElement element, WebElement... waitForElement) {
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                waitForElementTobeClickable(element);
                element.click();
            }
        } catch (WebDriverException e) {
            ////logger.error("Unable to click on the element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to click on the element: " + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void jsClickElement(WebElement element, WebElement... waitForElement) {
        try {
            if (element != null) {
                JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
                javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
                javaScriptExecutor.executeScript("arguments[0].click();", element);
            }
        } catch (WebDriverException e) {
            ////logger.error("Unable to enter text on the element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to enter text on the element: " + element + "\n " + e);
        }

        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    /***
     * Method is used to get the value from css attribute/property
     *
     * @param element      from which you gets the css property value
     * @param propertyName the css property name of the element
     * @return The current, computed value of the property.
     */
    public String getCssValue(WebElement element, String propertyName) {
        String cssValue = null;
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                cssValue = element.getCssValue(propertyName);
            }
        } catch (Exception e) {
            ////logger.error("Unable to get the css value from the element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get the css vlaue from the element: " + element + "\n " + e);
        }
        return cssValue;
    }

    /***
     * Method is used to enter text into element
     *
     * @param element        which you are going to enter the text
     * @param text           character sequence to send to the element
     * @param waitForElement Wait for Element to be present after entering text.
     */
    public void writeText(WebElement element, String text, WebElement... waitForElement) {
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                element.clear();
                element.sendKeys(text);

            }
        } catch (WebDriverException e) {
            //logger.error("Unable to enter text on the element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to enter text on the element: " + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    /***
     * Method is used to get the visible (i.e. not hidden by CSS) innerText of
     * element, including sub-elements, without any leading or trailing whitespace
     *
     * @param element From which you gets the text
     * @return The innerText of this element
     */
    public String getText(WebElement element) {
        String text = null;
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                text = element.getText();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the text from element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get the text from element: " + element + "\n " + e);
        }
        return text.trim();
    }

    /***
     * Method is used to get the visible (i.e. not hidden by CSS) innerText of
     * element, including sub-elements, without any leading or trailing whitespace
     *
     * @param element From which you gets the text
     * @return The innerText of this element
     */
    public List<String> getText(List<WebElement> element) {
        List<String> text = new ArrayList<String>();
        for (int i = 0; i < element.size(); i++) {
            try {
                if (element != null) {
                    waitForElementTobeLoaded(element.get(i));
                    text.add(element.get(i).getText().trim());
                }
            } catch (WebDriverException e) {
                //logger.error("Unable to get the text from element: " + element + "\n " + e.getMessage());
                throw new WebDriverException("Unable to get the text from element: " + element + "\n " + e);
            }
        }
        return text;
    }

    /***
     * Method is used to get the value of the given attribute of the element. Will
     * return the current value, even if this has been modified after the page has
     * been loaded.
     *
     * @param element The element from which you gets the attribute value.
     * @return The attribute/property's current value or null if the value is not
     *         set.
     */
    public String getTextFromValueAttribute(WebElement element) {
        String text = null;
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                text = element.getAttribute("value");
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to locate the element \n " + e.getMessage());
            throw new WebDriverException("Unable to locate the element \n " + e);
        }
        return text.trim();
    }

    /***
     * Method is used to get the value of the given attribute of the element. Will
     * return the current value, even if this has been modified after the page has
     * been loaded.
     *
     * @param element   The element from which you gets the attribute value
     * @param attribute The name of the attribute
     * @return The attribute/property's current value or null if the value is not
     *         set.
     */
    public String getTextFromAttribute(WebElement element, String attribute) {
        String text = null;
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                text = element.getAttribute(attribute);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the text from " + attribute + " attribute of an element: " + element + "\n "
            //+e.getMessage());
            throw new WebDriverException(
                    "Unable to get the text from " + attribute + " attribute of an element: " + element + "\n " + e);
        }
        return text.trim();
    }

    /**
     * Method is used to get dropdown. If it is not, then an
     * UnexpectedTagNameException is thrown.
     *
     * @param element SELECT element to wrap
     * @return
     * @throws UnexpectedTagNameException when element is not a SELECT
     */
    public Select getDropdown(WebElement element) {
        Select dropDown = null;
        try {
            if (element != null) {
                waitForElementTobeLoaded(element);
                dropDown = new Select(element);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the dropdwon element: " + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get the dropdwon element: " + element + "\n " + e);
        }
        return dropDown;
    }

    /***
     *
     * @param element
     * @return
     */
    public boolean isDropDownDisplayed(WebElement element) {
        boolean isTrue = false;
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Dropdown is not displayed \n " + e.getMessage());
            throw new WebDriverException("Dropdown is not displayed \n " + e);
        }
        return isTrue;
    }

    public void selectByVisibleText(WebElement element, String textToSelect, WebElement... waitForElement) {
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                dropdown.selectByVisibleText(textToSelect);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to select value from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to select value from the dropdown:" + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void selectByValue(WebElement element, String value, WebElement... waitForElement) {
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                dropdown.selectByValue(value);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to select value from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to select value from the dropdown:" + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void selectByIndex(WebElement element, int index, WebElement... waitForElement) {
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                dropdown.selectByIndex(index);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to select value from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to select value from the dropdown:" + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public String getSelectedValueFromDropDown(WebElement element, WebElement... waitForElement) {
        String selectedValue = null;
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                selectedValue = dropdown.getFirstSelectedOption().getText();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the selected value from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get the selected value from the dropdown:" + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return selectedValue;
    }

    public void launchApplication(String url, WebElement... waitForElement) {
        try {
            driver.get(url);
            //logger.info("Url lanched is " + url);
        } catch (WebDriverException e) {
            //logger.error("Unable to launch the application url: " + url + "\n" + e.getMessage());
            throw new WebDriverException("Unable to launch the application url: " + url + "\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean isTrue = false;
        try {
            if (element.isDisplayed()) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is not displayed \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is not displayed \n " + e);
        }
        return isTrue;
    }

    public boolean isElementDisplayed(List<WebElement> element) {
        boolean isTrue = false;
        try {
            if (element.size() > 0) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is not displayed \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is not displayed \n " + e);
        }
        return isTrue;
    }

    public boolean isElementNotDisplayed(List<WebElement> element) {
        boolean isTrue = false;
        try {
            if (element.isEmpty()) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is displayed \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is displayed \n " + e);
        }
        return isTrue;
    }

    public boolean isElementNotDisplayed(WebElement element) {
        boolean isTrue = false;
        try {
            if (!element.isDisplayed()) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is displayed \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is displayed \n " + e);
        }
        return isTrue;
    }

    public boolean isElementVisible(WebElement element) {
        boolean isTrue = false;
        try {
            if (element.isEnabled()) {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is not enabled \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is not enabled \n " + e);
        }
        return isTrue;
    }

    public boolean isElementNotVisible(WebElement element) {
        boolean isTrue = false;
        try {
            boolean isEnabled = element.isEnabled();
            if (isEnabled) {
                isTrue = false;
            } else {
                isTrue = true;
            }
        } catch (WebDriverException e) {
            //logger.error("Element " + element + " is enabled \n " + e.getMessage());
            throw new WebDriverException("Element " + element + " is enabled \n " + e);
        }
        return isTrue;
    }

    public List<WebElement> getAllOptionsFromDropdown(WebElement element) {
        List<WebElement> allOptions = null;
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                allOptions = dropdown.getOptions();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get all the options from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get all the options from the dropdown:" + element + "\n " + e);
        }
        return allOptions;
    }

    public List<String> getAllOptionsValuesFromDropdown(WebElement element) {
        List<String> allOptionValues = new ArrayList<String>();
        List<WebElement> allOptions = getAllOptionsFromDropdown(element);
        try {
            for (WebElement webElement : allOptions) {
                allOptionValues.add(getText(webElement));
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get all the options from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get all the options from the dropdown:" + element + "\n " + e);
        }
        return allOptionValues;
    }

    /***
     * Select a last value from the drop down
     *
     * @param element dropdown element
     */
    public void selectLastValueFromDropDown(WebElement element) {
        List<WebElement> allOptions = null;
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                allOptions = dropdown.getOptions();
                dropdown.selectByIndex(allOptions.size() - 1);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get all the options from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get all the options from the dropdown:" + element + "\n " + e);
        }
    }

    public void setWebDriverWait(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (WebDriverException e) {
            //logger.error("Element " + elements + " is not visible \n " + e.getMessage());
            throw new WebDriverException("Element " + elements + " is not visible \n " + e);
        }

    }

    public void waitforElement(WebElement element) {
        int timeout = 0;
        while (timeout <= 30) {
            try {
                driver.navigate().refresh();
                if (element.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                timeout = timeout + 1;
            }
        }
    }

    public String getTextFromAttributeWithoutException(WebElement element, String attribute) {
        String text = null;
        try {
            if (element != null) {
                text = element.getAttribute(attribute);
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the text from " + attribute + " attribute of an element: " + element + "\n "
            //+e.getMessage());
        }
        return text.trim();
    }

    public void scrollDownBy100() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,105)");
    }

    public void scrollDownBy300Px() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-2000)");
    }

    // validating element attribute availability

    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    public String getSelectedValueFromDropDwon(WebElement element, WebElement... waitForElement) {
        String selectedValue = null;
        try {
            Select dropdown = getDropdown(element);
            if (dropdown != null) {
                selectedValue = dropdown.getFirstSelectedOption().getText();
            }
        } catch (WebDriverException e) {
            //logger.error("Unable to get the selected value from the dropdown:" + element + "\n " + e.getMessage());
            throw new WebDriverException("Unable to get the selected value from the dropdown:" + element + "\n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return selectedValue;
    }
}
