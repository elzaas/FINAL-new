package org.example.employeedb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login");

        // Set the initial size of the window
        stage.setWidth(850);
        stage.setHeight(550);

        // Optionally, set the minimum and maximum sizes
        stage.setMinWidth(850);
        stage.setMinHeight(550);
        stage.setMaxWidth(850);
        stage.setMaxHeight(550);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
