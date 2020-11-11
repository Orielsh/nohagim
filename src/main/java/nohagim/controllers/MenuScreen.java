package nohagim.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nohagim.App;

import java.io.IOException;

public class MenuScreen {

    @FXML
    Button customerList;

    @FXML
    private void customersList() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/customersList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),900,487);
        Stage stage = new Stage();
        stage.setTitle("רשימת חברות");
        stage.setScene(scene);
        customerList.disableProperty().bind(stage.showingProperty());
        stage.show();
    }
}
