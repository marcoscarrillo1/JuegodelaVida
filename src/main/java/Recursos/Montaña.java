package Recursos;

import Individuo.Individuo;
import Tablero.Celdas;
import com.google.gson.annotations.Expose;
import org.example.trabajo.ParameterDataModelProperties;

public class Montaña  extends Recursos{
    @Expose
    public int probabilidadmontaña;
    public Montaña(Celdas x, int t, int p) {
        super(x, t, p);
    }
    public Montaña(ParameterDataModelProperties model){
        super(model);
        probabilidadmontaña = model.propMontanaProperty().getValue().intValue();
    }
    public Montaña(){
        super();
    }
    @Override
    public void Propiedad(Individuo individuo){
        individuo.setTurnosVida(individuo.getTurnosVida()-2);
    }

    public void setProbabilidadmontaña(int probabilidadmontaña) {
        this.probabilidadmontaña = probabilidadmontaña;
    }

    public int getProbabilidadmontaña() {
        return probabilidadmontaña;
    }
}
