package Tablero;

import Individuo.Individuo;
import Recursos.Recursos;

public class Tablero {
    private Celdas[][] tablero;
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
}
