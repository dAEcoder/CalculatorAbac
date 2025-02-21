module com.example.calculatorabac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculatorabac to javafx.fxml;
    exports com.example.calculatorabac;
}