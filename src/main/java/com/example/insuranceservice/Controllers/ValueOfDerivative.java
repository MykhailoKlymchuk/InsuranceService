package com.example.insuranceservice.Controllers;

import com.example.insuranceservice.db.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class ValueOfDerivative extends Menu {

    @FXML private Label cost;
    @FXML private Label costRisks;
    @FXML private Label totalCost;

    @FXML void initialize() throws SQLException, ClassNotFoundException {
        var BD = new DatabaseHandler();
        cost.setText(String.valueOf(BD.getCost()));
        costRisks.setText(String.valueOf(BD.getCostRisks()));
        totalCost.setText(String.valueOf(BD.getCost()+BD.getCostRisks()));
    }
}
