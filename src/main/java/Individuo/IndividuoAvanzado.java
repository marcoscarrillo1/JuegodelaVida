package Individuo;

public class IndividuoAvanzado extends Individuo{

    public IndividuoAvanzado(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }


    public void mover() {

    }

    public Individuo cambiarTipo(int x) {
        return this;
    }
}
