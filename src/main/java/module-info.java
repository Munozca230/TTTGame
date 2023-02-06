module com.example.ttt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ttt to javafx.fxml;
    exports com.example.ttt;
}