package Individuo;

import Estructuras.Generacion;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class IndividuoNormal extends Individuo{

    public IndividuoNormal(int identificador, Generacion generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoNormal(ParameterDataModelProperties x,Generacion generacion){
        super(x,generacion);
    }
    public IndividuoNormal(ParameterDataModelProperties x){
        super(x);
    }
    public IndividuoNormal(){
        super();
    }

    @Override
    public Individuo cambiarTipo(int x) {
        return this;
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {

    }
}