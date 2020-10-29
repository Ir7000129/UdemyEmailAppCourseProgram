package emailApp.Controller.Service;

import emailApp.Controller.EmailSendingResult;
import emailApp.Model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmailSenderService extends Service<EmailSendingResult>{
	
	private EmailAccount emailAccount;
	private String subject;
	private String receipient;
	private String content;
	
	public EmailSenderService(EmailAccount emailAccount, String subject, String receipient, String content) {
		this.emailAccount = emailAccount;
		this.subject = subject;
		this.receipient = receipient;
		this.content = content;
	}

	@Override
	protected Task<EmailSendingResult> createTask() {
		return new Task<EmailSendingResult>() {
			
			@Override
			protected EmailSendingResult call() throws Exception {
  				return null;
			}
		};
	}

}
