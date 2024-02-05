module com.example.transport_company {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.transport_company to javafx.fxml;
    exports com.example.transport_company;
}