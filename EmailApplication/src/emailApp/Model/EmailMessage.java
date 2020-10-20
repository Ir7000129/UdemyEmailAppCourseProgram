package emailApp.Model;

import java.util.Date;
import javax.mail.Message;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmailMessage {	
	private SimpleStringProperty subject;
	private SimpleStringProperty sender;
	private SimpleStringProperty receipient;
	private SimpleIntegerProperty size;
	private SimpleObjectProperty<Date> date;
	private boolean isRead;
	private Message message;
	
	public EmailMessage(String subject, String sender, String receipient, int size, Date date, boolean isRead, Message message) {
		super();
		this.subject = new SimpleStringProperty(subject);
		this.sender = new SimpleStringProperty(sender);
		this.receipient = new SimpleStringProperty(receipient);
		this.size = new SimpleIntegerProperty(size);
		this.date = new SimpleObjectProperty<Date>(date);
		this.isRead = isRead;
		this.message = message;
	}
	
	public String getSubject() {
		return this.subject.get();
	}
	
	public String getSender() {
		return this.sender.get();
	}
	
	public SimpleStringProperty getReceipient() {
		return receipient;
	}
	
	public Integer getSize() {
		return this.size.get();
	}
	
	public Date getDate() {
		return this.date.get();
	}
	
	public boolean isRead() {
		return isRead;
	}
	
	public void setIsRead(boolean read) {
		isRead = read;
	}
	
	public Message getMessage() {
		return message;
	}
}
