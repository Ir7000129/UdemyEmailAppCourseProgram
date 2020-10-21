package emailApp.Controller.Service;

import emailApp.Model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;

import java.util.List;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

public class FetchFoldersService extends Service<Void> {

    private Store store;
    private EmailTreeItem<String> foldersRoot;
    private List<Folder> foldersList;
    
    public FetchFoldersService(Store store, EmailTreeItem<String> foldersRoot, List<Folder> foldersList) {
        this.store = store;
        this.foldersRoot = foldersRoot;
        this.foldersList = foldersList;
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
    }

    private void handleFolders(Folder[] folders, EmailTreeItem<String> foldersRoot) throws MessagingException {
        for(Folder folder: folders){
            EmailTreeItem<String> emailTreeItem = new EmailTreeItem<String>(folder.getName());
            foldersRoot.getChildren().add((emailTreeItem));
            	 foldersRoot.setExpanded(true);
            	 getMessage(folder, emailTreeItem);
            	 addMessageListner(folder,emailTreeItem);
            if (folder.getType() == folder.HOLDS_FOLDERS) {
            	Folder[] subFolders = folder.list();
            	handleFolders(subFolders, emailTreeItem);
            }
            foldersList.add(folder);
        }

    }

	private void addMessageListner(Folder folder, EmailTreeItem<String> emailTreeItem) {
		folder.addMessageCountListener(new MessageCountListener() {

			@Override
			public void messagesAdded(MessageCountEvent e) {
                System.out.println("message added event!!!: " + e);

			}

			@Override
			public void messagesRemoved(MessageCountEvent arg0) {
				// TODO Auto-generated method stub
				
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

