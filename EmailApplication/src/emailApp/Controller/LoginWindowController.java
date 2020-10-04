package emailApp.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import emailApp.EmailManager;
import emailApp.Model.EmailAccount;
import emailApp.Services.LoginService;
import emailApp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends BaseController implements Initializable{

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorHandler;

    public LoginWindowController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
        super(viewFactory, emailManager, fxmlName);
    }

    @FXML
    void LoginAction() {
    	System.out.println("loginStarted");
    	if(fieldsAreValid()) {
    		var emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
    		var loginService = new LoginService(emailAccount, emailManager);
    		loginService.start();
    		loginService.setOnSucceeded(event -> {
    			EmailLoginResult emailLoginResult = loginService.getValue();
        		
        		switch (emailLoginResult) {
        		case SUCCESS:
        			System.out.println("Login Successful" + emailAccount + "\n" + "Main Window Running");
        			
                    viewFactory.showMainWindow();
                    viewFactory.updateStyles();
                    Stage stage = (Stage) errorHandler.getScene().getWindow();
                    viewFactory.closeShowingStage(stage); 
                    	return;
        		case FAILED_BY_CREDENTIALS:
        			errorHandler.setText("invalid credentials");
        			return;
        		case FAILED_BY_UNEXPECTED_ERROR:
        			errorHandler.setText("unexpected error occured");
        			return;
        		default:
        			return;
        		}
    		});
    	}	    	       
    }

	private boolean fieldsAreValid() {
		if(emailAddressField.getText().isEmpty() && passwordField.getText().isEmpty()) {
			errorHandler.setText("Please Fill In Credentials");
			System.out.println("Error: fields empty");
			return false;
		} 
		if(emailAddressField.getText().isEmpty()) {
			errorHandler.setText("Please Fill Email Address");
			System.out.println("Error: EmailAddress field empty");
			return false;
		}
		if(passwordField.getText().isEmpty()) {
			errorHandler.setText("Please Enter Password");
			System.out.println("Error: Password field empty");
			return false;
		}
		errorHandler.setText("");
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		emailAddressField.setText("appjack42@gmail.com");
		passwordField.setText("Freak3938");
		
	}
}