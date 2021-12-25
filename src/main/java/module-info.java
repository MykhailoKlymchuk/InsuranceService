module com.example.insuranceservice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.apache.logging.log4j;

    opens com.example.insuranceservice.Date to javafx.base;
    exports com.example.insuranceservice.Date;
    exports com.example.insuranceservice.Controllers;
    opens com.example.insuranceservice.Controllers to javafx.fxml;
    exports com.example.insuranceservice.db;
    opens com.example.insuranceservice.db to javafx.fxml;
}