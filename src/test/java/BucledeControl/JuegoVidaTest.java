package BucledeControl;

import Individuo.Individuo;
import Recursos.Agua;
import Recursos.Recursos;
import Tablero.Celdas;
import org.junit.jupiter.api.Test;
import Individuo.IndividuoAvanzado;
import Individuo.IndividuoNormal;
import Individuo.IndividuoBasico;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JuegoVidaTest {

    @Test
    void movimiento() {
        Celdas celda=null;
        IndividuoBasico ind1=null;
        IndividuoNormal ind2=null;
        IndividuoAvanzado ind3=null;
        movimiento();
    }

    @Test
    void crearrecursos() {
        Agua agua=new Agua();
        Random random=new Random();
        int x=random.nextInt(101);
        Celdas celda=new Celdas();
        if(agua.getProbabilidad()>x){
            celda.addRecurso(agua);
        }
    }

    @Test
    void reproduccion() {
        Celdas celda=new Celdas();
        Individuo individuo=null;
        Individuo indi=null;
        Random random=new Random();
        int x=random.nextInt(101);
        if(individuo.getReproducion()>x&&indi.getReproducion()>x){
            Individuo hijo=null;
            celda.addIndividuo(null);
        }
    }

    @Test
    void mejora() {
        Agua agua=new Agua();
        Individuo individuo=null;
        Celdas celda=new Celdas();
        celda.addIndividuo(individuo);
        celda.addRecurso(agua);
        if(celda.getIndividuoListaEnlazed().getElemento(0).getData()
                ==individuo&&celda.getRecursosListaEnlazed().getElemento(0).getData()==agua){
            agua.Propiedad(individuo);
        }
    }

    @Test
    void longevidad() {
    }

    @Test
    void individuoactualizado() {
        Celdas celda=new Celdas();
        Individuo individuo=null;
        celda.addIndividuo(individuo);
        if(individuo.getTurnosVida()==0){
            celda.getIndividuoListaEnlazed().del(0);
        }
    }

    @Test
    void recursoactivo() {
        Celdas celda=new Celdas();
        Recursos recursos =null;
        celda.addRecurso(recursos);
        if(recursos.getTiempo()==0){
            celda.getIndividuoListaEnlazed().del(0);

    }
    }

    @Test
    void clonacion() {
        Celdas celdas=new Celdas();
        Individuo individuo=null;
        celdas.addIndividuo(individuo);
        Random random=new Random();
        int x=random.nextInt(101);
        if(individuo.getClonacion()>x){
            celdas.addIndividuo(individuo);
        }
    }
}