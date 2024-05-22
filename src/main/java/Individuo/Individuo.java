package Individuo;

import Tablero.Celdas;
import org.example.trabajo.ParameterDataModel;
import org.example.trabajo.ParameterDataModelProperties;

public abstract class Individuo  {
    protected int identificador;
    protected int generacion;
    protected int turnosVida;
    protected int reproducion;
    protected int clonacion;
    protected int muerte;
    protected Celdas ruta;

    public Celdas getRuta() {
        return ruta;
    }

    public void setRuta(Celdas ruta) {
        this.ruta = ruta;
    }

    protected int tipo;
    private ParameterDataModelProperties datos;

    public Individuo(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        this.identificador = identificador;
        this.generacion = generacion;
        this.turnosVida = turnosVida;
        this.reproducion = reproducion;
        this.clonacion = clonacion;
        this.ruta=ruta;
        this.tipo = tipo;
    }
    public Individuo(ParameterDataModelProperties x){
        turnosVida = x.turnosVidaProperty().getValue().intValue();
        reproducion = x.reproduccionProperty().getValue().intValue();
        muerte = 1 - reproducion;
        clonacion = x.mutacionProperty().getValue().intValue();
    }
    public Individuo(){

    }

    public ParameterDataModelProperties getDatos() {
        return datos;
    }

    public void setDatos(ParameterDataModelProperties datos) {
        this.datos = datos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public int getTurnosVida() {
        return turnosVida;
    }

    public void setTurnosVida(int turnosVida) {
        this.turnosVida = turnosVida;
    }

    public int getReproducion() {
        return reproducion;
    }

    public void setReproducion(int reproducion) {
        this.reproducion = reproducion;
    }

    public int getClonacion() {
        return clonacion;
    }

    public void setClonacion(int clonacion) {
        this.clonacion = clonacion;
    }

    public int getMuerte() {
        return muerte;
    }

    public void setMuerte(int muerte) {
        this.muerte = muerte;
    }

    public void reproducirse(Individuo x){

    }
    public abstract void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) ;
    public abstract Individuo cambiarTipo(int x);
    public void mutar(){
    }
    public void Recorrido(){

    }
    public void mover(){

    }
}














    /*super Individuo(int vida, int clonacion, int hijo, int reproduccion){

    }
    public void setTipo(int t){
        this.tipo=t;
    }
    public int getTipo(){
        return tipo;
    }
    public boolean reproduccion(){
        Random random =new Random();
        int probabilidad= random.nextInt(101);
        if(probabilidad<=getporcentajereproduccion){
            return true;
        }else {
            return false;
        }
    }
    public boolean clonacion(){
        Random random =new Random();
        int probabilidad= random.nextInt(101);
        if(probabilidad<=getporcentajeclonacion()){
            return true;
        }else {
            return false;
        }
    }*/








