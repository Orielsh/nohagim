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