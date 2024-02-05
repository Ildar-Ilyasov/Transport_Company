package com.example.transport_company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CMenu {

    @FXML
    private Button BClose;
    @FXML
    private Button BAdd;
    @FXML
    private Button BCompany;
    @FXML
    private Button BDrivers;
    @FXML
    private Button BRoute;
    @FXML
    private Button BDelete;
    @FXML
    private DatePicker SendingDate;
    @FXML
    private DatePicker DeliveryDate;
    @FXML
    private ChoiceBox<String> CompBox;
    @FXML
    private ChoiceBox<String> DriverBox;
    @FXML
    private ChoiceBox<String> WhereFromBox;
    @FXML
    private ChoiceBox<String> WherBox;
    @FXML
    private TextField NameInput;
    @FXML
    private TextField WeightInput;
    @FXML
    private TableView<TableProd> productView;
    @FXML
    private TableColumn<TableProd, String> nameColumn;
    @FXML
    private TableColumn<TableProd, String> weightColumn;
    @FXML
    private TableColumn<TableProd, String> whereFromColumn;
    @FXML
    private TableColumn<TableProd, String> wherColumn;
    @FXML
    private TableColumn<TableProd, String> companyColumn;
    @FXML
    private TableColumn<TableProd, String> driverColumn;
    @FXML
    private TableColumn<TableProd, String> dispathColumn;
    @FXML
    private TableColumn<TableProd, String> delivtimeColumn;
    @FXML
    private TableColumn<TableProd, String> curtimeColumn;

    ObservableList<String> CompList = FXCollections.observableArrayList("ПАО \"РЖД\"", "ООО \"ПЭК\"", "ООО \"Байкал-Сервис\"", "ООО \"Деловые Линии\"",
            "ООО \"БКС Экспресс\"", "ООО \"Деутран\"", "ООО \"Экспедиторспед\"", "ООО \"Автотрансконсоли\"", "АО \"ДТЭК\"");
    ObservableList<String> RouteList = FXCollections.observableArrayList("Казань", "Москва", "Пенза", "Ростов-на-Дону", "Санкт-Петербург");


    @FXML
    void initialize() throws SQLException {
        UpdateDatabase();
        CompBox.setValue("Выберите компанию");
        DriverBox.setValue("Выберите водителя");
        WhereFromBox.setValue("Выберите откуда");
        WherBox.setValue("Выберите куда");
        CompBox.setItems(CompList);
        WhereFromBox.setItems(RouteList);
        WherBox.setItems(RouteList);
        SendingDate.setValue(LocalDate.now());

        CompBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("ПАО \"РЖД\"")) {
                DriverBox.setValue("Колесов Демид Максимович");
                DriverBox.setItems(FXCollections.observableArrayList("Колесов Демид Максимович", "Кузнецов Егор Давидович", "Морозов Максим Маратович"));
            } else if (newValue.equals("ООО \"ПЭК\"")) {
                DriverBox.setValue("Козлов Артемий Иванович");
                DriverBox.setItems(FXCollections.observableArrayList("Козлов Артемий Иванович", "Кузнецов Георгий Юрьевич", "Михайлов Александр Давидович"));
            } else if (newValue.equals("ООО \"Байкал-Сервис\"")) {
                DriverBox.setValue("Куприянов Дмитрий Михайлович");
                DriverBox.setItems(FXCollections.observableArrayList("Куприянов Дмитрий Михайлович", "Гришин Илья Ильич", "Щербаков Александр Максимович"));
            } else if (newValue.equals("ООО \"Деловые Линии\"")) {
                DriverBox.setValue("Попов Артур Александрович");
                DriverBox.setItems(FXCollections.observableArrayList("Попов Артур Александрович", "Худяков Андрей Денисович", "Иванов Денис Максимович"));
            } else if (newValue.equals("ООО \"БКС Экспресс\"")) {
                DriverBox.setValue("Попов Даниил Тимофеевич");
                DriverBox.setItems(FXCollections.observableArrayList("Попов Даниил Тимофеевич", "Марков Михаил Александрович", "Романов Дмитрий Миронович"));
            } else if (newValue.equals("ООО \"Деутран\"")) {
                DriverBox.setValue("Смирнов Артём Александрович");
                DriverBox.setItems(FXCollections.observableArrayList("Смирнов Артём Александрович", "Никифоров Павел Константинович", "Глебов Владислав Назарович"));
            } else if (newValue.equals("ООО \"Экспедиторспед\"")) {
                DriverBox.setValue("Тарасов Роман Миронович");
                DriverBox.setItems(FXCollections.observableArrayList("Тарасов Роман Миронович", "Крылов Артём Маркович", "Зиновьев Константин Русланович"));
            } else if (newValue.equals("ООО \"Автотрансконсоли\"")) {
                DriverBox.setValue("Семенов Даниил Петрович");
                DriverBox.setItems(FXCollections.observableArrayList("Семенов Даниил Петрович", "Агеев Фёдор Евгеньевич", "Яковлев Тимофей Леонович"));
            } else if (newValue.equals("АО \"ДТЭК\"")) {
                DriverBox.setValue("Филиппов Мирослав Давидович");
                DriverBox.setItems(FXCollections.observableArrayList("Филиппов Мирослав Давидович", "Новиков Иван Романович", "Ершов Михаил Артурович"));
            }
        });
        BClose.setOnAction(e -> {
            Start start = new Start();
            try {
                start.start(new Stage());
                BClose.getScene().getWindow().hide();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        BCompany.setOnAction(e -> {
            BCompany.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ListCarriers.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        BDrivers.setOnAction(e -> {
            BDrivers.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ListDrivers.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        BRoute.setOnAction(e -> {
            BRoute.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ListRoute.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        BAdd.setOnAction(e -> {
            String name = NameInput.getText();
            String weight = WeightInput.getText();
            LocalDate selecDate = SendingDate.getValue();
            LocalDate delivDate = DeliveryDate.getValue();
            String company = CompBox.getValue();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String currentTime = now.format(formatter);
            String driver = DriverBox.getValue();
            String wherefrom = WhereFromBox.getValue();
            String wher = WherBox.getValue();
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.minusDays(1);

            if (!name.isEmpty() && !weight.isEmpty() && company != "Выберите компанию" && wherefrom != "Выберите откуда" && wher != "Выберите куда" && wherefrom != wher && selecDate.isBefore(delivDate) && previousDay.isBefore(selecDate)) {
                String selectedDate = SendingDate.getValue().toString();
                String deliveryDate = DeliveryDate.getValue().toString();
                try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111")) {
                    String firstQuery = "INSERT INTO product (name, weight, wherefrom, wher , company, driver, selectedDate, deliveryDate, currentTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(firstQuery);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, weight);
                    preparedStatement.setString(3, wherefrom);
                    preparedStatement.setString(4, wher);
                    preparedStatement.setString(5, company);
                    preparedStatement.setString(6, driver);
                    preparedStatement.setString(7, selectedDate);
                    preparedStatement.setString(8, deliveryDate);
                    preparedStatement.setString(9, currentTime);
                    preparedStatement.executeUpdate();

                    NameInput.clear();
                    WeightInput.clear();
                    SendingDate.setValue(LocalDate.now());
                    DeliveryDate.setValue(null);
                    UpdateDatabase();
                } catch (SQLException ex) {
                    System.out.println("Ошибка при работе с БД: " + ex.getMessage());
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ErrorMenu.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BDelete.setOnAction(e -> deleteSelectedItem());
    }

    private void deleteSelectedItem() {
        TableProd selectedProduct = productView.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            productView.getItems().remove(selectedProduct);
            deleteFromDatabase(selectedProduct);
        }
    }


    private void deleteFromDatabase(TableProd selectedProduct) {
        String query = "DELETE FROM product WHERE name = ? AND weight = ? AND wherefrom = ? AND wher = ? AND company = ? AND driver = ? AND selectedDate = ? AND deliveryDate = ? AND currentTime = ? ";

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedProduct.getName());
            statement.setString(2, selectedProduct.getWeight());
            statement.setString(3, selectedProduct.getWherefrom());
            statement.setString(4, selectedProduct.getWher());
            statement.setString(5, selectedProduct.getCompany());
            statement.setString(6, selectedProduct.getDriver());
            statement.setString(7, selectedProduct.getSelectedDate());
            statement.setString(8, selectedProduct.getDeliveryDate());
            statement.setString(9, selectedProduct.getCurrentTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void UpdateDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111");
        String secondQuery = "SELECT name, weight, wherefrom, wher, company, driver, selectedDate, deliveryDate, currentTime FROM product";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(secondQuery);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        whereFromColumn.setCellValueFactory(new PropertyValueFactory<>("wherefrom"));
        wherColumn.setCellValueFactory(new PropertyValueFactory<>("wher"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        driverColumn.setCellValueFactory(new PropertyValueFactory<>("driver"));
        dispathColumn.setCellValueFactory(new PropertyValueFactory<>("selectedDate"));
        delivtimeColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        curtimeColumn.setCellValueFactory(new PropertyValueFactory<>("currentTime"));
        ObservableList<TableProd> prodList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String name1 = resultSet.getString("name");
            String weight1 = resultSet.getString("weight");
            String wherefrom1 = resultSet.getString("wherefrom");
            String wher1 = resultSet.getString("wher");
            String company1 = resultSet.getString("company");
            String driver1 = resultSet.getString("driver");
            String selectedDate1 = resultSet.getString("selectedDate");
            String deliveryDate1 = resultSet.getString("deliveryDate");
            String currentTime1 = resultSet.getString("currentTime");
            TableProd prod = new TableProd(name1, weight1, wherefrom1, wher1, company1, driver1, selectedDate1, deliveryDate1, currentTime1);
            prodList.add(prod);
        }
        productView.setItems(prodList);
    }
}