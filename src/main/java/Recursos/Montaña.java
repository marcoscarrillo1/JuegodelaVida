package Recursos;

import Individuo.Individuo;

public class Montaña  extends Recursos{
    public int probabilidadmontaña;
    public Montaña(int x, int y, int t, int p) {
        super(x, y, t, p);
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
