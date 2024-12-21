module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.google.gson;
    requires com.fasterxml.jackson.core;
    opens gui to javafx.fxml;
    //opens main to javafx.fxml;
    exports gui;

    exports domain to com.fasterxml.jackson.databind,com.google.gson;
    opens domain to com.fasterxml.jackson.databind,com.google.gson;

    //exports main;
}