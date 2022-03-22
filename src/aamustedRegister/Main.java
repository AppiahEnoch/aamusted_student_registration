package aamustedRegister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    ShareData m=ShareData.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        m.openMainConn();
        m.createTables();
        Parent root = FXMLLoader.load(getClass().getResource("mainLogin.fxml"));
        root = FXMLLoader.load(getClass().getResource("userName.fxml"));
        primaryStage.setTitle("PASSWORD REQUIRED!");
        primaryStage.setScene(new Scene(root));



        Image icon = new Image(getClass().getResourceAsStream("amusedLogo.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
