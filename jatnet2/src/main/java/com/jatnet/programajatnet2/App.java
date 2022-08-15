package com.jatnet.programajatnet2;

import com.jatnet.programajatnet2.dao.ComercioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String Ruta ="images/";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
     
        scene = new Scene(loadFXML("login"), 640, 480);
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

    public static void main(String[] args) {
           try {
            com.jatnet.programajatnet2.model.Comercio.getInstance(new ComercioDAO().getComercio());
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
       launch();
    }

}