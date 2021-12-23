package com.example.insuranceservice.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 365, 390);
        stage.setTitle("Insurance service");
        stage.getIcons().add(new Image("/icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}