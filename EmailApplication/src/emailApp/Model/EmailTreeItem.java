package emailApp.Model;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class EmailTreeItem<String> extends TreeItem<String> {

	private String name;
	private ObservableList<EmailMessage> emailMessages;
	private int unReadMessagesCount;
	
	public EmailTreeItem(String name) {
		super(name);
		this.name = name;
		this.emailMessages = FXCollections.observableArrayList();
	}	
	
	public ObservableList<EmailMessage> getEmailMessages() {
		return emailMessages;
	}
	
	public void addEmail(Message message) throws MessagingException {
		EmailMessage emailMessage = fetchEmails(message);
		emailMessages.add(emailMessage);
	}
		
	public void addEmailToTop(Message message) throws MessagingException {
		EmailMessage emailMessage = fetchEmails(message);
		emailMessages.add(0, emailMessage); 
	}

	private EmailMessage fetchEmails(Message message) throws MessagingException {
		boolean isMessageRead = message.getFlags().contains(Flags.Flag.SEEN);
		EmailMessage emailMessage = new EmailMessage(
					message.getSubject(),
					message.getFrom()[0].toString(),
					message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
					message.getSize(),
					message.getSentDate(),
					isMessageRead,
					message
				);		
		if (!isMessageRead) {
			incrementMessagesCount();
		}
		
		return emailMessage;
	}
	
	public void incrementMessagesCount() {
		unReadMessagesCount++;
		updateName();
	}
	
	public void decrementMessagesCount() {
		unReadMessagesCount--;
		updateName();
	}
	
	private void updateName() {
		if(unReadMessagesCount > 0) {
			this.setValue((String) (name + "(" + unReadMessagesCount + ")"));
		}else {
			this.setValue(name);
		}
	}

}