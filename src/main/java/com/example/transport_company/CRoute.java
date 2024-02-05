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

public class CRoute {
    @FXML private Button BackButton;
    @FXML private TableView<RouteTable> RouteView;
    @FXML private TableColumn<RouteTable, String> WhereFromColumn;
    @FXML private TableColumn<RouteTable, String> WherColumn;
    @FXML private TableColumn<RouteTable, String> DeliveryTimeColumn;
    @FXML private TableColumn<RouteTable, String> CoordWhereFromColumn;
    @FXML private TableColumn<RouteTable, String> CoorsWherColumn;

    @FXML
    void initialize() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111");
        String query = "SELECT wherefrom,wher, deliverytime,coordwherefrom,coordwhere FROM route";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        WhereFromColumn.setCellValueFactory(new PropertyValueFactory<>("wherefrom"));
        WherColumn.setCellValueFactory(new PropertyValueFactory<>("wher"));
        DeliveryTimeColumn.setCellValueFactory(new PropertyValueFactory<>("deliverytime"));
        CoordWhereFromColumn.setCellValueFactory(new PropertyValueFactory<>("coordwherefrom"));
        CoorsWherColumn.setCellValueFactory(new PropertyValueFactory<>("coordwhere"));
        ObservableList<RouteTable> routeList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String wherefrom = resultSet.getString("wherefrom");
            String wher = resultSet.getString("wher");
            String deliverytime = resultSet.getString("deliverytime");
            String coordwherefrom = resultSet.getString("coordwherefrom");
            String coordwhere = resultSet.getString("coordwhere");
            RouteTable route = new RouteTable(wherefrom,wher,deliverytime,coordwherefrom,coordwhere);
            routeList.add(route);
        }
        RouteView.setItems(routeList);
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