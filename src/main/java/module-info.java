module nohagim {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires net.bytebuddy;
    requires java.xml.bind;
    requires java.instrument;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;

    opens nohagim to javafx.fxml;
    exports nohagim.Entities;
    exports nohagim;

}