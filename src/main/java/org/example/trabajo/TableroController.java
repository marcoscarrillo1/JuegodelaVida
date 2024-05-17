package org.example.trabajo;

import Estructuras.ListaEnlazed;

import Tablero.Stack;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TableroController {
@FXML
    public GridPane tableroJuego;
    private ParameterDataModelProperties model;
    private Stage stage;
    public ListaEnlazed<Stack> stackis = new ListaEnlazed<Stack>();

    String tema;


    public void  setTableroController(ParameterDataModelProperties parametros) {
        model = parametros;
    }

    public void setStage(Stage stage) {
        this.stage= stage;

    }
    @FXML
    public void CrearTablero() {
        int x = model.tableroFilasProperty().getValue().intValue();
        int y = model.tableroColumnasProperty().getValue().intValue();
        for (int j = y; j > 0; j--) {
            for (int i = 0; i < x; i++) {
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 500/ x, (double) 500 / y);
                celdaButton.setMaxSize((double) 500/ x, (double) 500 / y);
                celdaButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                int finalI = i;
                int finalJ = j;
                celdaButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //onBotonCelda(finalI, finalJ);
                    }
                });
                tableroJuego.add(celdaButton, finalI, finalJ - 1);
                //stackis.getElemento(finalJ - 1).getData().getElemento(finalI).getData().setBoton(celdaButton);

            }
        }
    }
    @FXML
    public void onBotonCerrar(){
        stage.close();
    }
}
