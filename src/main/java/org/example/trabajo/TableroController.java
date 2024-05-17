package org.example.trabajo;

import Estructuras.ListaEnlazed;
import Estructuras.ListaSimple;
import Tablero.Stack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController {
@FXML
    public GridPane tableroJuego;
    private ParameterDataModel model;
    private Stage stage;

    String tema;
    public ListaEnlazed<Stack> stackis = new ListaEnlazed<>();

    public void  setTableroController(ParameterDataModel parametros) {
        model = parametros;
    }

    public void setStage(Stage stage) {
        this.stage= stage;

    }
    @FXML
    public void CrearTablero() {
        int filas = model.getTableroFilas();
        int columnas = model.getTableroColumnas();
        int id = 0;
        for (int i = 0 ; i<columnas;i++){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            tableroJuego.getColumnConstraints().add((columnConstraints));

        }
        for (int j= 0; j<filas;j++){
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            tableroJuego.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                Stack stack = new Stack(i, j);
                stack.setId(id);
                stack.setPrefHeight(100);
                stack.setPrefHeight(100);
                stack.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                stack.prefWidthProperty().bind(tableroJuego.widthProperty().divide(columnas));
                stack.prefHeightProperty().bind(tableroJuego.heightProperty().divide(filas));
                //setTema(stack, tema);
                tableroJuego.add(stack, i, j);
                stackis.add(stack);
                id++;

            }
        }
    }
    @FXML
    public void onBotonCerrar(){
        stage.close();
    }
}
