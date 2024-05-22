package org.example.trabajo;

import Individuo.Individuo;
import Json.Json;
import Estructuras.ListaEnlazed;
import Tablero.Celdas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Individuo.Individuo;
import Individuo.IndividuoAvanzado;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import BucledeControl.JuegoVida;


public class TableroController {
    private JuegoVida juegoVida;

public int turnosDeJuego= 0;
    public GridPane tableroJuego;
    public Button ventanParametros;
    public Button pausa;
    public Button finalizarPartida;
    public Button guardarPartida;
    public Label turnosDeJuegoText;
    private ParameterDataModelProperties model;
    private Stage stage;

    public  ListaEnlazed<Celdas> celdas = new ListaEnlazed<>();
    private String tema;




    public void  setTableroController(ParameterDataModelProperties parametros) {
        model = parametros;
        juegoVida = new JuegoVida(this,false,model);
    }

    public void setStage(Stage stage) {
        this.stage= stage;

    }

    public int getTurnosDeJuego() {
        return turnosDeJuego;
    }

    public void setTurnosDeJuego(int turnosDeJuego) {
        this.turnosDeJuego = turnosDeJuego;
        turnosDeJuegoText.setText(STR."Turno: \{turnosDeJuego}");
    }

    @FXML
    public void CrearTablero() {

        int x = model.tableroFilasProperty().getValue().intValue();
        int y = model.tableroColumnasProperty().getValue().intValue();
        for (int j = 1; j <= y; j++) {
            for (int i = 1; i <= x; i++) {
                double ladox = (double) 400 /x;
                double ladoy = (double) 400 / y;
                Button celdaButton = new Button();
                celdaButton.setMaxWidth(ladoy);
                celdaButton.setMaxHeight(ladox);
                celdaButton.setMinHeight(ladox);
                celdaButton.setMinWidth(ladoy);
                celdaButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                int finalI = j;
                int finalJ = i;
                celdaButton.setOnAction(actionEvent -> onBotonCelda(finalI, finalJ));
                tableroJuego.add(celdaButton, finalI, finalJ );
                Celdas celdita = new Celdas(finalJ,finalI);
                celdas.add(celdita);


            }
        }
    }
@FXML
    private void onBotonCelda(int finalI, int finalJ) {
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(TableroController.class.getResource("CeldaView.fxml"));
            stage.setTitle("Celda: "+finalJ+","+finalI);
            Scene scene = new Scene(fxmlLoader.load(), 441, 399);

            stage.setScene(scene);
            ControllerCeldita controllerCeldita = fxmlLoader.getController();
            controllerCeldita.setStage(stage);
            controllerCeldita.setModel(model);
            controllerCeldita.setXY(finalI,finalJ);
            controllerCeldita.setCeldas(celdas);
            controllerCeldita.updateLabels();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ListaEnlazed<Celdas> getCeldas() {
        return celdas;
    }

    public  void setCeldas(ListaEnlazed<Celdas> celdotas) {
        celdas = celdotas;
    }

    public Celdas getcelditas(int id){
        return celdas.getElemento(id).getData();
    }

    @FXML
    public void onBotonCerrar(){
        stage.close();
    }

    public void volverParametros() {

    }


    public void pausarPartida() {
        juegoVida.setJuego(!juegoVida.getJuego());
        juegoVida.bucledecontrol();



    }

    public void guardarPartida() {


    }

    public void terminarPartida(ActionEvent actionEvent) {
    }
}
