package Individuo;

import java.util.Random;

public class Individuo  {
    protected int identificador;
    protected int generacion;
    protected int turnosVida;
    protected int reproducion;
    protected int clonacion;
    protected int muerte;
    protected int tipo;

    public Individuo(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte, int tipo) {
        this.identificador = identificador;
        this.generacion = generacion;
        this.turnosVida = turnosVida;
        this.reproducion = reproducion;
        this.clonacion = clonacion;
        this.muerte = muerte;
        this.tipo = tipo;
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
    public void mutar(){
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




