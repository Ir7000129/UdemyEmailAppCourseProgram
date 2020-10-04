module FirstJavaFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires java.desktop;
//    requires activation;
    requires java.mail;    

    opens emailApp;
    opens emailApp.view;
    opens emailApp.Controller;

}