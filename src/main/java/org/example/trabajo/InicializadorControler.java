package org.example.trabajo;
import Json.Json;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.stage.Stage;
import Json.DatosInteres;

import java.io.IOException;


public class
InicializadorControler {
    public Button cerrar;
    private ParameterDataModel model = new ParameterDataModel(4,6,7,8,9,7,60,1,4,6,8,7,8);
    private ParameterDataModelProperties model_observable = new ParameterDataModelProperties(model);

    @FXML
    protected void cargarPartidaClick()  {
        Stage stage1 = (Stage) cerrar.getScene().getWindow();
        stage1.close();
        try {
            DatosInteres cargado = Json.cargarObjetoDesdeArchivo("datosPartida.json", DatosInteres.class);
            model.setTurnosVida(cargado.getTurnosVida());
            model.setMutacion(cargado.getMutacion());
            model.setReproducion(cargado.getReproducion());
            model.setTableroFilas(cargado.getTableroFilas());
            model.setTableroColumnas(cargado.getTableroColumnas());
            model.setTiempoApracion(cargado.getTiempoApracion());
            model.setProbAparicion(cargado.getProbAparicion());
            model.setPropAgua (cargado.getPropAgua());
            model.setPropBiblioteca(cargado.getPropBiblioteca());
            model.setPropMontana (cargado.getPropMontana());
            model.setPropComida(cargado.getPropComida());
            model.setPropPozo (cargado.getPropPozo());
            model.setPropTesoro(cargado.getPropTesoro());
            model_observable.setOriginal(model);

            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("Tablero.fxml"));
            stage.setTitle("Juego de la Vida");
            Scene scene = new Scene(fxmlLoader.load(), 720, 500);

            stage.setScene(scene);
            TableroController tableroController = fxmlLoader.getController();
            tableroController.setTableroController(model_observable);
            tableroController.setStage(stage);

            tableroController.CrearTablero();
            tableroController.setCeldas(cargado.getCelda());






            stage.show();
        } catch (Exception e) {
            e.printStackTrace();


        }



    }
    @FXML
    protected void nuevaPartidaClick () {
        Stage stage1 = (Stage) cerrar.getScene().getWindow();
        stage1.close();
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("PantallaParametros.fxml"));
            stage.setTitle("Establezca parámetros: ");
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            stage.setScene(scene);
            ParameterControler parametrosContralor = fxmlLoader.getController();
            parametrosContralor.loadUserData(model_observable);
            parametrosContralor.setStage(stage);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }}



