package org.example.trabajo;

import Individuo.IndividuoAvanzado;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Recursos.*;
import Tablero.Celdas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerCeldita {
    private ParameterDataModelProperties model;
    private static Celdas celdacontroller;
    private IndividuoBasico Individuobasico;
    private IndividuoNormal Individuonormal;
    private IndividuoAvanzado Individuoavanzado;
    private Agua agua;
    private Biblioteca biblioteca;
    private Comida comida;
    private Montaña montaña;
    private Pozo pozo;
    private Tesoro tesoro;
    private Stage stage;
    @FXML
    private Button BotonIndividuobasico;

    public Stage getStage() {
        return stage;
    }
    @FXML
    protected void onBottonAgua(){
        if(celdacontroller.RecursosisCompleto==Boolean.FALSE) {
        }else{
         celdacontroller.getRecursosListaEnlazed().add(agua);
         agua.setDatos(recurso)
            }

    }
    @FXML
    protected void onBottonIndividuoBasico(){
        if(celdacontroller.Individuosiscompleto()==Boolean.TRUE){
            System.out.println("No se añade");
        }else{
            celdacontroller.getIndividuoListaEnlazed().add(Individuobasico);
            System.out.println("Se añade");
            Individuobasico.setDatos(individuosc)
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(ParameterDataModelProperties model) {
        this.model = model;
    }
}
