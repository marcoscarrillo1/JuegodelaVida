package Estructuras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaSimpleTest {

    @Test
    void isVacia() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1= null;
        assertEquals(true,l1.isVacia() );
    }

    @Test
    void vaciar() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Hola");
        l1.vaciar();

        assertEquals(true,l1.isVacia() );
    }

    @Test
    void add() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        ElementoLS e2= new ElementoLS();
        e2.setData("Adios");
        ElementoLS e3= new ElementoLS();
        assertEquals("Adios",e3.getData());


    }



    @Test
    void insert() {
        ElementoLS e= new ElementoLS();

        ListaSimple l1= new ListaSimple(3);
        l1.insert(2,"Hola");


        assertEquals("Hola",l1.getELemento(2).getData());
    }



    @Test
    void del() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        l1.del(0);
        assertEquals(null,l1.getELemento(0));
    }

    @Test
    void getNumeroElementos() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        assertEquals(1,l1.getNumeroElementos());
    }

    @Test
    void getPrimero() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        assertEquals("Adios",l1.getPrimero().getClass());
    }

    @Test
    void getPosicion() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        ElementoLS e2= new ElementoLS();
        l1.add(e2);
        assertEquals(1,l1.getPosicion(e2));
    }

    @Test
    void getUltimo() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        ElementoLS e2= new ElementoLS();
        e2.setData("Hola");
        l1.add(e2);
        assertEquals("Hola",l1.getUltimo().getData());
    }

    @Test
    void getELemento() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);

        assertEquals("Adios",l1.getELemento(0).getData());

    }

    @Test
    void getSiguiente() {
        ListaSimple l1= new ListaSimple(3);
        ElementoLS e1= new ElementoLS();
        e1.setData("Adios");
        l1.add(e1);
        ElementoLS e2= new ElementoLS();
        e1.setData("Hola");
        l1.add(e2);
        assertEquals(null,l1.getSiguiente(e1).getData());

    }}