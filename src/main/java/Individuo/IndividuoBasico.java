package Individuo;

import Estructuras.ListaEnlazed;
import Estructuras.ListaSimple;
import Tablero.Celdas;

import java.util.Random;

public class IndividuoBasico extends Individuo {


    public IndividuoBasico(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte,int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }
    public IndividuoBasico(){
        super();
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {
        Random random= new Random();
    }

    @Override
    public void mover(){

    }
    @Override
    public Individuo cambiarTipo(int x) {
        return new IndividuoNormal(identificador,generacion,turnosVida,reproducion,clonacion,muerte,x);
    }
}