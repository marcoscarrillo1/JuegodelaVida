package Recursos;

import Individuo.Individuo;
import Tablero.Celdas;
import com.google.gson.annotations.Expose;
import org.example.trabajo.ParameterDataModelProperties;

public class Comida extends Recursos{
    @Expose
    public int probabilidadcomida;
    public Comida(Celdas y, int t, int p) {
        super(y, t, p);
    }
    public Comida(ParameterDataModelProperties model){
        super((model));
        probabilidadcomida= model.propComidaProperty().getValue().intValue();
    }
    public Comida(){
        super();
    }
    @Override
    public void Propiedad(Individuo individuo){
        individuo.setTurnosVida(individuo.getTurnosVida()+10);
    }

    public int getProbabilidadcomida() {
        return probabilidadcomida;
    }

    public void setProbabilidadcomida(int probabilidadcomida) {
        this.probabilidadcomida = probabilidadcomida;
    }
}
