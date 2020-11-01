package emailApp.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconResolver {
	
	public Node getIconFolder(String getFolder) {
		String lowerCaseFolderName = getFolder.toLowerCase();
		ImageView imageView = null;
		try {
			if(lowerCaseFolderName.contains("@")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/email.png")));
			} else if(lowerCaseFolderName.contains("inbox")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/inbox.png")));
			} else if(lowerCaseFolderName.contains("all mail")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/folder.png")));
			}else if(lowerCaseFolderName.contains("bin")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/folder.png")));
			}else if(lowerCaseFolderName.contains("drafts")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/folder.png")));
			}else if(lowerCaseFolderName.contains("important")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/folder.png")));
			}else if(lowerCaseFolderName.contains("sent mail")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/sent1.png")));
			}else if(lowerCaseFolderName.contains("spam")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/spam.png")));
			} else if(lowerCaseFolderName.contains("gmail")) {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/spam.png")));
			} else {
				imageView = new ImageView(new Image(getClass().getResourceAsStream("Icons/folder.png")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageView;		
	}

}
