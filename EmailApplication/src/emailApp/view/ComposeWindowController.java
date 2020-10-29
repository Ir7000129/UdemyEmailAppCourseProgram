package emailApp.view;

import java.net.URL;
import java.util.ResourceBundle;

import emailApp.EmailManager;
import emailApp.Controller.BaseController;
import emailApp.Model.EmailAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class ComposeWindowController extends BaseController implements Initializable{


	@FXML
    private TextField ReceipientTextBox;

    @FXML
    private TextField subjectTextBox;

    @FXML
    private ChoiceBox<EmailAccount> senderChoiceBox;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private MenuBar composeMenuBar;

    @FXML
    private Label errorLabel;
    
	public ComposeWindowController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
		super(viewFactory, emailManager, fxmlName);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpSenderChoiceBox();
	}

	private void setUpSenderChoiceBox() {
		senderChoiceBox.setItems(emailManager.getEmailAccounts());
		senderChoiceBox.setValue(emailManager.getEmailAccounts().get(0));
	}
}
