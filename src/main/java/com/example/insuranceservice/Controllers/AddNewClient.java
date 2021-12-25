package com.example.insuranceservice.Controllers;

import com.example.insuranceservice.db.DatabaseHandler;
import com.example.insuranceservice.Date.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddNewClient extends Menu {
    static final Logger logger= LogManager.getLogger(AddNewClient.class);

    @FXML
    private TextField bankAccountNumber;
    @FXML
    private TextField dateBirth;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;

    public void addNewClientAction(ActionEvent event) {
        var DB = new DatabaseHandler();
        var client = new Client();
        if (!firstName.getText().equals("")&&
                !lastName.getText().equals("")&&
                !dateBirth.getText().equals("")&&
                !bankAccountNumber.getText().equals("")&&
                !email.getText().equals("")&&
                !phoneNumber.getText().equals("")) {
            client.setFirstName(firstName.getText().trim());
            client.setLastName(lastName.getText().trim());
            try {
                client.setDateBirth(new SimpleDateFormat("yyyy-MM-dd")
                        .parse(dateBirth.getText().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            client.setBankAccountNumber(bankAccountNumber.getText().trim());
            client.setEmail(email.getText().trim());
            client.setPhoneNumber(phoneNumber.getText().trim());

            DB.addNewClients(client);

            bankAccountNumber.setText("");
            dateBirth.setText("");
            email.setText("");
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            logger.info("add new client");
        }
    }
}
