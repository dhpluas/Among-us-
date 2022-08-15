module com.jatnet.programajatnet2 {
 
requires javafx.controls;
    requires javafx.fxml;
    requires jsch;
    requires javafx.swing;
    requires com.jfoenix;
    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.javafx;
    requires org.controlsfx.controls;
    requires AnimateFX;
    requires java.base;
    requires java.sql;
   requires webcam.capture;
   requires jfilechooser.bookmarks;
   requires javafx.base;
   requires javafx.baseEmpty;
   requires jasperreports;
  



    opens com.jatnet.programajatnet2 to javafx.fxml;
    exports com.jatnet.programajatnet2;
}