package edu.lk.ijse.ganewaththalatex.ganewaththalatex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class appinitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        URL fxmlUrl = getClass().getResource("/view/LoginPage.fxml");
        if (fxmlUrl == null) {
            System.err.println("Error: Cannot find /view/LoginPage.fxml");
            return;
        }
        Parent parent = FXMLLoader.load(fxmlUrl);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("Ganewaththa Latex - Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}