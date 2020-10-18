package emailApp.Model;

import java.util.Properties;
import javax.mail.Store;

public class EmailAccount {
	private String Address;
	private String password;
	private Properties properties;
	private Store store;
	
	public String getAddress() {
		return Address;
	}
	
	public EmailAccount(String address, String password) {
		this.Address = address;
		this.password = password;
		properties = new Properties();
		properties.put("incomingHost", "imap.gmail.com");
	    properties.put("mail.store.protocol", "imaps");

	    properties.put("mail.transport.protocol", "smtps");
	    properties.put("mail.smtps.host", "smtp.gmail.com");
	    properties.put("mail.smtps.auth", "true");
	    properties.put("outgoingHost", "smtp.gmail.com");
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	
	public String getPassword() {
		return password;
	}
	
	
}
