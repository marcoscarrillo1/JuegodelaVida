package Individuo;

import Estructuras.Generacion;
import Tablero.Celdas;
import com.google.gson.annotations.Expose;
import org.example.trabajo.ParameterDataModel;
import org.example.trabajo.ParameterDataModelProperties;

public abstract class Individuo  {
    @Expose
    protected int identificador;
    @Expose
    protected Generacion Generacion;
    @Expose
    protected int turnosVida;
    @Expose
    protected int reproducion;
    @Expose
    protected int clonacion;
    @Expose
    protected int muerte;
    protected boolean movido;
    @Expose
    protected Celdas ruta;

    public Celdas getRuta() {
        return ruta;
    }

    public void setRuta(Celdas ruta) {
        this.ruta = ruta;
    }

    protected int tipo;
    private ParameterDataModelProperties datos;

    public Generacion getGeneracion() {
        return Generacion;
    }

    public void setGeneracion(Generacion generacion) {
        Generacion = generacion;
    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public boolean isMovido() {
        return movido;
    }

    public Individuo(int identificador,Generacion generacion , int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        this.identificador = identificador;
        this.Generacion = generacion;
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
    public Individuo(ParameterDataModelProperties x,Generacion generacion){
        turnosVida = x.turnosVidaProperty().getValue().intValue();
        reproducion = x.reproduccionProperty().getValue().intValue();
        muerte = 1 - reproducion;
        clonacion = x.mutacionProperty().getValue().intValue();
        this.Generacion=generacion;
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








