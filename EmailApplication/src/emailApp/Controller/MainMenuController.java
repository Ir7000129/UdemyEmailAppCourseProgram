package emailApp.Controller;

import java.io.IOException;

import emailApp.EmailManager;
import emailApp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainMenuController extends BaseController {

    @FXML
    private TreeView<?> emailsTreeView;

    @FXML
    private TableView<?> emailsTableView;

    @FXML
    private WebView emailsWebView;

    public MainMenuController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
        super(viewFactory, emailManager, fxmlName);
    }
    @FXML
    void closeAction() {
        Stage stage = (Stage) emailsTableView.getScene().getWindow();
        viewFactory.closeShowingStage(stage);
    }

    @FXML
    void optionsAction() {
        viewFactory.showOptionsWindow();
    	viewFactory.updateStyles();

    }
    
    @FXML
    void addAccountAction() throws IOException {
    	viewFactory.showLoginWindow();
    	viewFactory.updateStyles();
    }

}
