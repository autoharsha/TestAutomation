package webpageactions;

import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testcontext.TestingContext;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
public class WindowHandler extends BaseHandler {

    WebDriver currentDriver = null;

    public WindowHandler(TestingContext context) {
        super(context.getDriver());
        currentDriver = context.getDriver();
    }

    public String getMainWindowHanle() {
        String mainWindowHandle = driver.getWindowHandle();
        return mainWindowHandle;
    }

    public WebDriver switchToLatestWindow(WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
            log.info("Switching to window : " + driver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to latest window.\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to latest window.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public Set<String> getWindowHandles(WebElement... waitForElement) {
        Set<String> handles = null;
        try {
            handles = driver.getWindowHandles();
            log.info("No. of window handles: " + handles.size());
        } catch (WebDriverException e) {
            log.error("Unable to get the wiondow handles.\n" + e.getMessage());
            throw new WebDriverException("Unable to get the wiondow handles.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return handles;
    }

    public WebDriver switchToWindow(String name, WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().window(name);
            log.info("Switching to window : " + driver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to " + name + " window.\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to " + name + " window.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public void closeWindow(String windowHandle, WebElement... waitForElement) {
        WebDriver local = null;
        try {
            local = switchToWindow(windowHandle);
            log.info("Switching to window : " + driver.getTitle());
            if (local != null) {
                local.close();
            }
        } catch (WebDriverException e) {
            log.error("Unable to close the  " + windowHandle + " window.\n" + e.getMessage());
            throw new WebDriverException("Unable to close the  " + windowHandle + " window.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }

    }

    public void cleanUp(WebDriver webDriver) {
        try {
            Iterator<String> handles = webDriver.getWindowHandles().iterator();
            while (handles.hasNext()) {
                String handle = handles.next();
                WebDriver local = switchToWindow(handle);
                if (local != null) {
                    local.close();
                }
            }
        } catch (WebDriverException e) {
            log.error("Unable to close all the windows.\n" + e.getMessage());
            throw new WebDriverException("Unable to close all the windows.\n" + e);
        }
    }

    public WebDriver getCurrentWindow() {
        return currentDriver;
    }

    public String getParentWindow() {
        String parentWindow = null;
        try {
            parentWindow = driver.getWindowHandle();
        } catch (WebDriverException e) {
            log.error("Unable to get the parent window handle.\n" + e.getMessage());
            throw new WebDriverException("Unable to get the parent window handle.\n" + e);
        }
        return parentWindow;

    }

    public void switchToParentWindow(String parentWindow, WebElement... waitForElement) {
        switchToWindow(parentWindow, waitForElement);
    }

    public WebElement switchToModalDialog(WebElement... waitForElement) {
        WebElement activeElement = null;
        try {
            activeElement = driver.switchTo().activeElement();
            log.info("Switching to active element : " + activeElement.getText());
        } catch (WebDriverException e) {
            log.error("Unable to switch to active modal dialog active element.\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to active modal dialog active element.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return activeElement;
    }

    public WebDriver switchToFrame(int index, WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().frame(index);
            log.info("Switching to frame : " + driver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame index: " + index + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame index: " + index + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver switchToMainFrame(WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().defaultContent();
            log.info("Switching to main window");
        } catch (WebDriverException e) {
            log.error("Unable to switch to main/default frame.\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to main/default frame.\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver switchToFrame(WebElement element, WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().frame(element);
            log.info("Switching to frame : " + driver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame element: " + element + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame element: " + element + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver switchToFrame(String idOrName, WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().frame(idOrName);
            log.info("Switching to frame : " + driver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame id/name: " + idOrName + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame id/name: " + idOrName + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver switchToParentFrame(WebElement... waitForElement) {
        try {
            currentDriver = driver.switchTo().parentFrame();
            log.info("Switching to parent frame : " + currentDriver.getTitle());
        } catch (WebDriverException e) {
            log.error("Unable to switch to parent frame" + e.getMessage());
            throw new WebDriverException("Unable to switch to parent frame" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public String[] listFrames() {
        String[] frames = null;
        try {
            final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            int size = iframes.size();

            for (int i = 0; i < size; i++) {
                frames = new String[size];
                String id = iframes.get(i).getAttribute("id");
                if (!id.equals(null) && id.length() > 0) {
                    frames[i] = iframes.get(i).getAttribute("id");
                }
            }
        } catch (WebDriverException e) {
            log.error("Unable to get the list of frames" + e.getMessage());
            throw new WebDriverException("Unable to get the list of frames" + e);
        }
        return frames;
    }

    public WebDriver waitForFrameAndSwitchIt(int index, WebElement... waitForElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame index: " + index + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame index: " + index + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver waitForFrameAndSwitchIt(String idOrName, WebElement... waitForElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame id/name: " + idOrName + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame id/name: " + idOrName + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }

    public WebDriver waitForFrameAndSwitchIt(WebElement element, WebElement... waitForElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        } catch (WebDriverException e) {
            log.error("Unable to switch to frame by using frame element: " + element + ".\n" + e.getMessage());
            throw new WebDriverException("Unable to switch to frame by using frame element: " + element + ".\n" + e);
        }
        if (waitForElement != null && waitForElement.length > 0) {
            setWebDriverWait(waitForElement[0]);
        }
        return driver;
    }
}