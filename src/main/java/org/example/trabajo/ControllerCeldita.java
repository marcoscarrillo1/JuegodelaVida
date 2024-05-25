package org.example.trabajo;

import BucledeControl.JuegoVida;
import Estructuras.ElementoLe;
import Estructuras.Generacion;
import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Individuo.IndividuoAvanzado;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Recursos.*;
import Tablero.Celdas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCeldita {
    @FXML
    public Label avisos;
    @FXML
    public Button guardar;
    private int x,y;
    @FXML
    public Button delBasico;
    @FXML
    public Button addBasico;
    @FXML
    public Button delNormal;
    @FXML
    public Button addNormal;
    @FXML
    public Button addAvanzado;
    @FXML
    public Button delAvanzado;
    @FXML
    public Button delAgua;
    @FXML
    public Button addAgua;
    @FXML
    public Button delMontaña;
    @FXML
    public Button delBiblioteca;
    @FXML
    public Button addMontana;
    @FXML
    public Button addBiblioteca;
    @FXML
    public Button addTesoro;
    @FXML
    public Button delTesoro;
    @FXML
    public Button delPozo;
    @FXML
    public Button addPozo;
    @FXML
    public Button delComida;
    @FXML
    public Button addComida;
    @FXML
    public Label numAgua;
    @FXML
    public Label numComida;
    @FXML
    public Label numTesoro;
    @FXML
    public Label numPozo;
    @FXML
    public Label numMontana;
    @FXML
    public Label numBiblioteca;
    @FXML
    public Label numNormal;
    @FXML
    public Label numBasico;
    @FXML
    public Label numAvanzado;
    @FXML
    public Button infoBasico;
    @FXML
    public Button infoNormal;
    @FXML
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
        avisos.setText("");
    }


    public void updateLabels(){
        int columnas = model.tableroColumnasProperty().getValue().intValue();
        int filas = model.tableroFilasProperty().getValue().intValue();
        int pos = (columnas - x)* filas + (filas-y);
        Celdas celda = celdas.getElemento(pos).getData();

        int basicos = 0;
        int normal = 0;
        int avanzado = 0;
        if (!celda.getIndividuoListaEnlazed().isVacia()){
            for(int i = 0; i < celda.getIndividuoListaEnlazed().getNumeroElementos();i++){
            if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoBasico;")){
                basicos++;}
                numBasico.setText(String.valueOf(basicos));
             if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoNormal;")){
                normal++;}
                numNormal.setText(String.valueOf(normal));

           if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoAvanzado;")){
                avanzado++;}
                numAvanzado.setText(String.valueOf(avanzado));

            }}else {
            numBasico.setText("0");
            numNormal.setText("0");
            numAvanzado.setText("0");
        }


        int agua = 0;
        int montaña = 0;
        int biblioteca = 0;
        int tesoro= 0;
        int pozo = 0;
        int comida= 0;
        if(!celda.getRecursosListaEnlazed().isVacia()){for(int i = 0; i < celda.getRecursosListaEnlazed().getNumeroElementos();i++){
            if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Agua;")){
                agua++;}
                numAgua.setText(String.valueOf(agua));
             if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Montaña;")){
                montaña++;}
                numMontana.setText(String.valueOf(montaña));

           if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Pozo;")){
                pozo++;}
                numPozo.setText(String.valueOf(pozo));

           if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Comida;")){
                comida++;}
                numComida.setText(String.valueOf(comida));

           if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Tesoro;")) {
                tesoro++;}
                numTesoro.setText(String.valueOf(tesoro));
            if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Biblioteca;")){
                biblioteca++;}
                numBiblioteca.setText(String.valueOf(biblioteca));}}else {
            numTesoro.setText("0");
            numAgua.setText("0");
            numComida.setText("0");
            numPozo.setText("0");
            numMontana.setText("0");
            numBiblioteca.setText("0");
        }
        System.out.println("Se actualizo");
    }




    public void generarID(Individuo indi) {
        int id = 0;
        for (int j = 0; j < celdas.getNumeroElementos(); j++) {
            for (int i = 0; i <celdas.getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                for (int x = 0; x < celdas.getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); x++) {
                    Individuo individuo = celdas.getElemento(j).getData().getIndividuoListaEnlazed().getElemento(x).getData();
                    if (individuo.getIdentificador() > id) {
                        id = individuo.getIdentificador();
                    }
                }
            }
        }indi.setIdentificador(id+1);
    }



    public void addIndividuo(Class clase){
        int columnas = model.tableroColumnasProperty().getValue().intValue();
        int filas = model.tableroFilasProperty().getValue().intValue();
        int pos = (columnas - x)* filas + (filas-y);
        Celdas celda = celdas.getElemento(pos).getData();
        if (celda.getIndividuoListaEnlazed().getNumeroElementos() < 3){
            if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoBasico;")){
                IndividuoBasico nuevoB = new IndividuoBasico(model);
                Generacion arbol=new Generacion();
                nuevoB.setTipo(1);
                generarID(nuevoB);
                arbol.setPadre(null);
                arbol.setMadre(null);


                celda.addIndividuo(nuevoB);

            } else if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoNormal;")) {
                IndividuoNormal nuevoN = new IndividuoNormal(model);
                Generacion arbol=new Generacion();
                nuevoN.setTipo(2);
                generarID(nuevoN);

                arbol.setPadre(null);
                arbol.setMadre(null);
                celda.addIndividuo(nuevoN);


            } else if (Objects.equals(clase.descriptorString(),"LIndividuo/IndividuoAvanzado;")) {
                IndividuoAvanzado nuevoA = new IndividuoAvanzado(model);
                Generacion arbol=new Generacion();
                nuevoA.setTipo(3);
                generarID(nuevoA);
                arbol.setPadre(null);
                arbol.setMadre(null);
                celda.addIndividuo(nuevoA);

            }
            avisos.setText("Individuo añadido");

        }else {
            avisos.setText("Nose puede añadir individuo casilla llena");

        }
        updateLabels();


    }
    public void addRecurso(Class clase){
        int columnas = model.tableroColumnasProperty().getValue().intValue();
        int filas = model.tableroFilasProperty().getValue().intValue();
        int pos = (columnas - x)* filas + (filas-y);
        Celdas celda = celdas.getElemento(pos).getData();
        if (celda.getRecursosListaEnlazed().getNumeroElementos() < 3){
            if (Objects.equals(clase.descriptorString(), "LRecursos/Agua;")){
                Agua nuevoA = new Agua(model);
                celda.addRecurso(nuevoA);


            } else if (Objects.equals(clase.descriptorString(), "LRecursos/Biblioteca;")) {
                Biblioteca nuevoB = new Biblioteca(model);
                celda.addRecurso(nuevoB);

            } else if (Objects.equals(clase.descriptorString(),"LRecursos/Comida;")) {
                Comida nuevoC= new Comida(model);
                celda.addRecurso(nuevoC);

            }else if (Objects.equals(clase.descriptorString(),"LRecursos/Montaña;")) {
                Montaña nuevoM= new Montaña(model);
                celda.addRecurso(nuevoM);

            }else if (Objects.equals(clase.descriptorString(),"LRecursos/Pozo;")) {
                Pozo nuevoP= new Pozo(model);
                celda.addRecurso(nuevoP);

            }else if (Objects.equals(clase.descriptorString(),"LRecursos/Tesoro;")) {
                Tesoro nuevoT= new Tesoro(model);
                celda.addRecurso(nuevoT);

            }avisos.setText("Recurso añadido");

        }else {
                avisos.setText("No se puede añadir recurso casilla completa");
            }
        updateLabels();


    }
    protected ListaEnlazed<Individuo> informacion(Class clase){
        int columnas = model.tableroColumnasProperty().getValue().intValue();
        int filas = model.tableroFilasProperty().getValue().intValue();
        int pos = (columnas - x)* filas + (filas-y);
        Celdas celda = celdas.getElemento(pos).getData();
        ListaEnlazed<Individuo> info = new ListaEnlazed<>();

            if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoBasico;")){
                if(!Objects.equals(String.valueOf(numBasico), "0")){
                    for(int i= 0; i< celda.getIndividuoListaEnlazed().getNumeroElementos();i++){
                        if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoBasico;")){
                            info.add(celda.getIndividuoListaEnlazed().getElemento(i).getData());
                        }
                    }
                    return info;
                }else{
                    return null;
                }


            } else if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoNormal;")){
                if(!Objects.equals(String.valueOf(numBasico), "0")){
                    for(int i= 0; i< celda.getIndividuoListaEnlazed().getNumeroElementos();i++){
                        if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoNormal;")){
                            info.add(celda.getIndividuoListaEnlazed().getElemento(i).getData());
                        }
                    }
                    return info;
                }else{
                    return null;
                }

            } else if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoAvanzado;")){
                if(!Objects.equals(String.valueOf(numBasico), "0")){
                    for(int i= 0; i< celda.getIndividuoListaEnlazed().getNumeroElementos();i++){
                        if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoAvanzado;")){
                            info.add(celda.getIndividuoListaEnlazed().getElemento(i).getData());
                        }
                    }
                    return info;
                }else{
                    return null;
                }


            }else {
                return null;
            }
    }



    public void delIndividuo(Class clase){
        int columnas = model.tableroColumnasProperty().getValue().intValue();
        int filas = model.tableroFilasProperty().getValue().intValue();
        int pos = (columnas - x)* filas + (filas-y);
        Celdas celda = celdas.getElemento(pos).getData();
        boolean borrado = false;
        if (!celda.getIndividuoListaEnlazed().isVacia()){
            if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoBasico;")){
                int i = 0;
                while(!borrado){

                    if(i == celda.getIndividuoListaEnlazed().getNumeroElementos()-1){borrado = true;}
                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoBasico;")){
                        avisos.setText("IndividuoBasico borrado");
                        celda.getIndividuoListaEnlazed().del(i);
                        borrado = true;

                    }
                    i++;
                }


            } else if (Objects.equals(clase.descriptorString(), "LIndividuo/IndividuoNormal;")) {
                int i = 0;
                while(!borrado) {
                    if(i == celda.getIndividuoListaEnlazed().getNumeroElementos()-1){borrado = true;}

                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoNormal;")) {
                        celda.getIndividuoListaEnlazed().del(i);
                        avisos.setText("IndividuoNormal borrado");
                        borrado = true;
                    }
                    i++;

                }

            } else if (Objects.equals(clase.descriptorString(),"LIndividuo/IndividuoAvanzado;")) {
                int i = 0;
                while(!borrado){
                    if(i == celda.getIndividuoListaEnlazed().getNumeroElementos()-1){borrado = true;}

                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LIndividuo/IndividuoAvanzado;")){
                        celda.getIndividuoListaEnlazed().del(i);
                        borrado = true;
                    }
                    i++;
            }
                avisos.setText("IndividuoAvanzado borrado");
            }
       }
        else {
                avisos.setText("No hay Individuos que eliminar");
            }
        updateLabels();



        }
        public void delRecursos(Class clase){
            int columnas = model.tableroColumnasProperty().getValue().intValue();
            int filas = model.tableroFilasProperty().getValue().intValue();
            int pos = (columnas - x)* filas + (filas-y);
            Celdas celda = celdas.getElemento(pos).getData();
            boolean borrado = false;
            if (!celda.getRecursosListaEnlazed().isVacia()){
                if (Objects.equals(clase.descriptorString(), "LRecursos/Agua;")){
                    int i = 0;
                    while(!borrado){



                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Agua;")){
                            System.out.println(i);
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}
                        i++;
                    }

                } else if (Objects.equals(clase.descriptorString(), "LRecursos/Biblioteca;")) {
                    int i = 0;
                    while(!borrado){
                        if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Biblioteca;")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }
                        i++;
                    }

                } else if (Objects.equals(clase.descriptorString(), "LRecursos/Pozo;")) {
                    int i = 0;
                    while(!borrado){
                        if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Pozo;")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "LRecursos/Comida;")) {
                    int i = 0;
                    while(!borrado){
                        if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Comida;")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "LRecursos/Tesoro;")) {
                    int i = 0;
                    while(!borrado){
                        if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Tesoro;")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "LRecursos/Montaña;")) {
                    int i = 0;
                    while(!borrado){
                        if(i == celda.getRecursosListaEnlazed().getNumeroElementos()-1){borrado = true;}


                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "LRecursos/Montaña;")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                            avisos.setText("Recurso borrado");
                        }
                        i++;
                    }

                }

            }else{
                avisos.setText("No hay recursos que borrar");
            }
            updateLabels();

        }
     @FXML

    public void addAgua() {
        addRecurso(Agua.class);
    }
    @FXML
    public void delAgua(){
        delRecursos(Agua.class);
    }
    @FXML
    public void addComida() {
        addRecurso(Comida.class);
    }
    @FXML
    public void delComida(){
        delRecursos(Comida.class);
    }
    @FXML
    public void addMontana() {
        addRecurso(Montaña.class);
    }
    @FXML
    public void delMontana(){
        delRecursos(Montaña.class);
    }
    @FXML
    public void addPozo() {
        addRecurso(Pozo.class);
    }
    @FXML
    public void delPozo(){
        delRecursos(Pozo.class);
    }
    @FXML
    public void addTesoro() {
        addRecurso(Tesoro.class);
    }
    @FXML
    public void delTesoro(){
        delRecursos(Tesoro.class);
    }
    @FXML
    public void addBiblioteca() {
        addRecurso(Biblioteca.class);
    }
    @FXML
    public void delBiblioteca(){
        delRecursos(Biblioteca.class);
    }
    @FXML
    public void addBasico(){
        addIndividuo(IndividuoBasico.class);
    }
    @FXML
    public void delBasico(){
        delIndividuo(IndividuoBasico.class);
    }
    @FXML
    public void addNormal(){
        addIndividuo(IndividuoNormal.class);
    }
    @FXML
    public void delNormal(){
        delIndividuo(IndividuoNormal.class);
    }
    @FXML
    public void addAvanzado(){
        addIndividuo(IndividuoAvanzado.class);
    }
    @FXML
    public void delAvanzado(){
        delIndividuo(IndividuoAvanzado.class);
    }
