package Recursos;
import Individuo.Individuo;
import Tablero.Celdas;
import com.google.gson.annotations.Expose;
import org.example.trabajo.ParameterDataModelProperties;

import java.util.Random;
public class Biblioteca extends Recursos{
    @Expose
    protected int probabilidadbiblio;
    public Biblioteca(Celdas y, int t, int p, int pB) {
        super(y, t, p);
        probabilidadbiblio = pB;
    }
    public Biblioteca(ParameterDataModelProperties model){
        super((model));
        probabilidadbiblio = model.propBibliotecaProperty().getValue().intValue();
    }
    public Biblioteca(){
        super();
    }

    public int getProbabilidadbiblio() {
        return probabilidadbiblio;
    }

    public void setProbabilidadbiblio(int probabilidadbiblio) {
        this.probabilidadbiblio = probabilidadbiblio;
    }
@Override
    public void  Propiedad(Individuo individuo){
        Random random=new Random();
        int x= random.nextInt(101);
        individuo.setClonacion(individuo.getClonacion()*(1+x/100));
    }

}
