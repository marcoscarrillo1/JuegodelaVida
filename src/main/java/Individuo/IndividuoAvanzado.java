package Individuo;

import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class IndividuoAvanzado extends Individuo{

    public IndividuoAvanzado(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoAvanzado(ParameterDataModelProperties x){
        super(x);
    }
    public IndividuoAvanzado(){
        super();
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {

    }
    @Override
    public Individuo cambiarTipo(int x) {
        return this;
    }
}