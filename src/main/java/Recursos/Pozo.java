package Recursos;

import Individuo.Individuo;

public class Pozo extends Recursos{
    int probabilidadpozo;
    public Pozo(int x, int y, int t, int p) {
        super(x, y, t, p);
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
