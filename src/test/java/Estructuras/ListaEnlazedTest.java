package Estructuras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazedTest {

    @Test
    void isVacia() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("p");
        l1.add(e1);
        assertEquals(false,l1.isVacia());
    }

    @Test
    void vaciar() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("p");
        l1.add(e1);
        l1.vaciar();
        assertEquals(true,l1.isVacia());
    }

    @Test
    void add() {
        ListaEnlazed l1 = new ListaEnlazed();

        l1.insert("p",0);

        assertEquals("p",l1.getPrimero().getData());
    }

    @Test
    void insert() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();

        e1.setData(".");
        l1.insert(e1,0);
        ElementoLe e2= new ElementoLe();

        e1.setData("." +
                "p");
        l1.insert(e2,1);
        ElementoLe x = l1.getElemento(0);
        x.setData("rr");
        assertEquals("rr",x.getData());
    }

    @Test
    void getNumeroElementos() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData(".");
        l1.insert(e1,0);
        assertEquals(1,l1.getNumeroElementos());
    }

    @Test
    void getPrimero() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("FErnando");
        l1.add(e1);
        assertEquals(e1,l1.getPrimero().getData());
    }

    @Test
    void getPosicion() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("FErnando");
        l1.add(e1);
        ListaEnlazed l2 = new ListaEnlazed();
        ElementoLe e2= new ElementoLe();
        e2.setData("NAno");
        l2.add(e2);
        assertEquals(1,l1.getPosicion(e2));
    }

    @Test
    void getUltimo() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("FErnando");
        l1.insert("e",0);
        assertEquals("e",l1.getUltimo().getData());
    }

    @Test
    void getSiguiente() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();

        l1.add(e1);
        ListaEnlazed l2 = new ListaEnlazed();
        ElementoLe e2= new ElementoLe();
        e2.setData("NAno");
        l1.add(e2);
        assertEquals("NAno",l1.getSiguiente(e2).getData());


    }

    @Test
    void getElemento() {
        ListaEnlazed l1 = new ListaEnlazed();
        ElementoLe e1= new ElementoLe();
        e1.setData("FErnando");
        l1.add(e1);

        ElementoLe e2= new ElementoLe();
        e2.setData("NAno");
        l1.add(e2);
        assertEquals(e2,l1.getElemento(0));
    }


}