package emailApp.Controller.Service;

import emailApp.Model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

public class FetchFoldersService extends Service {

    private Store store;
    private EmailTreeItem<String> foldersRoot;
    private List<Folder> foldersList;
    private static int  NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE = 0;


 
    public FetchFoldersService(Store store, EmailTreeItem<String> foldersRoot, List<Folder> foldersList) {
        this.store = store;
        this.foldersRoot = foldersRoot;
        this.foldersList = foldersList;
        this.setOnSucceeded(e -> NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE--);
    }

    public static boolean noServicesActive() {
    	return NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE == 0;
//    		return true;
//    	} else return false;
    }
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                fetchFolders();
                return null;	
            }
        };
    }

    private void fetchFolders() throws MessagingException {
        Folder[] folders = store.getDefaultFolder().list();
        handleFolders(folders, foldersRoot);
        NUMBER_OF_FETCHFOLDERSERVICES_ACTIVE++;
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> foldersRoot) throws MessagingException {
        for(Folder folder: folders){
            foldersList.add(folder);
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            foldersRoot.getChildren().add((emailTreeItem));
            	 foldersRoot.setExpanded(true);
            	 getMessage(folder, emailTreeItem);
            	 addMessageListenerToFolder(folder,emailTreeItem);
            if (folder.getType() == folder.HOLDS_FOLDERS) {
            	Folder[] subFolders = folder.list();
            	handleFolders(subFolders, emailTreeItem);
            }
        }

    }

    private void addMessageListenerToFolder(Folder folder, EmailTreeItem<String> emailTreeItem) {
        folder.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for (int i = 0; i < e.getMessages().length; i++){
                    try {
                        Message message = folder.getMessage(folder.getMessageCount() - i);
                        emailTreeItem.addEmailToTop(message);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void messagesRemoved(MessageCountEvent e) {
                System.out.println("message removed event!!!: " + e);
            }
        });
    }

	private void getMessage(Folder folder, EmailTreeItem<String> emailTreeItem) {
		Service getMessageService = new Service() {
			@Override
			protected Task createTask() {
				return new Task() {
				@Override
				protected Object call() throws MessagingException {
					if(folder.getType() != folder.HOLDS_FOLDERS) {
						folder.open(folder.READ_WRITE);
						int messageSize = folder.getMessageCount();
						for(int i = messageSize; i > 0; i--) {
							emailTreeItem.addEmail(folder.getMessage(i));
						}
					}
					return null;
				}						
			};
		}
	};
	getMessageService.start();
}
	
}

