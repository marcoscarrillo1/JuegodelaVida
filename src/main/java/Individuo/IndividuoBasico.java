package Individuo;

import Estructuras.Cola;
import Estructuras.Generacion;
import Estructuras.ListaEnlazed;
import Estructuras.ListaSimple;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

import java.util.Random;

public class IndividuoBasico extends Individuo {


    public IndividuoBasico(int identificador, Generacion generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoBasico(ParameterDataModelProperties x){
        super(x);
    }
    public IndividuoBasico(ParameterDataModelProperties x,Generacion generacion){
        super(x,generacion);
    }
    public IndividuoBasico(ParameterDataModelProperties x,Generacion generacion,int tipo){
        super(x,generacion,tipo);
    }
    public IndividuoBasico(ParameterDataModelProperties x, int tipo){
        super(x,tipo);
    }
    public IndividuoBasico(){
        super();
    }

    public IndividuoBasico(ParameterDataModelProperties model, Estructuras.Generacion generacion, int i, Cola cola) {
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {
        Random random= new Random();
        int dir= random.nextInt(8);
        if(dir==1){

        }
    }

    @Override
    public void mover(){

    }
    @Override
    public Individuo cambiarTipo(int x) {
        return this;
    }
}