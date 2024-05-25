package Individuo;

import Estructuras.Cola;
import Estructuras.Generacion;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class IndividuoAvanzado extends Individuo{

    public IndividuoAvanzado(int identificador, Generacion generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoAvanzado(ParameterDataModelProperties x){
        super(x);
    }
    public IndividuoAvanzado(ParameterDataModelProperties x,Generacion generacion){
        super(x,generacion);
    }
    public IndividuoAvanzado(ParameterDataModelProperties x,Generacion generacion,int tipo){
        super(x,generacion,tipo);
    }
    public IndividuoAvanzado(ParameterDataModelProperties x, int tipo){
        super(x,tipo);
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