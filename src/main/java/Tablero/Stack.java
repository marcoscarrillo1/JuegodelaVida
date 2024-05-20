package Tablero;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Stack  {
    private Integer x,y;
    private boolean hayalguien;

    private ListaEnlazed<Individuo> individuos;
    private ListaEnlazed<Recursos> recursos;

    public Stack(int x, int y) {
        this.individuos = new ListaEnlazed<Individuo>();
        this.recursos = new ListaEnlazed<Recursos>();
        this.x = x;
        this.y = y;
        this.hayalguien = false;
    }

    public Stack(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public ListaEnlazed<Individuo> getIndividuos() {
        return individuos;
    }

    public ListaEnlazed<Recursos> getRecursos() {
        return recursos;
    }


    public void setIndividuos(ListaEnlazed<Individuo> individuos) {
        this.individuos = individuos;
    }

    public void setRecursos(ListaEnlazed<Recursos> recursos) {
        this.recursos = recursos;
    }

}
