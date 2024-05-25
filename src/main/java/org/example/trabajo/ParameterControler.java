package org.example.trabajo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ParameterControler implements Initializable {


    public Button iniciarJuego;
    public AnchorPane tab;
    @FXML
    private Slider sliderTurnosVIda;
    @FXML
    private Slider sliderMutacion;
    @FXML
    private Slider sliderReproducion;
    @FXML
    private Slider sliderTableroFilas;
    @FXML
    private Slider sliderTableroColumnas;
    @FXML
    private Slider sliderTiempoApracion;
    @FXML
    private Slider sliderProbAparicion;
    @FXML
    private Slider sliderPropAgua;
    @FXML
    private Slider sliderPropBiblioteca;
    @FXML
    private Slider sliderPropMontana;
    @FXML
    private Slider sliderPropComida;
    @FXML
    private Slider sliderPropPozo;
    @FXML
    private Slider sliderPropTesoro;
    private ParameterDataModelProperties model;
    private Stage scene;

    public void loadUserData(ParameterDataModelProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }
    @FXML
    protected void onBotonGuardarClick() {
       if(model != null){ model.commit();

       }
    }

    @FXML
    protected void onBotonReiniciarClick() {
        if(model != null){ model.rollback();
        }
    }

    @FXML protected void onBotonCerrarClick(){
        scene.close();
    }

    /**
     * Métodos de configuración
     **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");


    }

    /**
     * Este método se encarga de conectar los datos del modelo con el GUI
     **/
    protected void updateGUIwithModel() {
        sliderTurnosVIda.valueProperty().bindBidirectional(model.turnosVidaProperty());
        sliderMutacion.valueProperty().bindBidirectional(model.mutacionProperty());
        sliderReproducion.valueProperty().bindBidirectional(model.reproduccionProperty());
        sliderTableroFilas.valueProperty().bindBidirectional(model.tableroFilasProperty());
        sliderTableroColumnas.valueProperty().bindBidirectional(model.tableroColumnasProperty());
        sliderTiempoApracion.valueProperty().bindBidirectional(model.tiempoApariconProperty());
        sliderProbAparicion.valueProperty().bindBidirectional(model.porpApariconProperty());
        sliderPropAgua.valueProperty().bindBidirectional(model.propAguaProperty());
        sliderPropBiblioteca.valueProperty().bindBidirectional(model.propBibliotecaProperty());
        sliderPropMontana.valueProperty().bindBidirectional(model.propMontanaProperty());
        sliderPropComida.valueProperty().bindBidirectional(model.propComidaProperty());
        sliderPropPozo.valueProperty().bindBidirectional(model.propPozoProperty());
        sliderPropTesoro.valueProperty().bindBidirectional(model.propTesoroProperty());
    }

    /**
     * Este método recibe los datos del modelo y los establece
     **/


    public void setStage(Stage s){
        this.scene = s;
    }

    public void onBotonJugarClick() {
        Stage stage1 = (Stage) iniciarJuego.getScene().getWindow();
        stage1.close();
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Iniciadorjuego.class.getResource("Tablero.fxml"));
            stage.setTitle("Juego de la Vida");
            Scene scene = new Scene(fxmlLoader.load(), 720, 600);

            stage.setScene(scene);
            TableroController tableroController = fxmlLoader.getController();
            tableroController.setTableroController(model);
            tableroController.setStage(stage);

            tableroController.CrearTablero();



            stage.show();
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

}

