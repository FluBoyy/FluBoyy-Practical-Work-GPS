module pt.isec.gps2324.gps_g14 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires eu.hansolo.tilesfx;

    requires java.sql;
    requires ormlite.jdbc;
    requires jbcrypt;
    opens pt.isec.gps2324.gps_g14.App.API.Entities to ormlite.jdbc;
    exports pt.isec.gps2324.gps_g14.App to javafx.graphics;

    opens pt.isec.gps2324.gps_g14.ConsoleApp.Modules;
    opens pt.isec.gps2324.gps_g14.App.Controllers;
}