package com.example.insuranceservice.Controllers;

import java.sql.SQLException;

import com.example.insuranceservice.db.DatabaseHandler;
import com.example.insuranceservice.Date.InsuranceObligation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class SearchObl  extends Menu {

    @FXML
    private TextField Lower;
    @FXML
    private TextField Upper;
    @FXML
    private TextField oblId;

    @FXML
    private TableView<InsuranceObligation> tableObls;
    @FXML
    private TableColumn<?, ?> cost;
    @FXML
    private TableColumn<?, ?> creationDate;
    @FXML
    private TableColumn<?, ?> dateOfTermination;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> idClient;
    @FXML
    private TableColumn<?, ?> levelOfRisk;
    @FXML
    private TableColumn<?, ?> subjectInsurance;

    DatabaseHandler DB = new DatabaseHandler();

    @FXML
    void searchCostAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!Lower.getText().isEmpty()&&!Upper.getText().isEmpty()) {
            show(DB.getSearchObl(Lower.getText().trim(), Upper.getText().trim(), "cost"));
        }else errorMessage();
    }

    @FXML
    void searchCreationDateAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!Lower.getText().isEmpty() && !Upper.getText().isEmpty()) {
            try {
                show(DB.getSearchObl('\'' + Lower.getText().trim() + '\'', '\'' + Upper.getText().trim() + '\'', "creationDate"));
            } catch (Exception e) {
                errorMessage();
            }
        } else errorMessage();
    }

    @FXML
    void searchTerminationDateAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!Lower.getText().isEmpty() && !Upper.getText().isEmpty()) {
            try {
                show(DB.getSearchObl('\'' + Lower.getText().trim() + '\'', '\'' + Upper.getText().trim() + '\'', "dateOfTermination"));
            } catch (Exception e) {
                errorMessage();
            }
        } else errorMessage();
    }

    @FXML
    void searchIdAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!Lower.getText().isEmpty()&&!Upper.getText().isEmpty()) {
            show(DB.getSearchObl(Lower.getText().trim(), Upper.getText().trim(), "id"));
        }else errorMessage();
    }

    @FXML
    void searchRiskAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!Lower.getText().isEmpty()&&!Upper.getText().isEmpty()) {
            show(DB.getSearchObl(Lower.getText().trim(), Upper.getText().trim(), "levelOfRisk"));
        }else errorMessage();
    }

    @FXML
    void pay(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!oblId.getText().trim().isEmpty()&&DB.checkObl(oblId.getText().trim())) {
            DB.pay(oblId.getText().trim());
            show(DB.getAllObl());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Request error");
            alert.setHeaderText("Request error. \nCheck the content of the data in the id obligation fields!");
            alert.showAndWait();
        }
    }

    void show(ObservableList<InsuranceObligation> list) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        subjectInsurance.setCellValueFactory(new PropertyValueFactory<>("subjectInsurance"));
        levelOfRisk.setCellValueFactory(new PropertyValueFactory<>("levelOfRisk"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        dateOfTermination.setCellValueFactory(new PropertyValueFactory<>("dateOfTermination"));
        tableObls.setItems(list);
    }

    private void errorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Request error");
        alert.setHeaderText("Request error. \nCheck the content of the data in the Lower and Upper fields!");
        alert.showAndWait();
    }
}
