package com.example.lab_04_calc.model.calculation;

import com.example.lab_04_calc.model.number.BinaryNumber;

public class BinaryNumberCalculation implements Calculation<BinaryNumber> {

    @Override
    public BinaryNumber add(BinaryNumber a, BinaryNumber b) {
        int result = a.getValue() + b.getValue();
        return new BinaryNumber(result);
    }

    @Override
    public BinaryNumber subtract(BinaryNumber a, BinaryNumber b) {
        int result = a.getValue() - b.getValue();
        return new BinaryNumber(result);
    }

    public BinaryNumber multiply(BinaryNumber a, BinaryNumber b) {
        int result = a.getValue() & b.getValue();
        return new BinaryNumber(result);
    }

}
