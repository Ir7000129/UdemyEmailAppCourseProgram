package emailApp.Controller.Service;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.Transport;

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
			protected EmailSendingResult call() {
				try {
					//create the message
					MimeMessage mimeMessage = new MimeMessage(emailAccount.getSession());
					mimeMessage.setFrom(emailAccount.getAddress());
					mimeMessage.addRecipients(Message.RecipientType.TO, receipient);
					mimeMessage.setSubject(subject);
					//set the content
					Multipart multiPart = new MimeMultipart();
					BodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setContent(content, "Text/HTML");
					multiPart.addBodyPart(messageBodyPart);
					mimeMessage.setContent(multiPart);
					//send message
					Transport transport = emailAccount.getSession().getTransport();
					transport.connect(
							emailAccount.getProperties().getProperty("OutgoingHost"),
							emailAccount.getAddress(),
							emailAccount.getPassword()
							);
					transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
					return EmailSendingResult.FAILED_BY_NETWORK_ERROR;
				}
				catch (Exception e) {
					e.printStackTrace();
					return EmailSendingResult.FAILED_BY_UNEXPECTED_ERROR;
				}
				return EmailSendingResult.SUCCESS;
			}
		};
	}

}
