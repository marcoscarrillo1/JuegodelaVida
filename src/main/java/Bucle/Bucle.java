package Bucle;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import java.util.Random;

public class Bucle {
    //casillas individuo recrusos y esas vainas

    public ListaEnlazed<Individuo> individuos = new ListaEnlazed<Individuo>();
   /* IndividuoNormal i= new IndiviudoNormal(1,1,1,1,1,1,1);

    public Individuo ElMatadorillia(Celdas celda){
        ListaEnlazed<Individuo> dentrocelda=new ListaEnlazed<Individuo>();
        for(int i=0; i<individuos.getNumeroElementos();i++){
            if (individuos.getElemento(i).getData().getCelda().getx()==celda.getx()){
                dentrocelda=
            }
        }

    }*/
    public void Clonacion(){
        for(int i=0;i<individuos.getNumeroElementos();i++){
            Random random=new Random();


        }

    }
    public Individuo Reproduccion() {
        return individuos.getPrimero().getData();

    }

    public void Vida(){

    }


}
