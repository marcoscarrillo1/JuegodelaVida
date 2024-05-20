package Recursos;
import Individuo.Individuo;
import Tablero.Celdas;

import java.util.Random;

public class Tesoro extends Recursos{
    int probabilidadtesoro;
    public Tesoro(Celdas x , int t, int p) {
        super(x, t, p);
    }
    public Tesoro(){
        super();
    }
    @Override
    public void Propiedad(Individuo individuo){
        Random random= new Random();
        int x= random.nextInt(101);
        individuo.setReproducion(individuo.getReproducion()*(1+x/100));
    }

    public int getProbabilidadtesoro() {
        return probabilidadtesoro;
    }

    public void setProbabilidadtesoro(int probabilidadtesoro) {
        this.probabilidadtesoro = probabilidadtesoro;
    }
}
