package Individuo;

import Tablero.Celdas;

public class IndividuoNormal extends Individuo{

    public IndividuoNormal(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }
    public IndividuoNormal(){
        super();
    }

    @Override
    public Individuo cambiarTipo(int x) {
        return new IndividuoAvanzado(identificador,generacion,turnosVida,reproducion,clonacion,muerte,x);
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {

    }
}