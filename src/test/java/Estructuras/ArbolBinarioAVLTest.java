package Estructuras;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class ArbolBinarioAVLTest {

    @Test
    void eliminar() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1=new Nodo<>(2);
        Nodo<Integer> nodo6=new Nodo<>(9);
        Nodo<Integer> nodo2=new Nodo<>(8);
        Nodo<Integer> nodo3=new Nodo<>(7);
        Nodo<Integer> nodo4=new Nodo<>(6);
        Nodo<Integer> nodo5=new Nodo<>(11);
        arbol.getRaiz().setNodoizquierda(nodo1);
        arbol.getRaiz().setNododerecha(nodo3);
        arbol.getRaiz().getNodoizquierda().setNodoizquierda(nodo2);
        arbol.getRaiz().getNodoizquierda().getNodoizquierda().setNodoizquierda(nodo5);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
        arbol.Eliminar(nodo5,arbol);
        assertTrue(arbol.isequilibradorecurssivo(arbol));

    }

    @Test
    void insercion() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1=new Nodo<>(2);
        Nodo<Integer> nodo6=new Nodo<>(9);
        Nodo<Integer> nodo2=new Nodo<>(8);
        Nodo<Integer> nodo3=new Nodo<>(7);
        Nodo<Integer> nodo4=new Nodo<>(6);
        Nodo<Integer> nodo5=new Nodo<>(11);
        arbol.getRaiz().setNodoizquierda(nodo1);
        arbol.getRaiz().setNododerecha(nodo3);
        arbol.getRaiz().getNodoizquierda().setNodoizquierda(nodo2);
        arbol.insercion(nodo4,arbol);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
    }

    @Test
    void isequilibradorecurssivo() {
        Nodo<Integer>nodo1=new Nodo<>(7);
        Nodo<Integer> nodo2=new Nodo<>(3);
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(5);
        arbol.getRaiz().setNododerecha(nodo1);
        arbol.getRaiz().setNodoizquierda(nodo2);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
        arbol.add(4);
        arbol.add(2);
        arbol.add(6);
        arbol.add(8);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
    }

    @Test
    void rotacionIzqIzq() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1=new Nodo<>(2);
        Nodo<Integer> nodo4=new Nodo<>(9);
        Nodo<Integer> nodo2=new Nodo<>(8);
        Nodo<Integer> nodo3=new Nodo<>(7);
        arbol.getRaiz().setNododerecha(nodo1);
        arbol.getRaiz().setNodoizquierda(nodo4);
        arbol.getRaiz().getNodoizquierda().setNodoizquierda(nodo2);
        arbol.getRaiz().getNodoizquierda().getNodoizquierda().setNodoizquierda(nodo3);

        arbol.RotacionIzqIzq(nodo4);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
    }

    @Test
    void rotacionDerder() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1=new Nodo<>(2);
        Nodo<Integer> nodo4=new Nodo<>(9);
        Nodo<Integer> nodo2=new Nodo<>(8);
        Nodo<Integer> nodo3=new Nodo<>(7);
        arbol.getRaiz().setNodoizquierda(nodo1);
        arbol.getRaiz().setNododerecha(nodo4);
        arbol.getRaiz().getNododerecha().setNododerecha(nodo2);
        arbol.getRaiz().getNododerecha().getNododerecha().setNododerecha(nodo3);
        arbol.RotacionDerder(nodo4);
        assertTrue(arbol.isequilibradorecurssivo(arbol));

    }

    @Test
    void rotacionDerIzq() {
        ArbolBinarioAVL arbol = new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1 = new Nodo<>(9);
        Nodo<Integer> nodo2 = new Nodo<>(8);
        Nodo<Integer> nodo3 = new Nodo<>(7);
        Nodo<Integer> nodo4 = new Nodo<>(6);
        Nodo<Integer> nodo5=new Nodo<>(11);
        Nodo<Integer> nodo6=new Nodo<>(2);
        arbol.getRaiz().setNodoizquierda(nodo6);
        arbol.getRaiz().setNododerecha(nodo1);
        arbol.getRaiz().getNododerecha().setNododerecha(nodo4);
        arbol.getRaiz().getNododerecha().getNododerecha().setNodoizquierda(nodo2);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
        arbol.RotacionDerIzq(nodo1);

    }

    @Test
    void rotacionIzqder() {
        ArbolBinarioAVL arbol = new ArbolBinarioAVL(10);
        Nodo<Integer> nodo1 = new Nodo<>(9);
        Nodo<Integer> nodo2 = new Nodo<>(8);
        Nodo<Integer> nodo3 = new Nodo<>(7);
        Nodo<Integer> nodo4 = new Nodo<>(6);
        Nodo<Integer> nodo5=new Nodo<>(11);
        Nodo<Integer> nodo6=new Nodo<>(2);
        arbol.getRaiz().setNodoizquierda(nodo6);
        arbol.getRaiz().setNododerecha(nodo3);
        arbol.getRaiz().getNodoizquierda().setNodoizquierda(nodo2);
        arbol.getRaiz().getNodoizquierda().getNodoizquierda().setNododerecha(nodo1);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
        arbol.RotacionIzqder(nodo6);
        assertTrue(arbol.isequilibradorecurssivo(arbol));

    }

    @Test
    void verdesequilibrio() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(5);
        Nodo<Integer> nodo1=new Nodo<>(6);
        Nodo<Integer> nodo2=new Nodo<>(9);
        Nodo<Integer> nodo3 =new Nodo<>(2);
        Nodo<Integer> nodo4=new Nodo<>(1);
        Nodo<Integer> nodo5=new Nodo<>(14);
        arbol.add(nodo1);
        arbol.getRaiz().setNododerecha(nodo5);
        arbol.add(nodo2);
        arbol.add(nodo3);
        assertEquals("IzqIzq",arbol.Verdesequilibrio(arbol));
    }

    @Test
    void equilibrar() {
        ArbolBinarioAVL arbol=new ArbolBinarioAVL(7);
        Nodo<Integer> nodo1=new Nodo<>(4);
        Nodo<Integer> nodo2=new Nodo<>(9);
        Nodo<Integer> nodo3 =new Nodo<>(2);
        Nodo<Integer> nodo4=new Nodo<>(3);
        Nodo<Integer> nodo5=new Nodo<>(8);
        arbol.add(nodo1);
        arbol.getRaiz().setNododerecha(nodo5);
        arbol.add(nodo2);
        arbol.add(nodo3);
        assertFalse(arbol.isequilibradorecurssivo(arbol));
        arbol.equilibrar(arbol);
        assertTrue(arbol.isequilibradorecurssivo(arbol));
    }
}