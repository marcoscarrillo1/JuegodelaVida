package Individuo;

public class IndividuoAvanzado extends Individuo{

    public IndividuoAvanzado(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte, int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }

    @Override
    public void mover() {

    }
    @Override
    public Individuo cambiarTipo(int x) {
        return this;
    }
}
