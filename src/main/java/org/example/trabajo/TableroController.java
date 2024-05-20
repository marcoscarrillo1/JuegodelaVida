package org.example.trabajo;

import Estructuras.ListaEnlazed;

import Tablero.Celdas;
import Tablero.Stack;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TableroController {
@FXML
    public GridPane tableroJuego;
    private ParameterDataModelProperties model;
    private Stage stage;
    public ListaEnlazed<Celdas> celdas = new ListaEnlazed<Celdas>();

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
        for (int j = 1; j <= y; j++) {
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
                        onBotonCelda(finalI, finalJ);
                    }
                });
                tableroJuego.add(celdaButton, finalI, finalJ );
                Celdas celdita = new Celdas(finalI,finalJ);
                celdas.add(celdita);


            }
        }
    }

    private void onBotonCelda(int finalI, int finalJ) {
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("CeldaView.fxml"));
            stage.setTitle("Celda: "+finalI+","+finalJ);
            Scene scene = new Scene(fxmlLoader.load(), 820, 640);

            stage.setScene(scene);
            ControllerCeldita controllerCeldita = fxmlLoader.getController();
            controllerCeldita.setModel(model);
            controllerCeldita.setStage(stage);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ListaEnlazed<Celdas> getCeldas() {
        return celdas;
    }
    public Celdas getcelditas(int id){
        return celdas.getElemento(id).getData();
    }

    @FXML
    public void onBotonCerrar(){
        stage.close();
    }
}
