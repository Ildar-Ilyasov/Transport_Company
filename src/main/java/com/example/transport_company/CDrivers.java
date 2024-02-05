package com.example.transport_company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class CDrivers {
    @FXML private Button BackButton;
    @FXML private TableView<DriversTable> DriversView;
    @FXML private TableColumn<DriversTable, String> NameColumn;
    @FXML private TableColumn<DriversTable, String> CompanyColumn;
    @FXML private TableColumn<DriversTable, String> TransportColumn;
    @FXML private TableColumn<DriversTable, String> LocationColumn;

    @FXML
    void initialize() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111");
        String query = "SELECT name,company,transport,location FROM drivers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CompanyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        TransportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        ObservableList<DriversTable> driversList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String company = resultSet.getString("company");
            String transport = resultSet.getString("transport");
            String location = resultSet.getString("location");
            DriversTable driver = new DriversTable(name,company,transport,location);
            driversList.add(driver);
        }
        DriversView.setItems(driversList);
        BackButton.setOnAction(e -> {
            BackButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Menu.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}