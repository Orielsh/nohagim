package nohagim.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import nohagim.App;
import nohagim.Entities.Customer;
import nohagim.dao.GenericDAOImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class CustomersList implements Initializable {

    @FXML
    TableView<Customer> list;
    @FXML
    TableColumn<Customer, String> name;
    @FXML
    TableColumn<Customer, Integer> id;
    @FXML
    TableColumn<Customer, Integer> phone;
    @FXML
    TableColumn<Customer, Integer> fax;
    @FXML
    TableColumn<Customer, String> professionalManagerName;
    @FXML
    TableColumn<Customer, String> email;
    @FXML
    TextField searchField;
    @FXML
    Button viewCustomer;
    private GenericDAOImpl<Customer> dao = new GenericDAOImpl<>();
    private List<Customer> customers;
    private FilteredList<Customer> filteredList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customers = dao.getListOfRecords(Customer.class);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        fax.setCellValueFactory(new PropertyValueFactory<>("faxNumber"));
        professionalManagerName.setCellValueFactory(new PropertyValueFactory<>("professionalManagerName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Customer> observableListOfCustomers = FXCollections.observableList(customers);
        filteredList = new FilteredList<>(observableListOfCustomers);
        viewCustomer.disableProperty().bind(list.getSelectionModel().selectedItemProperty().isNull());

        list.setItems(filteredList);
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(createPredicate(newValue))
        );
        list.setRowFactory(c -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                    viewCustomer();
            });

            return row;
        });
    }

    private Predicate<Customer> createPredicate(String searchText) {
        return customer -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return search(customer, searchText);
        };
    }

    private boolean search(Customer c, String text) {
        if (c.getName().contains(text))
            return true;
        if (c.getId().toString().contains(text))
            return true;
        if (c.getPhoneNumber().toString().contains(text))
            return true;
        if (c.getFaxNumber().toString().contains(text))
            return true;
        if (c.getProfessionalManagerName().contains(text))
            return true;
        return c.getEmail().contains(text);
    }

    @FXML
    private void addCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/addCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("הוספת חברה");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void viewCustomer() {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/viewCustomer.fxml"));
       /* ViewCustomer viewCustomer = fxmlLoader.getController();
        //ViewCustomer viewCustomer = new ViewCustomer();
        viewCustomer.setCustomer(list.getSelectionModel().getSelectedItem());*/
        //set controller manually - outside fxml file for
        //fxmlLoader.setController(viewCustomer);
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ViewCustomer.class, (Callable<ViewCustomer>) () -> new ViewCustomer(list.getSelectionModel().getSelectedItem()));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/viewCustomer.fxml"));
        fxmlLoader.setControllerFactory(param -> {
            Callable<?> callable = creators.get(param);
            try {
                return callable.call();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("כרטיס לקוח");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}