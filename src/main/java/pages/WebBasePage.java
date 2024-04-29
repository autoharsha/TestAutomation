package pages;

import testcontext.TestingContext;
import webpageactions.*;

public abstract class WebBasePage extends BasePage {
    public WebBasePage(TestingContext context) {
        super(context);
    }

    private ElementHandler element;
    private ActionHandler actionHandler;
    private WindowHandler windowHandler;
    private PopUpHandler popUpHandler;
    private JavaScriptHandler javaScriptHandler;

    public ElementHandler getElement() {
        return element;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public WindowHandler getWindowHandler() {
        return windowHandler;
    }

    public PopUpHandler getPopUpHandler() {
        return popUpHandler;
    }

    public JavaScriptHandler getJavaScriptHandler() {
        return javaScriptHandler;
    }

    @Override
    protected void setupLocalProperties() {
        element = new ElementHandler(testingContext);
        actionHandler = new ActionHandler(testingContext);
        windowHandler = new WindowHandler(testingContext);
        popUpHandler = new PopUpHandler(testingContext);
        javaScriptHandler = new JavaScriptHandler(testingContext);
    }

}
