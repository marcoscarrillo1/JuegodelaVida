package Individuo;

import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class IndividuoNormal extends Individuo{

    public IndividuoNormal(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoNormal(ParameterDataModelProperties x){
        super(x);
    }
    public IndividuoNormal(){
        super();
    }

    @Override
    public Individuo cambiarTipo(int x) {
        return new IndividuoAvanzado(identificador,generacion,turnosVida,reproducion,clonacion,ruta,x);
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {

    }
}