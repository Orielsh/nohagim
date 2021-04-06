package nohagim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nohagim.Entities.Customer;
import nohagim.util.BlobUtil;
import nohagim.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("fxml/MainScreen"), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {
        /*Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Customer customer = new Customer();
        customer.setId(547982135);
        customer.setname("ASD");
        customer.setJoinDate(new Date());   //default = current date
        byte[] bytes = BlobUtil.getBytes("src/main/resources/nohagim/icons/Arrow.jpg");*/

        /*customer.setTransportationLicence(new SerialBlob(bytes));*/

        //System.out.println(bytes[1]);
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        /*s.save(customer);
        t.commit();*/

        launch();
    }

}

/*//todo:   1. validate blob column size.
            2. make windows order - when adding customer there are too many
            open windows.
            3. add "add customer" in main menu.
            4.validate fields when update / adding records.
            5. decide what nullable or not.
            6. make one static instance of dao for all usages. also, ask for better architecture
            7. pass on lists in "CustomerLists" sketch and see if can be optimized.
            8. make lazy and eager while loading lists and data
            9. make global list of "Customers" or a class for maintain.
            10. in adding customer. when set number (fax/ .. ) not show 0-begin




 */
