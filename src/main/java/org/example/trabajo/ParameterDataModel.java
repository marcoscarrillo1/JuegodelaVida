package org.example.trabajo;



public class ParameterDataModel {
    private int turnosVida;
    private int mutacion;
    private int reproducion;

    private int tableroFilas;
    private int tableroColumnas;
    private int tiempoApracion;

    private int probAparicion;

    private int propAgua;

    private int propBiblioteca;

    private int propMontana;

    private int propComida;

    private int propPozo;

    private int propTesoro;

    public ParameterDataModel(int turnosVida, int mutacion, int reproducion, int tableroFilas, int tableroColumnas, int tiempoApracion, int probAparicion, int propAgua, int propBiblioteca, int propMontana, int propComida, int propPozo, int propTesoro) {
        this.turnosVida = turnosVida;
        this.mutacion = mutacion;
        this.reproducion = reproducion;
        this.tableroFilas = tableroFilas;
        this.tableroColumnas = tableroColumnas;
        this.tiempoApracion = tiempoApracion;
        this.probAparicion = probAparicion;
        this.propAgua = propAgua;
        this.propBiblioteca = propBiblioteca;
        this.propMontana = propMontana;
        this.propComida = propComida;
        this.propPozo = propPozo;
        this.propTesoro = propTesoro;
    }

    public int getTurnosVida() {
        return turnosVida;
    }

    public void setTurnosVida(int turnosVida) {
        this.turnosVida = turnosVida;
    }

    public int getMutacion() {
        return mutacion;
    }

    public void setMutacion(int mutacion) {
        this.mutacion = mutacion;
    }

    public int getReproducion() {
        return reproducion;
    }

    public void setReproducion(int reproducion) {
        this.reproducion = reproducion;
    }

    public int getTableroFilas() {
        return tableroFilas;
    }

    public void setTableroFilas(int tableroFilas) {
        this.tableroFilas = tableroFilas;
    }

    public int getTableroColumnas() {
        return tableroColumnas;
    }

    public void setTableroColumnas(int tableroColumnas) {
        this.tableroColumnas = tableroColumnas;
    }

    public int getTiempoApracion() {
        return tiempoApracion;
    }

    public void setTiempoApracion(int tiempoApracion) {
        this.tiempoApracion = tiempoApracion;
    }

    public int getProbAparicion() {
        return probAparicion;
    }

    public void setProbAparicion(int probAparicion) {
        this.probAparicion = probAparicion;
    }

    public int getPropAgua() {
        return propAgua;
    }

    public void setPropAgua(int propAgua) {
        this.propAgua = propAgua;
    }

    public int getPropBiblioteca() {
        return propBiblioteca;
    }

    public void setPropBiblioteca(int propBiblioteca) {
        this.propBiblioteca = propBiblioteca;
    }

    public int getPropMontana() {
        return propMontana;
    }

    public void setPropMontana(int propMontana) {
        this.propMontana = propMontana;
    }

    public int getPropComida() {
        return propComida;
    }

    public void setPropComida(int propComida) {
        this.propComida = propComida;
    }

    public int getPropPozo() {
        return propPozo;
    }

    public void setPropPozo(int propPozo) {
        this.propPozo = propPozo;
    }

    public int getPropTesoro() {
        return propTesoro;
    }

    public void setPropTesoro(int propTesoro) {
        this.propTesoro = propTesoro;
    }
}