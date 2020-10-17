package emailApp.Model;

import javafx.scene.control.TreeItem;

public class EmailTreeItem<T> extends TreeItem<T> {

	private String name;
	
	public EmailTreeItem(String name) {
		super();
		this.name = name;
	}
	
	
}
