package emailApp;

import emailApp.Model.EmailAccount;
import emailApp.Model.EmailTreeItem;
import emailApp.Services.FetchFoldersService;


public class EmailManager {
	//folder handling 
	private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");

	public EmailTreeItem<String> getFoldersRoot() {
		return foldersRoot;
	}
	
	public void addEmailAccount(EmailAccount emailAccount) {
		var treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
		var fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
		fetchFoldersService.start();
		foldersRoot.getChildren().add(treeItem);
	}
}
