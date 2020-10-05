package emailApp.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import emailApp.EmailManager;
import emailApp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainMenuController extends BaseController implements Initializable{

    @FXML
    private TreeView<String> emailsTreeView;

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpEmailsTreeView();
		
	}
	private void setUpEmailsTreeView() {
		emailsTreeView.setRoot(emailManager.getFoldersRoot());
		emailsTreeView.setShowRoot(false);
		
	}

}
