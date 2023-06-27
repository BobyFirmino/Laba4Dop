package com.example.lab_04_calc.model.calculation;

public interface Calculation<T> {
    T add(T a, T b);
    T subtract(T a, T b);

}
