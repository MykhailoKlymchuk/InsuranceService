package com.example.insuranceservice.Controllers;

import java.sql.SQLException;

import com.example.insuranceservice.db.DatabaseHandler;
import com.example.insuranceservice.Date.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ViewAllClient  extends Menu {
    static final Logger logger= LogManager.getLogger(ViewAllClient.class);

    @FXML private TextField idDetele;

    @FXML private TableView<Client> tableClient;
    @FXML private TableColumn<?, ?> balanceInsurance;
    @FXML private TableColumn<?, ?> bankAccountNumber;
    @FXML private TableColumn<?, ?> email;
    @FXML private TableColumn<?, ?> firstName;
    @FXML private TableColumn<?, ?> idClient;
    @FXML private TableColumn<?, ?> lastName;
    @FXML private TableColumn<?, ?> phoneNumber;
    @FXML private TableColumn<?, ?> dateBirth;

    @FXML void deleteAction() throws SQLException, ClassNotFoundException {
        var id = idDetele.getText();
        if (!id.equals("")) {
            var DB = new DatabaseHandler();
            if (DB.checkClient(id)) {
                if (!DB.checkClientWithObl(id)) {
                    DB.deleteClient(id);
                    initialize();
                    logger.info("Зобов'язання з id="+id+" видалено.");
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Request error");
                    alert.setHeaderText("Obligations are assigned to this client, removal is impossible. \nFirst, remove all related commitments!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Request error");
                alert.setHeaderText("Client with id not found!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        var DB = new DatabaseHandler();
        idClient.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceInsurance.setCellValueFactory(new PropertyValueFactory<>("balanceInsurance"));
        bankAccountNumber.setCellValueFactory(new PropertyValueFactory<>("bankAccountNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        dateBirth.setCellValueFactory(new PropertyValueFactory<>("dateBirth"));
        tableClient.setItems(DB.getAllClients());
    }
}
