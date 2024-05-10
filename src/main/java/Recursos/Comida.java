package Recursos;

import Individuo.Individuo;

public class Comida extends Recursos{
    public int probabilidadcomida;
    public Comida(int x, int y, int t, int p) {
        super(x, y, t, p);
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
