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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login");

        // Set the initial size of the window
        stage.setWidth(700);
        stage.setHeight(500);

        // Optionally, set the minimum and maximum sizes
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.setMaxWidth(700);
        stage.setMaxHeight(500);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
