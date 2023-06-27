package com.example.lab_04_calc.model.calculation;

import com.example.lab_04_calc.model.number.RealNumber;

public class RealNumberCalculation  implements Calculation<RealNumber>{

    @Override
    public RealNumber add(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() + b.getValue());
    }

    @Override
    public RealNumber subtract(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() - b.getValue());
    }

    public RealNumber multiply(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() * b.getValue());
    }

    public RealNumber divide(RealNumber a, RealNumber b) {
        if (b.getValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new RealNumber(a.getValue() / b.getValue());
    }

    public RealNumber mod(RealNumber a, RealNumber b) {
        if (b.getValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new RealNumber(Math.floorMod((int) a.getValue(), (int) b.getValue()));
    }

    public RealNumber pow(RealNumber a, RealNumber b) {
        if (a.getValue() < 0) {
            throw new IllegalArgumentException("Base < 0");
        }
        return new RealNumber(Math.pow(a.getValue(), b.getValue()));
    }

    public RealNumber sqrt(RealNumber a) {
        if (a.getValue() < 0) {
            throw new IllegalArgumentException("Cannot sqrt < 0");
        }
        return new RealNumber(Math.sqrt(a.getValue()));
    }

    public RealNumber percent(RealNumber a) {
        return new RealNumber(a.getValue() / 100);
    }

    public RealNumber div1(RealNumber a) {
        if (a.getValue() == 0)
            throw new IllegalArgumentException("Cannot 1 / 0");
        return new RealNumber(1 / a.getValue());

    }
}
