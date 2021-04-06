package nohagim.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nohagim.Entities.Customer;
import nohagim.Entities.Entity;
import nohagim.dao.GenericDAOImpl;
import nohagim.dao.IGenericDAO;
import nohagim.util.BlobUtil;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddCustomer implements Initializable {

    private static final Pattern INTEGER_MATCH = Pattern.compile("[\\d]*");
    @FXML
    Label professionalManagerDocPath;
    private File professionalManagerDoc;
    private GenericDAOImpl dao = new GenericDAOImpl();
    @FXML
    private TextField customerId;
    @FXML
    private TextField name;
    @FXML
    private TextField contactPerson;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField faxNumber;
    @FXML
    private TextField email;
    @FXML
    private DatePicker joinDate;
    @FXML
    private TextField professionalManagerName;
    @FXML
    private Button save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerId.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }
            return (INTEGER_MATCH.matcher(c.getControlNewText()).matches()) ? c : null;
        }));
        customerId.setOnKeyTyped(keyEvent -> {
            if (customerId.getText().length() == 9)
                customerId.setStyle("-fx-background-color: rgba(0,255,0,0.10)");
            else
                customerId.setStyle("-fx-background-color: rgba(255,0,0,0.10)");
        });
        joinDate.setEditable(false);
    }

    @FXML
    private void chooseFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        professionalManagerDoc = fileChooser.showOpenDialog(stage);
        professionalManagerDocPath.setText(professionalManagerDoc.getPath());
    }

    @FXML
    private void save() {
        if (validate()) {
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(customerId.getText()));
            customer.setName(name.getText());
            customer.setContactPerson(contactPerson.getText());
            customer.setAddress(address.getText());
            customer.setPhoneNumber(Integer.parseInt(phoneNumber.getText())); // todo: may not be only numbers.
            customer.setFaxNumber(Integer.parseInt(faxNumber.getText()));
            customer.setEmail(email.getText());
            customer.setJoinDate(joinDate.getValue());
            customer.setProfessionalManagerName(professionalManagerName.getText());
            try {
                customer.setProfessionalManagerDoc(new SerialBlob(BlobUtil.getBytes(professionalManagerDoc)));
            } catch (Exception e) {
                System.out.println("Unable convert file bytes.");   //todo: logging + meaningful handling.
            }
            dao.save(customer);

            ((Stage) (save.getScene().getWindow())).close();
        }
    }

    private boolean validate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (customerId.getText().length() != 9) {
            alert.setContentText("מזהה חברה קצר");
            alert.show();
            return false;
        }
        if (name.getText().length() == 0) {
            alert.setContentText("הכנס שם חברה!");
            alert.show();
            return false;
        }
        if (contactPerson.getText().length() == 0) {
            alert.setContentText("הכנס פרטי איש קשר!");
            alert.show();
            return false;
        }
        if (address.getText().length() == 0) {
            alert.setContentText("הכנס כתובת!");
            alert.show();
            return false;
        }
        if (phoneNumber.getText().length() == 0) {
            alert.setContentText("הכנס מספר טלפון!");
            alert.show();
            return false;
        }
        if (faxNumber.getText().length() == 0) {
            alert.setContentText("הכנס מספר פקס!");
            alert.show();
            return false;
        }
        if (email.getText().length() == 0) {
            alert.setContentText("הכנס דואר אלקטרוני!");
            alert.show();
            return false;
        }
        if (joinDate.getValue() == null) {
            alert.setContentText("הכנס תאריך הצטרפות!");
            alert.show();
            return false;
        }
        if (professionalManagerName.getText().length() == 0) {
            alert.setContentText("הכנס שם מנהל מקצועי!");
            alert.show();
            return false;
        }
        if (professionalManagerDoc == null) {
            alert.setContentText("טען קובץ תעודה של מנהל מקצועי!");
            alert.show();
            return false;
        } else {
            String fileName = professionalManagerDoc.getName();
            if (fileName.length() > 5) {
                char end = fileName.charAt(fileName.length() - 1); // todo replace contain with equal.
                String extension = fileName.substring(fileName.length() - 5);
                if (!(extension.contains(".jpeg") || extension.contains(".pdf"))) {
                    alert.setContentText("קובץ תעודה של מנהל מקצועי לא תקין");
                    alert.show();
                    return false;
                }
            } else {
                alert.setContentText("קובץ תעודה של מנהל מקצועי לא תקין");
                alert.show();
                return false;
            }
        }
        return true;
    }
}

//todo: while adding through customers list -> refresh list.
