module com.fxsczxcj.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires annotations;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;

    opens com.fxsczxcj.game2048;
    exports com.fxsczxcj.game2048;
}