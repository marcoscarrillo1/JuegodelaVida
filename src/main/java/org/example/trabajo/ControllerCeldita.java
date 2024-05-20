package org.example.trabajo;

import Estructuras.ListaEnlazed;
import Individuo.IndividuoAvanzado;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Recursos.*;
import Tablero.Celdas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class ControllerCeldita {
    private int x,y;
    public Button delBasico;
    public Button addBasico;
    public Button delNormal;
    public Button addNormal;
    public Button addAvanzado;
    public Button delAvanzado;
    public Button delAgua;
    public Button addAgua;
    public Button delMontaña;
    public Button delBiblioteca;
    public Button addMontana;
    public Button addBiblioteca;
    public Button addTesoro;
    public Button delTesoro;
    public Button delPozo;
    public Button addPozo;
    public Button delComida;
    public Button addComida;
    public Label numAgua;
    public Label numComida;
    public Label numTesoro;
    public Label numPozo;
    public Label numMontana;
    public Label numBiblioteca;
    public Label numNormal;
    public Label numBasico;
    public Label numAvanzado;
    public Button infoBasico;
    public Button infoNormal;
    public Button infoAvanzado;
    private ParameterDataModelProperties model;
    public ListaEnlazed<Celdas> celdas = new ListaEnlazed<Celdas>();

    public void setCeldas(ListaEnlazed<Celdas> celdas) {
        this.celdas = celdas;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(ParameterDataModelProperties model) {
        this.model = model;
    }

    public void addIndividuo(Class clase){
        int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);
        Celdas celda = celdas.getElemento(pos).getData();
        if (celda.getIndividuoListaEnlazed().getNumeroElementos() < 3){
            if (Objects.equals(clase.descriptorString(), "IndividuoBasico")){

            }
        }


    }
}
