package com.example.lab_04_calc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.example.lab_04_calc.model.calculation.BinaryNumberCalculation;
import com.example.lab_04_calc.model.number.BinaryNumber;

import java.util.ArrayList;
import java.util.List;

public class BinaryNumberController extends NumberController<BinaryNumber> {

    private List<BinaryNumber> numbers;

    @FXML
    private TextField binaryDisplay;

    public BinaryNumberController() {
        numbers = new ArrayList<>();
    }

    @FXML
    public void processNumpad(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("0") || buttonText.equals("1")) {
            binaryDisplay.setText(binaryDisplay.getText() + buttonText);
        }
    }

    @FXML
    public void processOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("C")) {
            binaryDisplay.clear();
        } else if (buttonText.equals("⌫")) {
            String currentText = binaryDisplay.getText();
            if (!currentText.isEmpty()) {
                binaryDisplay.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else {
            if (!binaryDisplay.getText().isEmpty()){
                int value = Integer.parseInt(binaryDisplay.getText(), 2);
                numbers.add(new BinaryNumber(value));
            }

            binaryDisplay.clear();

            if (buttonText.equals("=")) {
                try {
                    BinaryNumber result = performCalculation(numbers);
                    binaryDisplay.setText(Integer.toBinaryString(result.getValue()));
                } catch (IllegalArgumentException e) {
                    binaryDisplay.setText(e.getMessage());
                }

                numbers.clear();
                currentOperator = null;
            } else {
                currentOperator = buttonText;
            }
        }
    }

    private BinaryNumber performCalculation(List<BinaryNumber> numbers) {
        if (numbers.isEmpty()) {
            return new BinaryNumber(0);
        }

        BinaryNumber result = numbers.get(0);
        BinaryNumberCalculation calculation = new BinaryNumberCalculation();

        for (int i = 1; i < numbers.size(); i++) {
            BinaryNumber number = numbers.get(i);

            result = switch (currentOperator) {
                case "+" -> calculation.add(result, number);
                case "-" -> calculation.subtract(result, number);
                case "*" -> calculation.multiply(result, number);
                default -> throw new IllegalArgumentException("Invalid operator");
            };
        }
        return result;
    }

}
