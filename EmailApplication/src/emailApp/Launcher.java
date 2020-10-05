package emailApp;

import emailApp.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        System.out.println("ihiug");
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws  Exception{
        var viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
        viewFactory.updateStyles();
    }
}
