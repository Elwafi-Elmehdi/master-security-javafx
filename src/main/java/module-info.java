module com.example.securityhashage {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.securityhashage to javafx.fxml;
    exports com.example.securityhashage;
}