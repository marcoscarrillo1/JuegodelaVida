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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("Pantallainicial.fxml")); // Va a cargar un fichero .fxml

        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Juego de la vida");
        stage.setScene(scene);
        stage.show();}
        catch (IOException e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch();
    }
}



