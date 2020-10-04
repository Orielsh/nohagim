module nohagimb {
    requires javafx.controls;
    requires javafx.fxml;

    opens nohagimb to javafx.fxml;
    exports nohagimb;
}