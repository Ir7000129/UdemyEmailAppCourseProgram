package emailApp;

import emailApp.Model.EmailAccount;
import emailApp.Model.EmailTreeItem;
import emailApp.Controller.Service.FetchFoldersService;
import javafx.scene.control.TreeItem;

public class EmailManager {

    //Folder handling:
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<String>("");

    public EmailTreeItem<String> getFoldersRoot(){
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<String>(emailAccount.getAddress());
//        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
//        fetchFoldersService.start();
        		treeItem.setExpanded(true);	
              treeItem.getChildren().add(new TreeItem<String>("INBOX"));	

        foldersRoot.getChildren().add(treeItem);
    }
}