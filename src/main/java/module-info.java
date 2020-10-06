module nohagimb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens nohagimb to javafx.fxml;
    exports nohagimb;
}