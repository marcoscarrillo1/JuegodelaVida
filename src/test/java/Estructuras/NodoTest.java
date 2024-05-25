package Estructuras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodoTest {
    Nodo x = new Nodo(56);
    Nodo y = new Nodo(2);
    ArbolBinarioAVL<Integer> arbol= new ArbolBinarioAVL<>();
    @Test
    void getData() {
        assertEquals(x.getData(),56);
    }

    @Test
    void setData() {
        x.setData(45);
        assertEquals(x.getData(),45);
    }

    @Test
    void getNododerecha() {
        assertEquals(x.getNododerecha(),null);
    }

    @Test
    void getNodoizquierda() {
        assertEquals(x.getNodoizquierda(),null);
    }

    @Test
    void setNododerecha() {
        x.setNododerecha(y);
        assertEquals(x.getNododerecha(),y);

    }

    @Test
    void setNodoizquierda() {
        x.setNodoizquierda(y);
        assertEquals(x.getNodoizquierda(),y);
    }

    @Test
    void add() {
        arbol.add(3);
        arbol.add(7);
        arbol.add(2);
        arbol.add(4);
        arbol.add(6);
        arbol.add(8);
        arbol.add(0);
        arbol.add(1);

        assertEquals(3,arbol.getRaiz().getData());

    }
}