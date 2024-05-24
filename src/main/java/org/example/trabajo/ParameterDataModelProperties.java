package org.example.trabajo;

import javafx.beans.property.*;

public class ParameterDataModelProperties {
    //Modelo de datos original
    protected ParameterDataModel original;

    private IntegerProperty turnosVida = new SimpleIntegerProperty();
    private IntegerProperty mutacion = new SimpleIntegerProperty();
    private IntegerProperty reproduccion = new SimpleIntegerProperty();

    private IntegerProperty tableroFilas = new SimpleIntegerProperty();
    private IntegerProperty tableroColumnas = new SimpleIntegerProperty();
    private IntegerProperty tiempoAparicion = new SimpleIntegerProperty();

    private IntegerProperty probAparicion= new SimpleIntegerProperty();

    private IntegerProperty propAgua = new SimpleIntegerProperty();

    private IntegerProperty propBiblioteca = new SimpleIntegerProperty();

    private IntegerProperty propMontana = new SimpleIntegerProperty();

    private IntegerProperty propComida= new SimpleIntegerProperty();

    private IntegerProperty propPozo = new SimpleIntegerProperty();

    private IntegerProperty propTesoro = new SimpleIntegerProperty();





    public ParameterDataModelProperties(ParameterDataModel original){
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosVida(turnosVida.get());
        original.setMutacion(mutacion.get());
        original.setReproducion(reproduccion.get());
        original.setTableroColumnas(tableroColumnas.get());
        original.setTableroFilas(tableroFilas.get());
        original.setProbAparicion(probAparicion.get());
        original.setTiempoApracion(tiempoAparicion.get());
        original.setPropAgua(propAgua.get());
        original.setPropBiblioteca(propBiblioteca.get());
        original.setPropMontana(propMontana.get());
        original.setPropComida(propComida.get());
        original.setPropPozo(propPozo.get());
        original.setPropTesoro(propTesoro.get());

    }

    public void rollback(){

        turnosVida.set(original.getTurnosVida());
        mutacion.set(original.getMutacion());
        reproduccion.set(original.getReproducion());
        tableroColumnas.set(original.getTableroColumnas());
        tableroFilas.set(original.getTableroFilas());
        probAparicion.set(original.getProbAparicion());
        tiempoAparicion.set(original.getTiempoApracion());
        propAgua.set(original.getPropAgua());
        propBiblioteca.set(original.getPropBiblioteca());
        propMontana.set(original.getPropMontana());
        propPozo.set(original.getPropPozo());
        propComida.set(original.getPropComida());
        propTesoro.set(original.getPropTesoro());
    }

    public ParameterDataModel getOriginal(){
        return original;
    }

    public void setOriginal(ParameterDataModel original){
        this.original = original;
        rollback();

    }


    public Property<Number> turnosVidaProperty() {
        return turnosVida;
    }
    public Property<Number> reproduccionProperty() {
        return reproduccion;
    }

    public Property<Number> mutacionProperty() {
        return mutacion;
    }

    public Property<Number> tableroFilasProperty() {
        return tableroFilas;
    }
    public Property<Number> tableroColumnasProperty() {
        return tableroColumnas;
    }
    public Property<Number> tiempoApariconProperty() {
        return tiempoAparicion;
    }
    public Property<Number> porpApariconProperty() {
        return probAparicion;
    }
    public Property<Number> propAguaProperty() {
        return propAgua;
    }
    public Property<Number> propBibliotecaProperty() {
        return propBiblioteca;
    }
    public Property<Number> propMontanaProperty() {
        return propMontana;
    }
    public Property<Number> propComidaProperty() {
        return propComida;
    }
    public Property<Number> propPozoProperty() {
        return propPozo;
    }
    public Property<Number> propTesoroProperty() {
        return propTesoro;
    }
}