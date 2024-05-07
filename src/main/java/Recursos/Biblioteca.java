package Recursos;
import Individuo.Individuo;

import java.util.Random;
public class Biblioteca extends Recursos{
    protected int probabilidadbiblio;
    public Biblioteca(int x, int y, int t, int p,int pB) {
        super(x, y, t, p);
        probabilidadbiblio = pB;
    }

    public int getProbabilidadbiblio() {
        return probabilidadbiblio;
    }

    public void setProbabilidadbiblio(int probabilidadbiblio) {
        this.probabilidadbiblio = probabilidadbiblio;
    }

    public void propiedadbiblioteca(Individuo individuo){
         Random random=new Random();
         int x= random.nextInt(101);
        individuo.setClonacion(individuo.getClonacion()*(1+x/100));
        individuo.cambiarTipo(individuo.getTipo()+1);

    }
}
