package nohagim.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import nohagim.Entities.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomer implements Initializable {

    private Customer customer;

    @FXML
    private
    GridPane gridPane;

    ViewCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.add(new Label(customer.getId().toString()), 1, 0);
        gridPane.add(new Label(customer.getName()), 1, 1);
        gridPane.add(new Label(customer.getContactPerson()), 1, 2);
        gridPane.add(new Label(customer.getAddress()), 1, 3);
        gridPane.add(new Label(customer.getPhoneNumber().toString()), 1, 4);
        gridPane.add(new Label(customer.getFaxNumber().toString()), 1, 5);
        gridPane.add(new Label(customer.getEmail()), 1, 6);
        gridPane.add(new Label(customer.getJoinDate().toString()), 1, 7);
        gridPane.add(new Label(customer.getProfessionalManagerName()), 1, 8);
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
