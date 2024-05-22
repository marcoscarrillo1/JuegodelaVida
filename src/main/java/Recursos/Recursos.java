package Recursos;

import Individuo.Individuo;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public  abstract class  Recursos {
    protected Celdas celda;
    protected int Tiempo;
    protected int Probabilidad;
    protected ParameterDataModelProperties model;

    public Recursos(Celdas x, int t,int p){
       x=celda;
        t=Tiempo;
        p=Probabilidad;

    }
    public Recursos(ParameterDataModelProperties model){
        Tiempo = model.tiempoApariconProperty().getValue().intValue();
        Probabilidad = model.porpApariconProperty().getValue().intValue();
    }
    public Recursos(){

    }

    public Celdas getCelda() {
        return celda;
    }

    public void setCelda(Celdas celda) {
        this.celda = celda;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public int getProbabilidad() {
        return Probabilidad;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    public void setProbabilidad(int probabilidad) {
        Probabilidad = probabilidad;
    }
    public abstract void Propiedad(Individuo x);



}
