package Tablero;

import Estructuras.ElementoLe;
import Estructuras.ListaEnlazed;
import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import com.google.gson.annotations.Expose;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class Celdas  {
    private Button boton;
    @Expose
    private int x,y;
    @Expose
    private ListaEnlazed<Individuo> individuoListaEnlazed;
    @Expose
    private ListaEnlazed<Recursos> recursosListaEnlazed;


    public ListaEnlazed<Recursos> getRecursosListaEnlazed() {
        return recursosListaEnlazed;
    }

    public ListaEnlazed<Individuo> getIndividuoListaEnlazed() {
        return individuoListaEnlazed;
    }

    private boolean hayalguien;

    public Button getBoton() {
        return boton;
    }

    public Celdas(int x, int y, ListaEnlazed<Recursos> re, ListaEnlazed<Individuo> in){
        this.x=x;
        this.y=y;
        individuoListaEnlazed= in;
        recursosListaEnlazed=re;
        this.hayalguien=false;
    }
    public Celdas(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Celdas(){

    }
    public void setBoton(Button boton){
        this.boton=boton;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHayalguien() {
        if (recursosListaEnlazed.isVacia() && individuoListaEnlazed.isVacia()){
            setHayalguien(false);
            return hayalguien;
        }else {
            return true;
        }

    }




    public void setHayalguien(boolean hayalguien) {
        this.hayalguien = hayalguien;
    }
    public void setColor(Color color){
        boton.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY
        , Insets.EMPTY)));
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
