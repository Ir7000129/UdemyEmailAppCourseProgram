package emailApp.view;

import emailApp.Controller.BaseController;
import emailApp.Controller.ComposeWindowController;
//import emailApp.Controller.ComposeWindowController;
import emailApp.Controller.LoginWindowController;
import emailApp.Controller.MainMenuController;
import emailApp.Controller.OptionsWindowController;
import emailApp.EmailManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private EmailManager emailManager;
    private ArrayList<Stage> activeStages;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;
    private boolean mainWindowOpen = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages=new ArrayList<Stage>();
    }
   
    public boolean isMainWindowOpen() {
    	return mainWindowOpen;
    }

    //View Options Handling
    	//method to open the login window
    public void showLoginWindow(){
        System.out.println("Application running");
        BaseController controller = new LoginWindowController(this, emailManager,"LoginWindow.fxml");
        stageShowing(controller, false);
    }
    	//method to open the main window
    public void showMainWindow(){
        BaseController controller = new MainMenuController(this, emailManager,"MainMenu.fxml");
        stageShowing(controller, true);
        mainWindowOpen = true;
    }
    	//method to open the options window
    public void showOptionsWindow(){
        System.out.println("Options window opened");
        BaseController controller = new OptionsWindowController(this,emailManager,"OptionsWindow.fxml");
        stageShowing(controller, false);
    }
    
    public void showComposeWindow(){
        System.out.println("Compose window opened");
        BaseController controller = new ComposeWindowController(this,emailManager,"ComposeWindow.fxml");
        stageShowing(controller, false);
    }
         

    private void stageShowing(BaseController baseController, boolean minSize){
        var fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try{
            parent = fxmlLoader.load();
        }catch(IOException e){
            System.out.println("error 401");
            e.printStackTrace();
            return;
        }
        var scene = new Scene(parent);
        var stage = new Stage();
        stage.setScene(scene);
        if(minSize) {
        	stage.setMinWidth(1400);
        	stage.setMinHeight(840);
        }
 
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        activeStages.add(stage);
    }
    public void closeShowingStage(Stage stageClose){
        stageClose.close();
        activeStages.remove(stageClose);
    }

    
    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }
    	
    	//
    public void updateStyles (){
        for(Stage stage:activeStages){
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();            
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }
}
