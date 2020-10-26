package emailApp;

import emailApp.Model.EmailAccount;
import emailApp.Model.EmailMessage;
import emailApp.Model.EmailTreeItem;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;
import javax.mail.Folder;

import emailApp.Controller.Service.FetchFoldersService;
import emailApp.Controller.Service.FolderUpdaterService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class EmailManager {

    private FolderUpdaterService folderUpdaterService;
    
    //Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");
    private EmailMessage selectedMessage;
    private EmailTreeItem<String> selectedFolder;
    private ObservableList<EmailAccount> emailAccounts = FXCollections.observableArrayList();
    private List<Folder> folderList = new ArrayList<Folder>();

    public ObservableList<EmailAccount> getEmailAccounts(){
    	return emailAccounts;
    }
    
    public EmailMessage getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(EmailMessage selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public EmailTreeItem<String> getSelectedFolder() {
		return selectedFolder;
	}

	public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
		this.selectedFolder = selectedFolder;
	}

	public EmailTreeItem<String> getFoldersRoot(){
        return foldersRoot;
    }

    public  List<Folder> getFolderList(){
        return this.folderList;
    }

    public EmailManager(){
        folderUpdaterService = new FolderUpdaterService(folderList);
        folderUpdaterService.start();
    }

    public void addEmailAccount(EmailAccount emailAccount){
        emailAccounts.add(emailAccount);
    	EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, folderList);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }

	public void setRead() {
		try {
			selectedMessage.setIsRead(true);
			selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
			selectedFolder.decrementMessagesCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUnread() {
		try {
			selectedMessage.setIsRead(false);
			selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, false);
			selectedFolder.incrementMessagesCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMessage() {
		try {
			selectedMessage.getMessage().setFlag(Flags.Flag.DELETED, true);
            selectedFolder.getEmailMessages().remove(selectedMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
