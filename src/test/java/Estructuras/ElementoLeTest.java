package Estructuras;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLeTest {


    @Test
    void insertarmeEn() {
        ElementoLe x = new ElementoLe();
        x.setData("p");
        ElementoLe y = new ElementoLe();
        x.setData("t");y.insertarmeEn(x);
        assertEquals(x.getData(),"t");
    }

    @Test
    void setSiguiente() {
        ElementoLe e1 = new ElementoLe();
        e1.setData("hola");
        ElementoLe e2= new ElementoLe();
        e2.setData("adios");
        e1.setSiguiente(e2);
        assertEquals("adios",e1.getSiguiente().getData());

    }

    @Test
    void getData() {
        ElementoLe x = new ElementoLe();
        x.setData("p");
        assertEquals(x.getData(),"p");


    }

    @Test
    void setData() {
        ElementoLe x = new ElementoLe();
        x.setData("p");
        assertEquals(x.getData(),"p");
    }
}