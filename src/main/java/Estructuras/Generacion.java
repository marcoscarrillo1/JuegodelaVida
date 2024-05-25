package Estructuras;

import Individuo.Individuo;

public class Generacion {
    public Individuo padre;
    public Individuo madre;

    public Individuo getPadre() {
        return padre;
    }

    public Individuo getMadre() {
        return madre;
    }

    public void setPadre(Individuo padre) {
        this.padre = padre;
    }

    public void setMadre(Individuo madre) {
        this.madre = madre;
    }
}
