package Recursos;

import Individuo.Individuo;
import Tablero.Celdas;
import org.example.trabajo.ParameterDataModelProperties;

public class Pozo extends Recursos{
    int probabilidadpozo;
    public Pozo(Celdas x, int t, int p) {
        super(x, t, p);
    }
    public Pozo(ParameterDataModelProperties model){
        super((model));
       probabilidadpozo = model.propPozoProperty().getValue().intValue();
    }
    public Pozo(){
        super();
    }
    @Override
    public void Propiedad(Individuo individuo){
        individuo.setTurnosVida(-1);
    }

    public int getProbabilidadpozo() {
        return probabilidadpozo;
    }

    public void setProbabilidadpozo(int probabilidadpozo) {
        this.probabilidadpozo = probabilidadpozo;
    }
}
