package Bucle;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import Individuo.IndividuoBasico;
import Individuo.IndividuoNormal;
import Individuo.IndividuoAvanzado;
import Recursos.Recursos;
import Recursos.Agua;
import Recursos.Tesoro;
import Recursos.Biblioteca;
import Recursos.Comida;
import Recursos.Montaña;
import Recursos.Pozo;
import Tablero.Tablero;
import Tablero.Stack;
import Tablero.Celdas;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class JuegoVida  {}


    public  Tablero tablero;
    private boolean juego;

    public void JuegoVida(GridPane tablero) {
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
    private void addTipo(){}

 private void reproduccion(){
      ListaEnlazed<Stack> listastakis=tablero.getStackis();
      for(int i=0;i<listastakis.getNumeroElementos();i++){
          Stack ahora=listastakis.getElemento(i).getData();
          if(ahora.getIndividuos().getNumeroElementos()==2){
              Individuo paco=ahora.getIndividuos().getDatos(0);
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
  }
  public int generarID(){
return 4;
  }
  public void eliminarIndividuos(){
      ListaEnlazed<Stack> listastakis=tablero.getStackis();
      ListaEnlazed<Individuo> listaaborrar=new ListaEnlazed<Individuo>();
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
         eliminarIndividuos(listaaborrar);
      }


  }
  public void eliminarIndividuos(ListaEnlazed<Individuo> lista){
      ListaEnlazed<Individuo> todos= getIndiviuos();
      for(int i=0;i<lista.getNumeroElementos();i++){
          for(int j=0;j<todos.getNumeroElementos();j++){
              if(lista.getDatos(i).getIdentificador()==todos.getDatos(j).getIdentificador()){
                  todos.del(j);
              }
          }
      }
  }
  public void bucledecontrol(){
      individuoactualizado();
      recursoactivo();
      movimiento;
      mejora();
      reproduccion();
      clonacion();
      crearrecursos();
  }
  public void crearrecursos(){
      Random random=new Random();
      Agua agua=new Agua();
      Biblioteca biblio=new Biblioteca();
      Comida comida=new Comida();
      Montaña montaña=new Montaña();
      Pozo pozo=new Pozo();
      Tesoro tesoro=new Tesoro();
        for(int i=0;i<tablero.getStackis().getNumeroElementos();i++){
            int x= random.nextInt(101);
            if(x<agua.getProbabilidadagua()){
                addRecursos(tablero.getStack(i));
            }else if(x<biblio.getProbabilidadbiblio()){
                addRecursos(tablero.getStack(i));
            }else if(x<comida.getProbabilidadcomida()){
                addRecursos(tablero.getStack(i));
            }else if(x<montaña.getProbabilidadmontaña()){
                addRecursos(tablero.getStack(i));
            }else if(x<pozo.getProbabilidadpozo()){
                addRecursos(tablero.getStack(i));
            }else if(x<tesoro.getProbabilidadtesoro()){
                addRecursos(tablero.getStack(i));
            }
        }
  }
  public void mejora() {
      ListaEnlazed<Stack> listastakis = tablero.getStackis();
      for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
          Stack actual = listastakis.getDatos(i);
          Individuo individuo = actual.getIndividuos().getDatos(0);
          Recursos recurso = actual.getRecursos().getDatos(0);
          if (individuo != null && recurso != null) {
              if (recurso.getClass() == Agua.class) {
                  recurso.Propiedad(individuo);
              } else if (recurso.getClass() == Biblioteca.class) {
                  recurso.Propiedad(individuo);
              } else if (recurso.getClass() == Tesoro.class) {
                  recurso.Propiedad(individuo);
              } else if (recurso.getClass() == Pozo.class) {
                  recurso.Propiedad(individuo);
              } else if (recurso.getClass() == Montaña.class) {
                  recurso.Propiedad(individuo);
              } else if (recurso.getClass() == Comida.class) {
                  recurso.Propiedad(individuo);
              }
          }


      }
  }


      public void individuoactualizado () {
          ListaEnlazed<Stack> listastakis = tablero.getStackis();
          for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
              Stack actual = listastakis.getDatos(i);
              Individuo individuo = actual.getIndividuos().getDatos(0);
              individuo.setTurnosVida(individuo.getTurnosVida() - 1);
              individuo.setClonacion(individuo.getClonacion() - 10);
              individuo.setReproducion(individuo.getReproducion() - 10);
              if (individuo.getTurnosVida() == 0) {
                  eliminarIndividuos(individuo);
              }
          }
      }
      public void recursoactivo () {
          ListaEnlazed<Stack> listastakis = tablero.getStackis();
          for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
              Stack actual = listastakis.getDatos(i);
              Recursos recurso = actual.getRecursos().getDatos(0);
              recurso.setTiempo(recurso.getTiempo() - 1);
              if (recurso.getTiempo() == 0) {
                  eliminarrecurso(recurso);
              }
          }
      }
      public void eliminarrecurso () {
          ListaEnlazed<Recursos> todos = getRecursos();
          ListaEnlazed<Recursos> lista = new ListaEnlazed<Recursos>();
          for (int i = 0; i < todos.getNumeroElementos(); i++) {
              Recursos recurso = todos.getDatos(i);
              if (recurso.getTiempo() == 0) {
                  int stackid =
                  int celdaid =
                          tablero.getStack(stackid).getCelda(celdaid).setHayalguien(false);
              } for (int numrecursos = 0; numrecursos < 3; numrecursos++) {
                  if (tablero.getStack(stackid).getRecursos().getDatos(numrecursos) == recurso) {
                      tablero.getStack(stackid).getRecursos().del(numrecursos);
                  }
              }
              lista.add(recurso);

          } eliminarrecurso(lista);
      }


      private void eliminarrecurso (ListaEnlazed < Recursos > list) {
          ListaEnlazed<Recursos> todos = getRecursos();
          for (int i = 0; i < list.getNumeroElementos(); i++) {
              for (int j = 0; j < todos.getNumeroElementos(); j++) {
                  if (list.getDatos(i) == todos.getDatos(j)) {
                      todos.del(j);
                  }
              }
          }
      }
      public void clonacion () {
          ListaEnlazed<Stack> listastakis = tablero.getStackis();
          Random random = new Random();
          int x = random.nextInt(101);
          for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
              Stack actual = listastakis.getDatos(i);
              Individuo individuo = actual.getIndividuos().getDatos(0);
              Individuo individuonuevo;
              if (individuo.getClonacion() > x) {
                  if (individuo.getTipo() == 1) {
                      individuonuevo = new IndividuoBasico();
                  } else if (individuo.getTipo() == 2) {
                      individuonuevo = new IndividuoNormal();
                  } else {
                      individuonuevo = new IndividuoAvanzado();
                  }
              }
              actual.getIndividuos().add(individuonuevo);
          }


      }
      public void acabar () {

      }

*/
