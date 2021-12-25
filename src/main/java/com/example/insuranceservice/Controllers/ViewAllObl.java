package com.example.insuranceservice.Controllers;

import com.example.insuranceservice.db.DatabaseHandler;
import com.example.insuranceservice.Date.InsuranceObligation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ViewAllObl extends Menu {
    static final Logger logger= LogManager.getLogger(ViewAllObl.class);

    @FXML private TextField idDetele;

    @FXML private TableView<InsuranceObligation> tableObls;
    @FXML private TableColumn<?, ?> cost;
    @FXML private TableColumn<?, ?> creationDate;
    @FXML private TableColumn<?, ?> dateOfTermination;
    @FXML private TableColumn<?, ?> id;
    @FXML private TableColumn<?, ?> idClient;
    @FXML private TableColumn<?, ?> levelOfRisk;
    @FXML private TableColumn<?, ?> subjectInsurance;

    @FXML
    public void deleteAction() throws SQLException, ClassNotFoundException {
        var id=idDetele.getText();
        if(!id.equals("")){
            var DB = new DatabaseHandler();
            if(DB.checkObl(id)){
                DB.deleteObl(idDetele.getText());
                initialize();
                logger.info("Зобов'язання з id="+id+" видалено.");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Request error");
                alert.setHeaderText("Insurance obligation with id not found!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        var DB = new DatabaseHandler();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        subjectInsurance.setCellValueFactory(new PropertyValueFactory<>("subjectInsurance"));
        levelOfRisk.setCellValueFactory(new PropertyValueFactory<>("levelOfRisk"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        dateOfTermination.setCellValueFactory(new PropertyValueFactory<>("dateOfTermination"));
        tableObls.setItems(DB.getAllObl());
    }
}
