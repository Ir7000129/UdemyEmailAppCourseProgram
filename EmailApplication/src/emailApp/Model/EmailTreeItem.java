package emailApp.Model;

import javafx.scene.control.TreeItem;

@SuppressWarnings("hiding")
public class EmailTreeItem<String> extends TreeItem<String> {

	private String name;
	
	public EmailTreeItem(String name) {
		super();
		this.name = name;
	}
	
	
}
