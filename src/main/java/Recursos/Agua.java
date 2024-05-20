package Recursos;

import Individuo.Individuo;
import Tablero.Celdas;

public class Agua extends Recursos {

    protected   int probabilidadagua;

    public Agua(Celdas x, int t, int p, int Pagua) {
        super(x,t,p);
        this.probabilidadagua=Pagua;

    }
    public Agua(){
        super();
    }


    public int getProbabilidadagua() {
        return probabilidadagua;
    }

    public void setProbabilidadagua(int probabilidadagua) {
        this.probabilidadagua = probabilidadagua;
    }
    public void Propiedad(Individuo individuo){
        individuo.setTurnosVida(individuo.getTurnosVida()+2);
    }
}
