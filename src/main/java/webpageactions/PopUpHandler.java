package webpageactions;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testcontext.TestingContext;

import java.time.Duration;

@Slf4j
public class PopUpHandler extends BaseHandler {

    public PopUpHandler(TestingContext context) {
        super(context.getDriver());
    }

    public boolean isAlertPresent() {
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }
        return foundAlert;
    }

    public Alert switchToAlert(WebElement... waitForElement) {
        Alert alert = null;
        try {
            driver.switchTo().defaultContent();
            alert = null;
            if (isAlertPresent()) {
                alert = driver.switchTo().alert();
                log.info("Switching to Alert........");
            }
        } catch (WebDriverException e) {
            log.error("Unable to switch to the alert.\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to the alert.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return alert;

    }

    public void acceptAlert(WebElement... waitForElement) {
        try {
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            }
        } catch (WebDriverException e) {
            log.error("Unable accept the alert \n " + e.getMessage());
            throw new WebDriverException("Unable accept the alert \n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void dismissAlert(WebElement... waitForElement) {
        try {
            driver.switchTo().defaultContent();
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                alert.dismiss();
            }
        } catch (WebDriverException e) {
            log.error("Unable dismiss the alert \n " + e.getMessage());
            throw new WebDriverException("Unable dismiss the alert \n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public String getAlertText(WebElement... waitForElement) {
        String alertText = null;
        try {
            /* driver.switchTo().defaultContent(); */
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                log.info("Switching to alert");
                alertText = alert.getText();
            }

        } catch (WebDriverException e) {
            log.error("Unable get the text from an alert \n " + e.getMessage());
            throw new WebDriverException("Unable get the text from an alert \n " + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return alertText;
    }

    public void loginWithoutPopup(String urlWithoutHTTPS, String userName, String password,
                                  WebElement... waitForElement) {
        try {
            String newURL = "https://" + userName + ":" + password + "@" + urlWithoutHTTPS;
            driver.get(newURL);
        } catch (WebDriverException e) {
            log.error("Unable top login to the application with windows authentication.\n" + e.getMessage());
            throw new WebDriverException("Unable top login to the application with windows authentication.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
    }

    public void logInToApplication(String username, String password, WebElement... waitForElement) {
		/*try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			Credentials credentials = new UserAndPassword(username, password);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.authenticateUsing(credentials);

		} catch (WebDriverException e) {
			log.error("Unable top login to the application with windows authentication.\n" + e.getMessage());
			throw new WebDriverException("Unable top login to the application with windows authentication.\n" + e);
		}
		if (waitForElement != null && waitForElement.length > 0) {
			setWebDriverWait(waitForElement[0]);
		}*/

    }
}