@FXML
    public void guardarCambios() {
        Stage stage1 = (Stage) guardar.getScene().getWindow();
        stage1.close();

    }
    @FXML

    public void infoBasico() {
        if(informacion(IndividuoBasico.class)!= null){
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(TableroController.class.getResource("Info.fxml"));
            stage.setTitle("Info de Individuos Basicos");
            Scene scene = new Scene(fxmlLoader.load(), 441, 399);

            stage.setScene(scene);
            ControllerInfo pestaña = fxmlLoader.getController();
            pestaña.setInfo(informacion(IndividuoBasico.class));
            pestaña.setText();
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }}else {
            avisos.setText("No hay informacion que ver");
        }

    }
    @FXML

    public void infoNormal() {
        if(informacion(IndividuoNormal.class)!= null){
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(TableroController.class.getResource("Info.fxml"));
            stage.setTitle("Info de Individuos Normales");
            Scene scene = new Scene(fxmlLoader.load(), 441, 399);

            stage.setScene(scene);
            ControllerInfo pestaña = fxmlLoader.getController();
            pestaña.setInfo(informacion(IndividuoBasico.class));
            pestaña.setText();
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }}else {
            avisos.setText("No hay informacion que ver");
        }

    }

    @FXML

    public void infoAvanzado(ActionEvent actionEvent) {
        if(informacion(IndividuoAvanzado.class)!=null){
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(TableroController.class.getResource("Info.fxml"));
            stage.setTitle("Info de Individuos Avanzados");
            Scene scene = new Scene(fxmlLoader.load(), 441, 399);

            stage.setScene(scene);
            ControllerInfo pestaña = fxmlLoader.getController();
            pestaña.setInfo(informacion(IndividuoBasico.class));
            pestaña.setText();
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }}else {
            avisos.setText("No hay informacion que ver");
        }

    }

}

