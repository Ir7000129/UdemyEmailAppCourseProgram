package emailApp;

import emailApp.Model.EmailAccount;
import emailApp.Model.EmailTreeItem;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;

import emailApp.Controller.Service.FetchFoldersService;
import emailApp.Controller.Service.FolderListerService;
import javafx.scene.control.TreeItem;

public class EmailManager {

    //Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");
    private List<Folder> foldersList = new ArrayList<Folder>();
    private FolderListerService folderListerService;
    
    
    public List<Folder> getFoldersList() {return this.foldersList;
	}

	public EmailTreeItem<String> getFoldersRoot(){return foldersRoot;
    }
	
	public EmailManager() {
		folderListerService = new FolderListerService(foldersList);
		folderListerService.start();
	}

    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, foldersList);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }
}	
