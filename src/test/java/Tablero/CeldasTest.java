package Tablero;
import Estructuras.ListaEnlazed;
import Individuo.Individuo;

import Recursos.Recursos;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class CeldasTest {

    @Test
    void addIndividuo() {
        Individuo individuo=null;
        Celdas celda=null;
        celda.addIndividuo(individuo);
        assertEquals(1, celda.getIndividuoListaEnlazed().getNumeroElementos());
    }


    @Test
    void addRecurso() {
        Recursos recurso=null;
        Celdas celda=null;
        celda.addRecurso(recurso);
        assertEquals(1,celda.getRecursosListaEnlazed().getNumeroElementos());


    }
}