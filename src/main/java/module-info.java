module com.example.securityhashage {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.securityhashage to javafx.fxml;
    exports com.example.securityhashage;
}