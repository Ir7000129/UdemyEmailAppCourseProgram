package emailApp.Controller.Service;

import java.util.List;

import javax.mail.Folder;
import javax.mail.MessagingException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class FolderUpdaterService extends Service {

	private List<Folder> foldersList;
	
	public FolderUpdaterService(List<Folder> foldersList) {
		this.foldersList = foldersList;
	}
		/*starting thread that runs every 5 seconds to retrieve email as they are recieved by gmail
		 *  
		 */
		 
		 @Override
		    protected Task createTask() {
		        return new Task() {
		            @Override
		            protected Object call() throws Exception {
		              while (true) {
		            	try {
//		            	 if (FetchFoldersService.noServicesActive()){
		                       Thread.sleep(5000);
		                       for (Folder folder: foldersList){
		                    	   if (folder.getType() != folder.HOLDS_FOLDERS && folder.isOpen()) {
										folder.getMessageCount();
//		                    	   }
		                       }
		                  	}
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
		    }
		};
	}
}
