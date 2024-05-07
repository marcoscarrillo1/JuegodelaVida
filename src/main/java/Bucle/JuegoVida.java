package Bucle;

import Individuo.Individuo;
import Recursos.Recursos;
import Tablero.Tablero;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class JuegoVida {
    public static Tablero tablero;
    private boolean juego;

    public JuegoVida(GridPane tablero) {
        this.tablero = new Tablero(tablero);
        this.juego = true;

    }

    private void addInidividuo(Celdas celdas) {
        Random random = new Random();
        int tipo = random.nextInt(2);
        Individuo individuonuevo = new Individuo(tipo, new Arbol<>(null));
        addTipo(celdas, 1);
        celdas.getIndividuos().add(individuonuevo);
        System.out.println("Se ha añadido un nuevo individuo");
    }
    private void addRecursos(Celdas celdas,int tipo){
        Recursos recursonuevo=new Recursos(tipo);
        addTipo(celdas,tipo);
        celdas.getRecursos().add(recursonuevo);
        System.out.println("Se ha añadido un nuevo recurso");
    }

}
