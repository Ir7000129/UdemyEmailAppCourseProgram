package emailApp.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import emailApp.EmailManager;
import emailApp.Controller.Service.EmailSenderService;
import emailApp.Model.EmailAccount;
import emailApp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class ComposeWindowController extends BaseController implements Initializable {
	
	    public ComposeWindowController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
		super(viewFactory, emailManager, fxmlName);
	}

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

	    @FXML
	    void sendButtonAction() {
	    	EmailSenderService emailSenderService = new EmailSenderService(
	    			senderChoiceBox.getValue(), 
	    			subjectTextBox.getText(), 
	    			ReceipientTextBox.getText(), 
	    			htmlEditor.getHtmlText());
	    	
	    	emailSenderService.start();
	    	emailSenderService.setOnSucceeded(e -> {
	    		EmailSendingResult emailSendingResult = emailSenderService.getValue();
	    		switch (emailSendingResult) {
				case SUCCESS: {
					Stage stage = (Stage)errorLabel.getScene().getWindow();
					viewFactory.closeShowingStage(stage);
					break;
				}
				case FAILED_BY_UNEXPECTED_ERROR{
					break;					
				}
				case FAILED_BY_NETWORK_ERROR{
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + emailSendingResult);
				}
	    	});
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
