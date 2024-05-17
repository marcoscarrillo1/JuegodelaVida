package Individuo;

import Estructuras.ListaEnlazed;
import Estructuras.ListaSimple;
import Tablero.Celdas;

import java.util.Random;

public class IndividuoBasico extends Individuo {


    public IndividuoBasico(int identificador, int generacion, int turnosVida, int reproducion, int clonacion, int muerte,int tipo) {
        super(identificador, generacion, turnosVida, reproducion, clonacion, muerte, tipo);
    }

    @Override
    public void mover(int maxcolumnas, int maxfilas, Celdas[][] matriz) {
        /* 1 es arriba
            2 es derecha
            3 es abajo
            4 es izquierda

         */

        ListaEnlazed<Integer> listamovimientos=new ListaEnlazed<Integer>();
        listamovimientos.add(1);
        listamovimientos.add(2);
        listamovimientos.add(3);
        listamovimientos.add(4);
        Random random=new Random();
        int azar= random.nextInt(listamovimientos.getNumeroElementos());
        int Movimientoaleatorio=random.nextInt(listamovimientos.getElemento(azar).getData());
        if(Movimientoaleatorio==1){
            //matriz[][]
        }
        else if (Movimientoaleatorio==2){

        }
        else if (Movimientoaleatorio==3){

        }else if (Movimientoaleatorio==4){

        }

    }
    @Override
    public Individuo cambiarTipo(int x) {
        return new IndividuoNormal(identificador,generacion,turnosVida,reproducion,clonacion,muerte,x);
    }
}