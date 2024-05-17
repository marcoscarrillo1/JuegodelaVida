package Tablero;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Recursos.Recursos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Stack extends StackPane {
    private Integer x,y;
    private boolean hayalguien;
    private int id;

    private ListaEnlazed<Celdas> celdas=new ListaEnlazed<Celdas>();
    private ListaEnlazed<Individuo> individuos;
    private ListaEnlazed<Recursos> recursos;
    GridPane grid;
    public Stack(int x, int y){
        this.individuos=new ListaEnlazed<Individuo>();
        this.recursos=new ListaEnlazed<Recursos>();
        this.x=x;
        this.y=y;
        this.hayalguien=false;
        this.grid=new GridPane();
        Integer id=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                Celdas celda=new Celdas(i,j);
                celda.setPrefHeight(100);
                celda.setPrefWidth(100);
                celda.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                celda.setColor(Color.RED);
                celda.setId(id);
                id++;
                grid.add(celda,i,j);
                celdas.add(celda);


            }
        }
        getChildren().add(grid);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIndividuos(ListaEnlazed<Individuo> individuos) {
        this.individuos = individuos;
    }

    public void setRecursos(ListaEnlazed<Recursos> recursos) {
        this.recursos = recursos;
    }
    public Celdas getCelda(int id){
        for (Node nodo: grid.getChildren()){
            if(nodo instanceof Celdas){
            Celdas celda=(Celdas) nodo;
            if(Integer.parseInt(celda.getId())==id){
                return celda;
            }
            }
        }return null;
    }
}
