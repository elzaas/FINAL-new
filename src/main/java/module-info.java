module org.example.employeedb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.employeedb to javafx.fxml;
    exports org.example.employeedb;
}