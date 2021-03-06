package emailApp.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;


import emailApp.EmailManager;
import emailApp.Controller.Service.MessageRendererService;
import emailApp.Model.EmailMessage;
import emailApp.Model.EmailTreeItem;
import emailApp.view.ViewFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainMenuController extends BaseController implements Initializable{
	
	//fields use to create menu items for a context menu that has options to delete/ set unread
	private MenuItem unreadActionMenuItem = new MenuItem("Set as Unread");
	private MenuItem deleteActionMenuItem = new MenuItem("Delete Message");

    @FXML
    private TreeView<String> emailsTreeView;

    @FXML
    private TableView<EmailMessage> emailsTableView;

    @FXML
    private WebView emailsWebView;
    
    @FXML
    private AnchorPane detailsAnchor;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;
    
    @FXML
    private Label emptyLabel;

    @FXML
    private Label recipientLabel;
    
    @FXML
    private TableColumn<EmailMessage, String> senderCol;

    @FXML
    private TableColumn<EmailMessage, Date> dateCol;

    private MessageRendererService messageRendererService;
 
    public MainMenuController(ViewFactory viewFactory, EmailManager emailManager, String fxmlName) {
        super(viewFactory, emailManager, fxmlName);
    }
    @FXML
    void closeAction() {
        Stage stage = (Stage) emailsTableView.getScene().getWindow();
        viewFactory.closeShowingStage(stage);
    }
    
    @FXML
    void openComposeAction() {
    	viewFactory.showComposeWindow();
    	viewFactory.updateStyles();
    }

    @FXML
    void optionsAction() {
        viewFactory.showOptionsWindow();
    	viewFactory.updateStyles();
    }
    
    @FXML
    void addAccountAction() throws IOException {
    	viewFactory.showLoginWindow();
    	viewFactory.updateStyles();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpEmailsTreeView();
		setUpEmailsTableView();
		setUpFolderSelection();	
		setUpBoldRows();
		setUpMessageRendererService();
		setUpMessageSelection();
		setUpContextMenu();
	}
	
	private EmailMessage getSelectedMessage() {
		EmailMessage emailMessage = emailsTableView.getSelectionModel().getSelectedItem();
		return emailMessage;
	}
	
	private void setUpContextMenu() {
		unreadActionMenuItem.setOnAction(e -> emailManager.setUnread());
		deleteActionMenuItem.setOnAction(e -> {
			emailManager.deleteMessage();
			subjectLabel.setText("");
			recipientLabel.setText("");
			senderLabel.setText("");
			emailsWebView.getEngine().loadContent("");
		});
	}
	
	private void setUpMessageRendererService() {
		messageRendererService = new MessageRendererService(emailsWebView.getEngine()); 
	} 
	
	public static void main(String[] args) {
		java.util.Map<String, String > map = new HashMap<String, String>();
		map.put("a", "aaa");
		map.put("b", "asa");
		map.put("c", "cunt");

		
		  String a = map.get("a");
		  String b = map.get("b");
		  
		  if (a == b) {
		    map.remove("a");
		    map.remove("b");
		  }
		  System.out.println(map);
		  
		  Integer [] strings = new Integer[10];
		  double j = Math.random();
		  for(Integer string: strings) {
			  strings[strings.length -1] = (int) j; 
		  }
		  System.out.println(strings);
	}
	
	 private void setUpMessageSelection() {
	        emailsTableView.setOnMouseClicked(event -> {
	        	emailsWebView.getEngine().loadContent("");
	            EmailMessage emailMessage =	getSelectedMessage();
	            //letting emailManager know which message is currently selected	
	            emailManager.setSelectedMessage(emailMessage);
	            
	            recipientLabel.setText(emailMessage.getReceipient().get());
	            senderLabel.setText(emailMessage.getSender());
	            
	            if(emailMessage.getSubject() != null) {
		            subjectLabel.setText(emailMessage.getSubject());
	            } else {
	            	subjectLabel.setText("-(No Subject)-");
	            }
	            
	            emptyLabel.setVisible(false);
	            detailsAnchor.setVisible(true);
	            emailsWebView.setVisible(true);
	            
	            
	            //con
	            if(emailMessage != null){
	            	//updating app to change messages to read when opened
	            	if(!emailMessage.isRead()) {
	            		emailManager.setRead();
	            	}
	            	
	                messageRendererService.setEmailMessage(emailMessage);
	                messageRendererService.restart();
	            }
	        });
	        
	        
	    }
	
	private void setUpBoldRows() {
		emailsTableView.setRowFactory(new Callback<TableView<EmailMessage>, TableRow<EmailMessage>>() {
			@Override
			public TableRow<EmailMessage> call(TableView<EmailMessage> arg0) {
				return new TableRow<EmailMessage>() {
					@Override
					protected void updateItem(EmailMessage item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							if (item.isRead()) {
								setStyle("");
							} else {
								setStyle("-fx-font-weight: bold");
							}
						}
					}
				};
				
			}
		});
	}
	
	private void setUpFolderSelection() {
		//on mouse click (OF emailTreeItem folder, receive messages)
		emailsTreeView.setOnMouseClicked(e -> {
			EmailTreeItem<String> item = ((EmailTreeItem<String>)emailsTreeView.getSelectionModel().getSelectedItem());
			if (item != null) {
                emailManager.setSelectedFolder(item);
				emailsTableView.setItems(item.getEmailMessages());
			}
		});		
	}
	private void setUpEmailsTableView() {
		
		senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessage, String>("sender"));
		dateCol.setCellValueFactory(new PropertyValueFactory<EmailMessage, Date>("date"));
		
		//creating a context menu that has two menu items for unread/delete actions
		emailsTableView.setContextMenu(new ContextMenu(unreadActionMenuItem, deleteActionMenuItem));
	}
	
	private void setUpEmailsTreeView() {
		emailsTreeView.setRoot(emailManager.getFoldersRoot());
		emailsTreeView.setShowRoot(false);
		
	}

}
