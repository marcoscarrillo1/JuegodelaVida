package Bucle;

import Estructuras.ListaSimple;
import Individuo.Individuo;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Individuo.IndividuoAvanzado;
import Recursos.Recursos;
import Tablero.Tablero;
import Tablero.Celdas;
import Tablero.Stack;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class JuegoVida  {


    public static Tablero tablero;
    private boolean juego;

    /*public JuegoVida(GridPane tablero) {
        this.tablero = new Tablero(tablero);
        this.juego = true;

    }

    private void addInidividuo(Celdas celdas) {
        Random random = new Random();
        int tipo = random.nextInt(2);
        Individuo individuonuevo = new Individuo(tipo, new Arb<>(null));
        addTipo(celdas, 1);
        celdas.getIndividuos().add(individuonuevo);
        System.out.println("Se ha añadido un nuevo individuo");
    }
    private void addRecursos(Celdas celdas,int tipo){
        Recursos recursonuevo=new Recursos(tipo);
        addTipo(celdas,tipo);


        celdas.getRecursos().add(recursonuevo);
        System.out.println("Se ha añadido un nuevo recurso");
    }
    private void addTipo()*/

 /* private void reproduccion(){
      ListaSimple<Stack> listastakis=tablero.getStackis();
      for(int i=0;i<listastakis.getNumeroElementos();i++){
          Stack ahora=listastakis.getDatos(i);
          if(ahora.getIndividuos().getNumeroElementos()==2){
              Individuo paco=ahora.getIndividuos().getPrimero();
              Individuo jose=ahora.getIndividuos().getDatos(1);
              int probabilidad1=paco.getReproducion();
              int probabilidad2=jose.getReproducion();
              Random random=new Random();
              int x= random.nextInt(101);
              if(x<probabilidad1&&x<probabilidad2){
                  Integer tipo1=paco.getTipo();
                  Integer tipo2=jose.getTipo();
                  Integer tipohijo;
                  if(tipo1<=tipo2){
                    tipohijo=tipo2;
                  }else{
                      tipohijo=tipo1;
                  }
                  Individuo elnuevo;
                  if(tipohijo==1){
                      elnuevo=new IndividuoBasico();
                  } else if (tipohijo==2) {
                    elnuevo=new IndividuoNormal();
                  }else {
                      tipohijo=new IndividuoAvanzado();
                  }
                  ahora.getIndividuos().add(elnuevo);



              }else{
                  //mueren
              }
          }
      }
  }*/
  public int generarID(){
return 4;
  }
  public void eliminarIndividuos(){
      ListaSimple<Stack> listastakis=tablero.getStackis();
      ListaSimple<Individuo> listaaborrar=new ListaSimple<Individuo>();
      for(int i=0;i<listastakis.getNumeroElementos();i++){
          Stack ahora=listastakis.getDatos(i);
          if(!ahora.getIndividuos().isVacia()){
              int x=0;
              while(x<ahora.getIndividuos().getNumeroElementos()){
                  if(ahora.getIndividuos().getElemento(x).getData().getTurnosVida()<=0){
                      listaaborrar.add(ahora.getIndividuos().getElemento(x));
                      ahora.getIndividuos().del(x);
                  }else{
                      x++;
                  }
              }
          }
      }if(listaaborrar.isVacia()){
        return;
      }else{
         // eliminarIndividuos(listaaborrar);
      }


  }
  /*public void eliminarIndividuos(ListaSimple<Individuo> lista){
      ListaSimple<Individuo> todos= getIndiviuos();
      for(int i=0;i<lista.getNumeroElementos();i++){
          for(int j=0;j<todos.getNumeroElementos();j++){
              if(lista.getDatos(i).getIdentificador()==todos.getDatos(j).getIdentificador()){
                  todos.del(j);
              }
          }
      }*/
  }









