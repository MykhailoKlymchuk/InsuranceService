package com.example.insuranceservice.Controllers;


import java.sql.SQLException;
import com.example.insuranceservice.db.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HelloApp extends Menu{

    @FXML private Label nc;
    @FXML private Label nm;
    @FXML private Label no;

    @FXML void initialize() throws SQLException, ClassNotFoundException {
        var BD = new DatabaseHandler();
        nc.setText(String.valueOf(BD.getNumClient()));
        nm.setText(String.valueOf(BD.getNumUser()));
        no.setText(String.valueOf(BD.getNumObl()));
    }
}
