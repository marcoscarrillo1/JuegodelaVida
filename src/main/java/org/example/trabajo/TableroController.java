package org.example.trabajo;

import BucledeControl.Bucle;
import Excepciones.IDexistente;
import Individuo.Individuo;
//import Json.Json;
import Estructuras.ListaEnlazed;
import Tablero.Celdas;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;


public class TableroController {
    private JuegoVida juegoVida;

public int turnosDeJuego= 0;
    public GridPane tableroJuego;
    public Timeline control;
    public Button ventanParametros;
    public Button pausa;
    public Button play;
    public Button finalizarPartida;
    public Button guardarPartida;
    public Label turnosDeJuegoText;
    private ParameterDataModelProperties model;
    private Stage stage;

    public  ListaEnlazed<Celdas> celdas = new ListaEnlazed<>();
    private String tema;
    public ParameterDataModelProperties tablerodatamodel;




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
    public void buclecontroliniciar(){
        if(control==null){
            control=new Timeline(new KeyFrame(Duration.seconds(1),event->{
                if(juegoVida.getJuego()) {
                    JuegoVida elquecontrola = new JuegoVida(celdas);
                    elquecontrola.setModel(tablerodatamodel);
                    try {
                        juegoVida.bucledecontrol();
                    } catch (IDexistente e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    System.out.println("Se ha pausado el juego");
                    control.stop();
                }
            }));
            control.setCycleCount(Animation.INDEFINITE);
        }else{
            control.stop();
        }control.play();

    }


    public void pausarPartida() throws IDexistente {
        buclecontroliniciar();
        juegoVida.setJuego(true);

    }
    public void playJuego(){

    }

    public void guardarPartida() {


    }

    public void terminarPartida(ActionEvent actionEvent) {
    }

    public void jugarPartida(ActionEvent actionEvent) {
        juegoVida.setJuego(true);
        buclecontroliniciar();
    }
}
