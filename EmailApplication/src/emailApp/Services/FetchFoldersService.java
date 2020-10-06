package emailApp.Services;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

import emailApp.Model.EmailTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FetchFoldersService extends Service<Void>{
	
	private Store store;
	private EmailTreeItem<String> foldersRoot;
	
	public FetchFoldersService(Store store, EmailTreeItem<String> foldersRoot) {
		this.store = store;
		this.foldersRoot = foldersRoot;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception{
				FetchFolders();
				return null;
			}			
		};
	};
		private void FetchFolders() throws MessagingException {				
			Folder[] folders = store.getDefaultFolder().list();
			handleFolders(folders, foldersRoot);
		}
	
		private void handleFolders(Folder[] folders, EmailTreeItem<String> foldersRoot) {
			for (Folder folder:folders) {
				var emailTreeITem = new EmailTreeItem<String>(folder.getName());
				foldersRoot.getChildren().add(emailTreeITem);
			}
		}
}
