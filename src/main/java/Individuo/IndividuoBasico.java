package Individuo;

public class IndividuoBasico extends Individuo {


    public IndividuoBasico(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte,int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }


    public void mover() {

    }

    public Individuo cambiarTipo(int x) {
        return new IndividuoNormal(identificador,generacion,turnosVida,reproducion,clonacion,muerte,x);
    }
}
