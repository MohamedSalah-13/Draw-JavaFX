module com.hamza.draw.draw {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.jfoenix;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires lombok;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires extension;

    opens com.hamza.draw.draw to javafx.fxml;
    exports com.hamza.draw.draw;

    exports com.hamza.draw.test;
    opens com.hamza.draw.test to javafx.fxml;

    exports com.hamza.draw.image;
    exports com.hamza.draw.model;
}