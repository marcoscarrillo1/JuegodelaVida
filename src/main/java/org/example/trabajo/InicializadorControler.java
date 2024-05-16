package org.example.trabajo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.stage.Stage;

import java.io.IOException;


public class InicializadorControler {
    public Button cerrar;
    private ParameterDataModel model = new ParameterDataModel(4,6,7,8,9,7,3,1,4,6,8,7,8);
    private ParameterDataModelProperties model_observable = new ParameterDataModelProperties(model);

    @FXML
    protected void cargarPartidaClick() throws IOException {




        }
    @FXML
    protected void nuevaPartidaClick () {
        Stage stage1 = (Stage) cerrar.getScene().getWindow();
        stage1.close();
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("PantallaParametros.fxml"));
            stage.setTitle("Establezca parámetros: ");
            Scene scene = new Scene(fxmlLoader.load(), 820, 640);

            stage.setScene(scene);
            ParameterControler parametrosContralor = fxmlLoader.getController();
            parametrosContralor.loadUserData(model_observable);
            parametrosContralor.setStage(stage);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }}



