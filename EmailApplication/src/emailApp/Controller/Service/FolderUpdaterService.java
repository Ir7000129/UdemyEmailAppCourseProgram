package emailApp.Controller.Service;

import java.util.List;

import javax.mail.Folder;
import javax.mail.MessagingException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderUpdaterService extends Service {

	private List<Folder> foldersList;
	
	public FolderUpdaterService(List<Folder> foldersList) {
		super();
		this.foldersList = foldersList;
	}

	@Override
	protected Task createTask() {
		return new Task() {
			@Override
			protected Object call() throws MessagingException {
				while(true) {
					try {
						Thread.sleep(5000);
						for (Folder folder: foldersList) {
							if (folder.getType() != folder.HOLDS_FOLDERS && folder.isOpen()) {
								folder.getMessageCount();
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		};
	}

}
