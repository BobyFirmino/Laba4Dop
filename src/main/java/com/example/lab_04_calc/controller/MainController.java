package com.example.lab_04_calc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    public Button realNumbersButton;
    public Button binaryNumbersButton;

    @FXML
    public void goToRealNumbersCalculator() throws IOException {
        Stage stage = (Stage) realNumbersButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/lab_04_calc/real_number.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToBinaryNumbersCalculator() throws IOException {
        Stage stage = (Stage) binaryNumbersButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/lab_04_calc/binary_number.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
