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

public class CCarriers {
    @FXML private Button BackButton;
    @FXML private TableView<CarriersTable> carriersView;
    @FXML private TableColumn<CarriersTable, String> nameColumn;
    @FXML private TableColumn<CarriersTable, String> termColumn;
    @FXML private TableColumn<CarriersTable, String> capacityColumn;
    @FXML private TableColumn<CarriersTable, String> typetransportColumn;

    @FXML
    void initialize() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111");
        String query = "SELECT name,term,capacity,typetransport FROM carriers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        termColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        typetransportColumn.setCellValueFactory(new PropertyValueFactory<>("typetransport"));
        ObservableList<CarriersTable> carriersList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String term = resultSet.getString("term");
            String capacity = resultSet.getString("capacity");
            String typetransport = resultSet.getString("typetransport");
            CarriersTable carrier = new CarriersTable(name,term,capacity,typetransport);
            carriersList.add(carrier);
        }
        carriersView.setItems(carriersList);
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