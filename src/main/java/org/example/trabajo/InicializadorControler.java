package org.example.trabajo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class InicializadorControler {
    @FXML
    protected void cargarPartidaClick() throws IOException {




        }
        @FXML
        protected void nuevaPartidaClick () throws IOException {
            try {
                System.out.println(Iniciadorjuego.class.getResource("PantallaParametros.fxml"));

                Stage stage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("PantallaParametros.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 820, 640);
                stage.setTitle("Establezca parámetros: ");
                stage.setScene(scene);
                stage.show();}catch (IOException e){
                e.printStackTrace();
            }


        }

    }

