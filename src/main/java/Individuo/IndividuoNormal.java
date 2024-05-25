package Individuo;

import Estructuras.Generacion;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class IndividuoNormal extends Individuo{

    public IndividuoNormal(int identificador, Generacion generacion, int turnosVida, int reproducion, int clonacion, Celdas ruta, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, ruta, tipo);
    }
    public IndividuoNormal(ParameterDataModelProperties x,int tipo){
        super(x,tipo);
    }
    public IndividuoNormal(ParameterDataModelProperties x,Generacion geenracion){
        super(x,geenracion);
    }
    public IndividuoNormal(ParameterDataModelProperties x,Generacion generacion,int tipo){
        super(x,generacion,tipo);
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