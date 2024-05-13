package org.example.trabajo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Iniciadorjuego extends Application {
    //private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("Pantallainicial.fxml")); // Va a cargar un fichero .fxml
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Juego de la vida");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



