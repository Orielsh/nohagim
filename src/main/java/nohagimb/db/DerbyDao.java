package nohagimb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDao implements Dao{

    private static final String DEFAULT_PROTOCOL = "jdbc:derby:";
    private static final String DEFAULT_DBNAME = "db//NohagimB";    //db files location
    private static final String DEFAULT_DRIVERNAME = "org.apache.derby.jdbc.EmbeddedDriver";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DEFAULT_DRIVERNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DEFAULT_PROTOCOL + DEFAULT_DBNAME + ";create=true");
    }


}
