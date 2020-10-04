package emailApp.Controller;

import emailApp.EmailManager;
import emailApp.view.ViewFactory;

public abstract class BaseController {
    protected ViewFactory viewFactory;
    protected EmailManager emailManager;
    private String fxmlName;
    //Fields used by classes that extend this class.

    public BaseController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
        this.viewFactory = viewFactory;
        this.emailManager = emailManager;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
