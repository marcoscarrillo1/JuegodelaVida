package Tablero;

import Bucle.ElementoLe;
import Bucle.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import javafx.scene.layout.StackPane;

public class Celdas extends StackPane {
    private ListaEnlazed<Recursos> recursosListaEnlazed = new ListaEnlazed<Recursos>();
    private ListaEnlazed<Individuo> individuoListaEnlazed = new ListaEnlazed<Individuo>();

    public ListaEnlazed<Recursos> getRecursosListaEnlazed() {
        return recursosListaEnlazed;
    }

    public ListaEnlazed<Individuo> getIndividuoListaEnlazed() {
        return individuoListaEnlazed;
    }

    public void setRecursosListaEnlazed(ListaEnlazed<Recursos> recursosListaEnlazed) {
        this.recursosListaEnlazed = recursosListaEnlazed;
    }

    public void setIndividuoListaEnlazed(ListaEnlazed<Individuo> individuoListaEnlazed) {
        this.individuoListaEnlazed = individuoListaEnlazed;
    }























    public void addIndividuo(Individuo individuo){
        if(individuoListaEnlazed.getNumeroElementos()< 3){
            individuoListaEnlazed.add(individuo);
        }else{
            individuoListaEnlazed.add(individuo);
            boolean cambiado = true;
            while (cambiado){
                cambiado = false;
            for (int i = 0 ; i < individuoListaEnlazed.getNumeroElementos()- 1; i++){
                if(individuoListaEnlazed.getElemento(i).getData().getTurnosVida() >
                        individuoListaEnlazed.getElemento(i+1).getData().getTurnosVida()) {
                    ElementoLe<Individuo> temp = individuoListaEnlazed.getElemento(i);
                    individuoListaEnlazed.insert(individuoListaEnlazed.getElemento(i+1),i);
                    individuoListaEnlazed.insert(temp,i+1);
                    cambiado = true;
                }
                }}
            while (individuoListaEnlazed.getNumeroElementos()> 3){
                individuoListaEnlazed.del(0);

            }
        }
    }
    public void addRecurso(Recursos recurso){
        if (recursosListaEnlazed.getNumeroElementos()< 3){
            recursosListaEnlazed.add(recurso);
        }
    }
    public void EliminarIndividuo(Individuo individuo){
        int pos=individuoListaEnlazed.getPosicion(new ElementoLe<Individuo>(individuo));
        individuoListaEnlazed.del(pos);
    }
    public void EliminarRecurso(Recursos recurso){
        int pos=recursosListaEnlazed.getPosicion(new ElementoLe<Recursos>(recurso));
        recursosListaEnlazed.del(pos);
    }



}
