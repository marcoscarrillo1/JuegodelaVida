package org.example.trabajo;

import BucledeControl.Bucle;
import Excepciones.IDexistente;
import BucledeControl.JuegoVida;
import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Json.Json;
import Recursos.Recursos;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Json.DatosInteres;
import Recursos.Agua;
import Recursos.Tesoro;
import Recursos.Pozo;
import Recursos.Montaña;
import Recursos.Comida;
import Recursos.Biblioteca;



import javafx.util.Duration;
import org.apache.logging.log4j.Logger;

import java.security.cert.PolicyNode;
import java.util.Objects;

import static org.example.trabajo.ControllerCeldita.log;


public class TableroController {
    private JuegoVida juegoVida;

public int turnosDeJuego= 0;


    private ParameterDataModel parametrosData = new ParameterDataModel(5, 10, 5,8,8,5,5,5,5,5,5,5,5);
   private ParameterDataModelProperties modeloParaGUICompartido= new ParameterDataModelProperties(parametrosData);
    public GridPane tableroJuego;
    public Timeline control;
    public ListaEnlazed<StackPane> stacks= new ListaEnlazed<>();
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
                Background transparentBackground = new Background(new BackgroundFill(
                        Color.TRANSPARENT, CornerRadii.EMPTY, null));
                celdaButton.setBackground(transparentBackground);
                celdaButton.setMaxWidth(ladoy);
                celdaButton.setMaxHeight(ladox);
                celdaButton.setMinHeight(ladox);
                celdaButton.setMinWidth(ladoy);
                celdaButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                int finalI = j;
                int finalJ = i;
                celdaButton.setOnAction(actionEvent -> onBotonCelda(finalI, finalJ));

                tableroJuego.add(celdaButton, finalI, finalJ );
                ListaEnlazed<Recursos> re= new ListaEnlazed<>();
                ListaEnlazed<Individuo> in = new ListaEnlazed<>();
                Celdas celdita = new Celdas(finalJ,finalI,re,in);
                celdita.setBoton(celdaButton);
                celdas.add(celdita);


            }
        }
    }


    public void actualizarColor(){
       for(int i = 0; i< celdas.getNumeroElementos();i++){
       Celdas celda = celdas.getElemento(i).getData();
        int Individuos = celda.getIndividuoListaEnlazed().getNumeroElementos();
        celda.setBoton(celda.getBoton());
        if(celda.getBoton()!=null){
        if(Individuos<=3){
            if(Individuos==0&&celda.getRecursosListaEnlazed().isVacia()){
                celda.getBoton().setStyle("-fx-background-color: #ffffff ; -fx-border-color:#ffffff");
            }
            if(Individuos ==1 ){
                celda.getBoton().setStyle("-fx-background-color: #ffc300 ; -fx-border-color:#ffd500");
            } else if (Individuos == 2) {
                celda.getBoton().setStyle("-fx-background-color: #ff8000 ; -fx-border-color:#ff7400 ");
            } else if (Individuos==3) {
                celda.getBoton().setStyle("-fx-background-color: #ff0000 ; -fx-border-color:#ff0101");

            }
        } if(Individuos==0&& !celda.getRecursosListaEnlazed().isVacia()){
            if(celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()== Agua.class){
                celda.getBoton().setStyle("-fx-background-color: #00fff7 ; -fx-border-color:#00fff7");
            } else if (celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()== Comida.class) {
                celda.getBoton().setStyle("-fx-background-color: #5a7227 ; -fx-border-color:#5a7227");
            }else if (celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()==Biblioteca.class){
                celda.getBoton().setStyle("-fx-background-color: #8a5928 ; -fx-border-color:#8a5928");
            }else if (celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()== Montaña.class){
                celda.getBoton().setStyle("-fx-background-color: #332820 ; -fx-border-color:#332820");
            }else if (celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()== Tesoro.class){
                celda.getBoton().setStyle("-fx-background-color: #d7d70e ; -fx-border-color:#d7d70e");
            }else if (celda.getRecursosListaEnlazed().getElemento(0).getData().getClass()==Pozo.class){
                celda.getBoton().setStyle("-fx-background-color: #202062 ; -fx-border-color:#202062");
            }}
        }}}
@FXML
    private void onBotonCelda(int finalI, int finalJ) {
        if(!juegoVida.getJuego()){
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
        }}

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
@FXML
    public void volverParametros() {
    log.info("has entrado a parameter controller");
    ;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TableroController.class.getResource("PantallaParametros.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Establezca parámetros: ");
                stage.setScene(scene);
                ParameterControler p = fxmlLoader.getController();
                p.loadUserData(this.modeloParaGUICompartido);
                p.tab.setDisable(true);
                p.setStage(stage);
                stage.show();
                log.info("terminando la pestaña de nueva partida ");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("No se ha podido iniciar");
            }



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
            control.play();
        }

    }


    public void pausarPartida() throws IDexistente {
        juegoVida.setJuego(false);

    }
    public void playJuego(){

    }
    public void guardarDatos(){
        DatosInteres x = new DatosInteres(model.turnosVidaProperty().getValue().intValue(),model.mutacionProperty().getValue().intValue(),
                model.reproduccionProperty().getValue().intValue(),model.tableroFilasProperty().getValue().intValue(),model.tableroColumnasProperty().getValue().intValue()
                ,model.tiempoApariconProperty().getValue().intValue(),model.porpApariconProperty().getValue().intValue(),model.propAguaProperty().getValue().intValue()
                ,model.propBibliotecaProperty().getValue().intValue(),model.propMontanaProperty().getValue().intValue(),
                model.propComidaProperty().getValue().intValue(),model.propPozoProperty().getValue().intValue(),
                model.propTesoroProperty().getValue().intValue(),celdas);
        Json.guardarObjetoEnArchivo("datosPartida.json",x);
    }

    public void guardarPartida() {
        if(!juegoVida.getJuego()){guardarDatos();}



    }
@FXML
    public void terminarPartida(ActionEvent actionEvent) {
        juegoVida.setJuego(false);
        Stage stage1 = (Stage) finalizarPartida.getScene().getWindow();
        stage1.close();
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("Pantallafinal.fxml"));
            stage.setTitle("Final de Juego");
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);

            stage.setScene(scene);
            PantallaFinalControler Pantallafinal = fxmlLoader.getController();





            stage.show();
        } catch (Exception e) {
            e.printStackTrace();


        }}
    public void pantallafinal(){

    }

    public void jugarPartida(ActionEvent actionEvent) {
        juegoVida.setJuego(true);
        buclecontroliniciar();
    }
}
