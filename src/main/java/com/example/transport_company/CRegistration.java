package com.example.transport_company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRegistration {
    @FXML private Button B1;
    @FXML private Button BClose;
    @FXML private TextField TF1;
    @FXML private TextField TF2;
    @FXML private TextField TF3;
    @FXML
    void initialize() {
        B1.setOnAction(e -> {
            String name = TF1.getText();
            String login = TF2.getText();
            String password = TF3.getText();
            Start start = new Start();
            String HashedPassword = hashString(password);
            if (!name.isEmpty() && !login.isEmpty() && !password.isEmpty()) {
                try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserData", "postgres", "1111")) {
                    String query = "INSERT INTO userdata (name, login, password) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, login);
                    preparedStatement.setString(3, HashedPassword);
                    preparedStatement.executeUpdate();
                    try {
                        start.start(new Stage());
                        B1.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (SQLException ex) {
                    System.out.println("Ошибка при работе с БД: " + ex.getMessage());
                }
            }
            else{
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ErrorReg.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
    }
    private String hashString(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte a : hashBytes) {
                String hex = Integer.toHexString(0xff & a);
                if (hex.length() == 1) {hexString.append('0');}
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}