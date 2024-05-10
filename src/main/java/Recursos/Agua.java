package Recursos;

import Individuo.Individuo;

public class Agua extends Recursos {

    protected   int probabilidadagua;

    public Agua(int x, int y, int t,int p, int Pagua) {
        super(x, y,t,p);
        this.probabilidadagua=Pagua;

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
