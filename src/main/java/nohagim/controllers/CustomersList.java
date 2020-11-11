package nohagim.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nohagim.App;

import java.io.IOException;

public class CustomersList {

    @FXML
    private void addCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/addCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),674,657);
        Stage stage = new Stage();
        stage.setTitle("הוספת חברה");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
