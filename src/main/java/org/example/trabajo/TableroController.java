package org.example.trabajo;

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

    public GridPane tableroJuego;
    private ParameterDataModel model;
    private Stage stage;

    String tema;
    public ListaSimple<Stack> stackis = new ListaSimple<Stack>();

    public void  setTableroController(ParameterDataModel parametros) {
        model = parametros;
    }

    public void setStage(Stage stage) {
        this.stage= stage;

    }
    public void CrearTablero() {
        int filas = model.getTableroFilas();
        int columnas = model.getTableroColumnas();
        int id = 0;
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                Stack stack = new Stack(i, j);
                stack.setId(id);
                stack.setPrefWidth(100);
                stack.setPrefHeight(100);
                stack.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
