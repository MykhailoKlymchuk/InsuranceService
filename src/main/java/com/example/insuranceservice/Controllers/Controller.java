package com.example.insuranceservice.Controllers;
 import java.io.IOException;
 import java.sql.SQLException;

 import com.example.insuranceservice.db.DatabaseHandler;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.image.Image;
 import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button loginButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;


    @FXML
    private void cancelButtonAction() {
        System.exit(0);
    }

    @FXML
    private void loginButtonAction() throws SQLException, ClassNotFoundException {
        var DB=new DatabaseHandler();
        String loginText = loginField.getText().trim();
        String loginPassword = passwordField.getText().trim();
        if (!loginText.equals("") && (!loginPassword.equals(""))) {
            if (DB.checkUser(loginText,loginPassword)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("app.fxml")); // створення сцени, відвантаження з файлу .fxml
                Stage _stage = (Stage) loginButton.getScene().getWindow(); // повернення батьківської арени
                _stage.close(); //закриття арени
                Stage stage = new Stage(); // створення нової арени
                try {
                    Scene scene = new Scene(loader.load(),675,525);  // завантаження нової сцени
                    stage.setScene(scene);
                    stage.setTitle("Insurance service"); //заголовок арени
                    stage.getIcons().add(new Image("/icon.png")); //зображення ярлика
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.showAndWait(); // завантаження нової арени з головним вікном
            } else {
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login error");
                alert.setHeaderText("Incorrect login information!");
                alert.showAndWait();
            }
        }
    }
}

