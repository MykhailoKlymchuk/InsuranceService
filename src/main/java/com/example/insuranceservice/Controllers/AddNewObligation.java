package com.example.insuranceservice.Controllers;

import com.example.insuranceservice.db.DatabaseHandler;
import com.example.insuranceservice.Date.InsuranceObligation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewObligation  extends Menu {

    @FXML
    private TextField cost;
    @FXML
    private TextField dateOfTermination;
    @FXML
    private TextField idClient;
    @FXML
    private TextField levelOfRisk;
    @FXML
    private TextField subjectInsurance;

    @FXML
    public void addNewObl(ActionEvent event) throws SQLException, ClassNotFoundException {
        var DB = new DatabaseHandler();
        var obl = new InsuranceObligation();
        if (!idClient.getText().equals("") &&
                !subjectInsurance.getText().equals("") &&
                !levelOfRisk.getText().equals("") &&
                !cost.getText().equals("") &&
                !dateOfTermination.getText().equals("")) {
            if (DB.checkClient(idClient.getText().trim())) {
                obl.setIdClient(Integer.parseInt(idClient.getText().trim()));
                obl.setSubjectInsurance(subjectInsurance.getText().trim());
                obl.setLevelOfRisk(Double.parseDouble(levelOfRisk.getText().trim()));
                obl.setCost(Double.parseDouble(cost.getText().trim()));
                try {
                    obl.setDateOfTermination(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(dateOfTermination.getText().trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                obl.setCreationDate(new Date());
                DB.addNewObligation(obl);
                cost.setText("");
                idClient.setText("");
                subjectInsurance.setText("");
                levelOfRisk.setText("");
                dateOfTermination.setText("");
            }else {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Request error");
                alert.setHeaderText("Client with id not found! \nInsurance obligation not added.");
                alert.showAndWait();
            }
        }
    }
}
