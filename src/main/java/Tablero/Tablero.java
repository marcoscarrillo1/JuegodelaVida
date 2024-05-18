package Tablero;

import Estructuras.ListaEnlazed;
import Estructuras.ListaEnlazed;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import org.example.trabajo.ParameterDataModel;

public class Tablero  {
    private ParameterDataModel model;
    GridPane tableroJuego;
    String tema;
    public ListaEnlazed<Stack> stackis = new ListaEnlazed<Stack>();

    public Tablero(GridPane tableroJuego, String tema,ParameterDataModel model) {
        this.model = model;
        this.tableroJuego = tableroJuego;
        this.tema = tema;
    }

//    public ParameterDataModel getModel() {
//        return model;
//    }
//
//    public void CrearTablero(GridPane tableroJuego, String tema) {
//        int filas = model.getTableroFilas();
//        int columnas = model.getTableroColumnas();
//        int id = 0;
//        for (int i = 0; i < columnas; i++) {
//            for (int j = 0; j < filas; j++) {
//                Stack stack = new Stack(i, j);
//                stack.setId(id);
//                stack.setPrefWidth(100);
//                stack.setPrefHeight(100);
//                stack.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                setTema(stack, tema);
//                tableroJuego.add(stack, i, j);
//                stackis.add(stack);
//                id++;
//
//            }
//        }
//    }
//
//    public void setTema(Stack stak, String tema) {
//        Color color = null;
//        if (tema == "oscuro") {
//            color = Color.web("black");
//        } else if (tema == "claro") {
//            color = Color.web("white");
//        }
//        stak.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
//    }
//
    public ListaEnlazed<Stack> getStackis() {
        return stackis;
    }
//
   public Stack getStack(int id) {
        return stackis.getElemento(id).getData();
    }





















    /*private Celdas[][] tablero;
    private int filas;
    private int columnas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        tablero = new Celdas[filas][columnas];
        for (int i = 0 ; i< filas;i++){
            for (int j = 0; j< columnas;j++){
                tablero[i][j]= new Celdas();
            }
        }
    }
    public void addIndividuoT(int x, int y ,Individuo individuo){
        tablero[x][y].addIndividuo(individuo);

    }
    public void addRecursoT(int x, int y, Recursos recursos){
        tablero[x][y].addRecurso(recursos);
    }
*/
}
