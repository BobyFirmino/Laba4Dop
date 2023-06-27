module com.example.lab_04_calc {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.example.lab_04_calc to javafx.fxml;
//    exports com.example.lab_04_calc;
    exports com.example.lab_04_calc.application;
    opens com.example.lab_04_calc.application to javafx.fxml;
    exports com.example.lab_04_calc.controller;
    opens com.example.lab_04_calc.controller to javafx.fxml;
}