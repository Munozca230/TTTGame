module com.example.ttt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ttt to javafx.fxml;
    exports com.example.ttt;
}