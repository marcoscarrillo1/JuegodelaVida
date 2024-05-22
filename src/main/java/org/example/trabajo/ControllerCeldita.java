package org.example.trabajo;

import Estructuras.ElementoLe;
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

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerCeldita {
    @FXML
    public Label avisos;
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
    }


    public void updateLabels(){
        int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);

        Celdas celda = celdas.getElemento(pos).getData();
        System.out.println(pos+celda.getX()+celda.getY());
        int basicos = 0;
        int normal = 0;
        int avanzado = 0;
        for(int i = 0; i < celda.getIndividuoListaEnlazed().getNumeroElementos();i++){
            if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoBasico")){
                basicos++;
                numBasico.setText(String.valueOf(basicos));
            } else if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoNormal")){
                normal++;
                numNormal.setText(String.valueOf(normal));

            } else if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoAvanzado")){
                avanzado++;
                numAvanzado.setText(String.valueOf(avanzado));

            }
        }
        int agua = 0;
        int montaña = 0;
        int biblioteca = 0;
        int tesoro= 0;
        int pozo = 0;
        int comida= 0;
        for(int i = 0; i < celda.getRecursosListaEnlazed().getNumeroElementos();i++){
            if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Agua")){
                agua++;
                numAgua.setText(String.valueOf(agua));
            } else if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Montaña")){
                montaña++;
                numMontana.setText(String.valueOf(montaña));

            } else if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Pozo")){
                pozo++;
                numPozo.setText(String.valueOf(pozo));

            }else if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Comida")){
                comida++;
                numComida.setText(String.valueOf(comida));

            }else if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Tesoro")) {
                tesoro++;
                numTesoro.setText(String.valueOf(tesoro));
            }else if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Biblioteca")){
                biblioteca++;
                numBiblioteca.setText(String.valueOf(biblioteca));




            }}}

    public void addIndividuo(Class clase){
        int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);
        Celdas celda = celdas.getElemento(pos).getData();
        if (celda.getIndividuoListaEnlazed().getNumeroElementos() < 3){
            if (Objects.equals(clase.descriptorString(), "IndividuoBasico")){
                IndividuoBasico nuevoB = new IndividuoBasico(model);
                celda.addIndividuo(nuevoB);

            } else if (Objects.equals(clase.descriptorString(), "IndividuoNormal")) {
                IndividuoNormal nuevoN = new IndividuoNormal(model);
                celda.addIndividuo(nuevoN);

            } else if (Objects.equals(clase.descriptorString(),"IndividuoAvanzado")) {
                IndividuoAvanzado nuevoA = new IndividuoAvanzado(model);
                celda.addIndividuo(nuevoA);

            }
            avisos.setText("Individuo añadido");

        }else {
            avisos.setText("Nose puede añadir individuo casilla llena");
        }
        updateLabels();


    }
    public void addRecurso(Class clase){
        int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);
        Celdas celda = celdas.getElemento(pos).getData();
        if (celda.getRecursosListaEnlazed().getNumeroElementos() < 3){
            if (Objects.equals(clase.descriptorString(), "Agua")){
                Agua nuevoA = new Agua(model);
                celda.addRecurso(nuevoA);


            } else if (Objects.equals(clase.descriptorString(), "Biblioteca")) {
                Biblioteca nuevoB = new Biblioteca(model);
                celda.addRecurso(nuevoB);

            } else if (Objects.equals(clase.descriptorString(),"Comida")) {
                Comida nuevoC= new Comida(model);
                celda.addRecurso(nuevoC);

            }else if (Objects.equals(clase.descriptorString(),"Montaña")) {
                Montaña nuevoM= new Montaña(model);
                celda.addRecurso(nuevoM);

            }else if (Objects.equals(clase.descriptorString(),"Pozo")) {
                Pozo nuevoP= new Pozo(model);
                celda.addRecurso(nuevoP);

            }else if (Objects.equals(clase.descriptorString(),"Tesoro")) {
                Tesoro nuevoT= new Tesoro(model);
                celda.addRecurso(nuevoT);

            }avisos.setText("Recurso añadido");
        }else {
                avisos.setText("No se puede añadir recurso casilla completa");
            }
        updateLabels();


    }
    public void delIndividuo(Class clase){
        int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);
        Celdas celda = celdas.getElemento(pos).getData();
        boolean borrado = false;
        if (celda.getIndividuoListaEnlazed().isVacia()== false){
            if (Objects.equals(clase.descriptorString(), "IndividuoBasico")){
                int i = 0;
                while(!borrado){

                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoBasico")){
                        celda.getIndividuoListaEnlazed().del(i);
                        borrado = true;
                    }
                    i++;
                }

            } else if (Objects.equals(clase.descriptorString(), "IndividuoNormal")) {
                int i = 0;
                while(!borrado) {

                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoNormal")) {
                        celda.getIndividuoListaEnlazed().del(i);
                        borrado = true;
                    }
                    i++;
                }

            } else if (Objects.equals(clase.descriptorString(),"IndividuoAvanzado")) {
                int i = 0;
                while(!borrado){

                    if (Objects.equals(celda.getIndividuoListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "IndividuoAvanzado")){
                        celda.getIndividuoListaEnlazed().del(i);
                        borrado = true;
                    }
                    i++;
            }
            }
        avisos.setText("Individuo borrado");}
        else {
                avisos.setText("No hay Individuos que eliminar");
            }
        updateLabels();


        }
        public void delRecursos(Class clase){
            int pos = (x)*model.tableroFilasProperty().getValue().intValue()+ (y);
            Celdas celda = celdas.getElemento(pos).getData();
            boolean borrado = false;
            if (!celda.getRecursosListaEnlazed().isVacia()){
                if (Objects.equals(clase.descriptorString(), "Agua")){
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Agua")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                } else if (Objects.equals(clase.descriptorString(), "Biblioteca")) {
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Biblioteca")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                } else if (Objects.equals(clase.descriptorString(), "Pozo")) {
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Pozo")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "Comida")) {
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Comida")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "Tesoro")) {
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Teosoro")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                }else if (Objects.equals(clase.descriptorString(), "Montaña")) {
                    int i = 0;
                    while(!borrado){

                        if (Objects.equals(celda.getRecursosListaEnlazed().getElemento(i).getData().getClass().descriptorString(), "Montaña")){
                            celda.getRecursosListaEnlazed().del(i);
                            borrado = true;
                        }
                        i++;
                    }

                }
                avisos.setText("Recurso borrado");
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

}

