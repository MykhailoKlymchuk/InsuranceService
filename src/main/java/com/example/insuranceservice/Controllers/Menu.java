package com.example.insuranceservice.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Menu {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void addOblAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("AddNewObligation.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 700, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewAllOblAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("ViewAllObl.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addClientAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("AddNewClient.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 675, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewAllClientAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("viewAllClient.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchOblAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("searchObl.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1000, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void valueDerAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass().getResource("valueOfDerivative.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 650, 525);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }
}
