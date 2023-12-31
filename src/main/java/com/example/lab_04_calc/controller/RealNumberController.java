package com.example.lab_04_calc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.example.lab_04_calc.model.calculation.RealNumberCalculation;
import com.example.lab_04_calc.model.number.RealNumber;

import java.util.ArrayList;
import java.util.List;

public class RealNumberController extends NumberController<RealNumber> {

    private List<RealNumber> numbers;

    public RealNumberController() {
        numbers = new ArrayList<>();
    }

    @FXML
    public void processNumpad(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals(".") && realDisplay.getText().contains(".")) {
            return;
        }

        realDisplay.setText(realDisplay.getText() + buttonText);
    }

    @FXML
    public void processOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (!realDisplay.getText().isEmpty()){
            double value = Double.parseDouble(realDisplay.getText());
            numbers.add(new RealNumber(value));
        }

        realDisplay.clear();
        if (buttonText.equals("sqrt") || buttonText.equals("%") || buttonText.equals("1/x")) {
            try {
                if (buttonText.equals("1/x")){
                    RealNumber result = perform1div(numbers);
                    realDisplay.setText(String.valueOf(result.getValue()));

                }
                else {
                    RealNumber result = buttonText.equals("sqrt") ? performSqrt(numbers) : performPercent(numbers);
                    realDisplay.setText(String.valueOf(result.getValue()));
                }
            } catch (IllegalArgumentException e) {
                realDisplay.setText(e.getMessage());
            }
        } else {
            currentOperator = buttonText;
        }
    }

    private RealNumber perform1div(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber number = numbers.get(0);
        numbers.clear();
        RealNumberCalculation calculation = new RealNumberCalculation();
        return calculation.div1(number);
    }

    private RealNumber performSqrt(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber number = numbers.get(0);
        numbers.clear();
        RealNumberCalculation calculation = new RealNumberCalculation();
        return calculation.sqrt(number);
    }

    private RealNumber performPercent(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber number = numbers.get(0);
        numbers.clear();
        RealNumberCalculation calculation = new RealNumberCalculation();
        return calculation.percent(number);
    }

    @FXML
    public void processEquals(ActionEvent event) {
        if (!realDisplay.getText().isEmpty()){
            double value = Double.parseDouble(realDisplay.getText());
            numbers.add(new RealNumber(value));
        }

        try {
            RealNumber result = performCalculation(numbers);
            realDisplay.setText(String.valueOf(result.getValue()));
        } catch (IllegalArgumentException e) {
            realDisplay.setText(e.getMessage());
        }

        numbers.clear();
        currentOperator = null;
    }

    private RealNumber performCalculation(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber result = numbers.get(0);
        RealNumberCalculation calculation = new RealNumberCalculation();

        for (int i = 1; i < numbers.size(); i++) {
            RealNumber number = numbers.get(i);

            result = switch (currentOperator) {
                case "+" -> calculation.add(result, number);
                case "-" -> calculation.subtract(result, number);
                case "*" -> calculation.multiply(result, number);
                case "/" -> calculation.divide(result, number);
                case "mod" -> calculation.mod(result, number);
                case "^" -> calculation.pow(result, number);
                default -> throw new IllegalArgumentException("Invalid operator");
            };
        }
        return result;
    }

}