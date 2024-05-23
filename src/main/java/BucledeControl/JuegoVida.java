package BucledeControl;


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
import Tablero.Celdas;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import org.example.trabajo.ParameterDataModel;
import org.example.trabajo.ParameterDataModelProperties;
import org.example.trabajo.TableroController;

import java.util.Random;

public class JuegoVida {


    public TableroController tablero;
    private boolean juego;

    ParameterDataModelProperties model;
    private Timeline control = new Timeline(1);

    public JuegoVida(TableroController tablero, boolean juego, ParameterDataModelProperties model) {
        this.tablero = tablero;
        this.juego = juego;
        this.model = model;
    }

    public void JuegoVida(GridPane tablero) {
        this.juego = false;

    }

    public boolean getJuego() {
        return juego;
    }

    public void setJuego(boolean juego) {
        this.juego = juego;
    }

    private void addTipo() {
    }

    private void reproduccion() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas ahora = listastakis.getElemento(i).getData();
            if (ahora.getIndividuoListaEnlazed().getNumeroElementos() == 2) {
                Individuo paco = ahora.getIndividuoListaEnlazed().getDatos(0);
                Individuo jose = ahora.getIndividuoListaEnlazed().getDatos(1);
                int probabilidad1 = paco.getReproducion();
                int probabilidad2 = jose.getReproducion();
                Random random = new Random();
                int x = random.nextInt(101);
                if (x < probabilidad1 && x < probabilidad2) {
                    Integer tipo1 = paco.getTipo();
                    Integer tipo2 = jose.getTipo();
                    Integer tipohijo;
                    if (tipo1 <= tipo2) {
                        tipohijo = tipo2;
                    } else {
                        tipohijo = tipo1;
                    }
                    Individuo elnuevo;
                    if (tipohijo == 1) {
                        elnuevo = new IndividuoBasico();
                    } else if (tipohijo == 2) {
                        elnuevo = new IndividuoNormal();
                    } else {
                        elnuevo = new IndividuoAvanzado();
                    }
                    generarID(elnuevo);
                    ahora.getIndividuoListaEnlazed().add(elnuevo);
                    int vida = 90000;
                    for (int y = 0; y < ahora.getIndividuoListaEnlazed().getNumeroElementos(); y++) {
                        int vidaloca = ahora.getIndividuoListaEnlazed().getElemento(y).getData().getTurnosVida();
                        if (vidaloca < vida) {
                            vida = vidaloca;
                        }
                    }
                    for (int f = 0; f < ahora.getIndividuoListaEnlazed().getNumeroElementos(); f++) {
                        if (ahora.getIndividuoListaEnlazed().getElemento(f).getData().getTurnosVida() == vida) {
                            ahora.getIndividuoListaEnlazed().del(f);
                        }
                    }


                } else {
                    ahora.getIndividuoListaEnlazed().del(1);
                    ahora.getIndividuoListaEnlazed().del(0);

                }
            }
        }
    }

    public void generarID(Individuo indi) {
        int id = 0;
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                for (int x = 0; x < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); x++) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(x);
                    if (individuo.getIdentificador() > id) {
                        id = individuo.getIdentificador();
                    }
                }
            }
        }
        indi.setIdentificador(id + 1);
    }

    public void eliminarIndividuos() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        ListaEnlazed<Individuo> listaaborrar = new ListaEnlazed<Individuo>();
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas ahora = listastakis.getDatos(i);
            if (!ahora.getIndividuoListaEnlazed().isVacia()) {
                int x = 0;
                while (x < ahora.getIndividuoListaEnlazed().getNumeroElementos()) {
                    if (ahora.getIndividuoListaEnlazed().getElemento(x).getData().getTurnosVida() <= 0) {
                        listaaborrar.add(ahora.getIndividuoListaEnlazed().getElemento(x));
                        ahora.getIndividuoListaEnlazed().del(x);
                    } else {
                        x++;
                    }
                }
            }
        }
        if (listaaborrar.isVacia()) {
            return;
        } else {
            eliminarIndividuos(listaaborrar);
        }


    }

    public void eliminarIndividuos(ListaEnlazed<Individuo> lista) {
        ListaEnlazed<Individuo> todos = new ListaEnlazed<Individuo>();
        for (int i = 0; i < lista.getNumeroElementos(); i++) {
            for (int j = 0; j < todos.getNumeroElementos(); j++) {
                if (lista.getDatos(i).getIdentificador() == todos.getDatos(j).getIdentificador()) {
                    todos.del(j);
                }
            }
        }
    }

    public void bucledecontrol() {
        if (juego){
            control.play();
            int turnos = tablero.getTurnosDeJuego();
            turnos++;
            tablero.setTurnosDeJuego(turnos);
            individuoactualizado();

            recursoactivo();

            movimiento();

            mejora();

            reproduccion();

            clonacion();

            crearrecursos();

            setJuego(false);

        }else {
            control.stop();
        }
    }

    public void movimiento() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                System.out.println(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos());
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 1) {
                    System.out.println("moviendome");
                    Random random = new Random();
                    int dir = random.nextInt(1, 8);
                    if (dir == 1) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX();
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                    } else if (dir == 2) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 3) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY();
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 4) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 5) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX();
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 6) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 7) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY();
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 8) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    }
                } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 2) {

                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(i);
                    if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                        Celdas ruta = new Celdas();
                        Random random = new Random();
                        while (ruta.getRecursosListaEnlazed().isVacia()) {
                            int x = random.nextInt(model.tableroColumnasProperty().getValue().intValue());
                            int y = random.nextInt(model.tableroFilasProperty().getValue().intValue());
                            ruta = new Celdas(x, y);
                        }
                        individuo.setRuta(ruta);
                    }
                    if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY()) {
                        if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }
                        if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }


                    } else if (tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                        if (tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }
                        if (tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }
                    }


                } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 3) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(i);
                    if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                        Celdas ruta = new Celdas();
                        double distanciaminima = Double.MAX_VALUE;
                        int x1 = tablero.getCeldas().getElemento(j).getData().getX();
                        int y1 = tablero.getCeldas().getElemento(j).getData().getY();
                        for (int p = 0; p < tablero.getCeldas().getNumeroElementos(); p++) {
                            int x2 = tablero.getCeldas().getElemento(p).getData().getX();
                            int y2 = tablero.getCeldas().getElemento(p).getData().getY();
                            if (!tablero.getCeldas().getElemento(p).getData().getRecursosListaEnlazed().isVacia()) {
                                double distancia = Math.sqrt((x1 - x2) ^ 2 - (y1 - y2) ^ 2);
                                if (distancia < distanciaminima && distancia > 0) {
                                    distanciaminima = distancia;
                                    ruta = tablero.getCeldas().getElemento(p).getData();
                                }
                            }
                        }
                        individuo.setRuta(ruta);
                    }
                    if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() || tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                        if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                        } else if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() == individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }

                        } else if (tablero.getCeldas().getElemento(j).getData().getY() == individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }
                            if (tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                        }
                    }
                }
            }
        }
    }


    public void crearrecursos() {
        Random random = new Random();
        Agua agua = new Agua();
        Biblioteca biblio = new Biblioteca();
        Comida comida = new Comida();
        Montaña montaña = new Montaña();
        Pozo pozo = new Pozo();
        Tesoro tesoro = new Tesoro();
        for (int i = 0; i < tablero.getCeldas().getNumeroElementos(); i++) {
            int x = random.nextInt(101);
            if (x < agua.getProbabilidadagua()) {
                tablero.getcelditas(i).addRecurso(agua);
                System.out.println("aguita");
            }
            if (x < biblio.getProbabilidadbiblio()) {
                tablero.getcelditas(i).addRecurso(biblio);
                System.out.println("aguita");
            }
            if (x < comida.getProbabilidadcomida()) {
                tablero.getcelditas(i).addRecurso(comida);
                System.out.println("aguita");
            }
            if (x < montaña.getProbabilidadmontaña() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                tablero.getcelditas(i).addRecurso(montaña);
                System.out.println("aguita");
            }
            if (x < pozo.getProbabilidadpozo() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                tablero.getcelditas(i).addRecurso(pozo);
                System.out.println("aguita");
            }
            if (x < tesoro.getProbabilidadtesoro() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                tablero.getcelditas(i).addRecurso(tesoro);
                System.out.println("aguita");
            }
        }
    }

    public void mejora() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();

        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas actual = listastakis.getDatos(i);
            ListaEnlazed<Individuo> listIndi = actual.getIndividuoListaEnlazed();
            ListaEnlazed<Recursos> listRecur = actual.getRecursosListaEnlazed();
           // System.out.println("Antes if");
            if(!listIndi.isVacia() && !listRecur.isVacia()){

            Individuo individuo = actual.getIndividuoListaEnlazed().getDatos(0);
            Recursos recurso = actual.getRecursosListaEnlazed().getDatos(0);

            if(recurso instanceof Agua || recurso instanceof Pozo ||recurso instanceof Montaña ||
                    recurso instanceof Biblioteca ||recurso instanceof Comida ||recurso instanceof Tesoro ){
                recurso.Propiedad(individuo);

            }}


        }
    }


    public void individuoactualizado() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(0);
                individuo.setTurnosVida(individuo.getTurnosVida() - 1);
                individuo.setClonacion(individuo.getClonacion() - 10);
                individuo.setReproducion(individuo.getReproducion() - 10);
                if (individuo.getTurnosVida() == 0) {
                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                }
            }
        }
    }

    public void recursoactivo() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getNumeroElementos(); i++) {
            Recursos recurso = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getDatos(i);
            recurso.setTiempo(recurso.getTiempo() - 1);
            if (recurso.getTiempo() == 0) {
                tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().del(i);
            }
        }}
    }

    public void eliminarrecurso() {
        ListaEnlazed<Recursos> todos = new ListaEnlazed<>();
        ListaEnlazed<Recursos> lista = new ListaEnlazed<>();
        for (int i = 0; i < todos.getNumeroElementos(); i++) {
            Recursos recurso = todos.getDatos(i);
            if (recurso.getTiempo() == 0) {
                Celdas celdaid = recurso.getCelda();
                celdaid.setHayalguien(false);
                lista.add(recurso);
            }


        }
        eliminarrecurso(lista);
    }


    private void eliminarrecurso(ListaEnlazed<Recursos> list) {
        ListaEnlazed<Recursos> todos = new ListaEnlazed<>();
        for (int i = 0; i < list.getNumeroElementos(); i++) {
            for (int j = 0; j < todos.getNumeroElementos(); j++) {
                if (list.getDatos(i) == todos.getDatos(j)) {
                    todos.del(j);
                }
            }
        }
    }

    public void clonacion() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        Random random = new Random();
        int x = random.nextInt(101);
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas actual = listastakis.getElemento(i).getData();
            if (actual.getIndividuoListaEnlazed().getNumeroElementos() < 2 && !actual.getIndividuoListaEnlazed().isVacia()) {
                Individuo individuo = actual.getIndividuoListaEnlazed().getDatos(0);
                Individuo individuonuevo = null;
                if (individuo.getClonacion() > x) {
                    if (individuo.getTipo() == 1) {
                        individuonuevo = new IndividuoBasico();
                    } else if (individuo.getTipo() == 2) {
                        individuonuevo = new IndividuoNormal();
                    } else {
                        individuonuevo = new IndividuoAvanzado();
                    }
                }
                if (individuonuevo != null) {
                    tablero.getcelditas(i).addIndividuo(individuonuevo);
                }
            }
        }


    }

    public boolean acabar() {
        return juego = false;
    }

    public void getcaminobasico() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 1) {
                    Random random = new Random();
                    int dir = random.nextInt(1, 8);
                    if (dir == 1) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX();
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                    } else if (dir == 2) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 3) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY();
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 4) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 5) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX();
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 6) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 7) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY();
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);


                    } else if (dir == 8) {
                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                        int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));

                            }
                        }
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                    }
                }
            }
        }
    }

    public void getcaminonormal() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 2) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(i);
                    if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                        Celdas ruta = new Celdas();
                        Random random = new Random();
                        while (ruta.getRecursosListaEnlazed().isVacia()) {
                            int x = random.nextInt(model.tableroColumnasProperty().getValue().intValue());
                            int y = random.nextInt(model.tableroFilasProperty().getValue().intValue());
                            ruta = new Celdas(x, y);
                        }
                        individuo.setRuta(ruta);
                    }
                    if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY()) {
                        if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }
                        if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }


                    } else if (tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                        if (tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                        }
                        if (tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                            for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                    tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                }

                            }
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                        }
                    }
                }
            }
        }
    }

    public void getcaminoavanzado() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 3) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(i);
                    if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                        Celdas ruta = new Celdas();
                        double distanciaminima = Double.MAX_VALUE;
                        int x1 = tablero.getCeldas().getElemento(j).getData().getX();
                        int y1 = tablero.getCeldas().getElemento(j).getData().getY();
                        for (int p = 0; p < tablero.getCeldas().getNumeroElementos(); p++) {
                            int x2 = tablero.getCeldas().getElemento(p).getData().getX();
                            int y2 = tablero.getCeldas().getElemento(p).getData().getY();
                            if (!tablero.getCeldas().getElemento(p).getData().getRecursosListaEnlazed().isVacia()) {
                                double distancia = Math.sqrt((x1 - x2) ^ 2 - (y1 - y2) ^ 2);
                                if (distancia < distanciaminima && distancia > 0) {
                                    distanciaminima = distancia;
                                    ruta = tablero.getCeldas().getElemento(p).getData();
                                }
                            }
                        }
                        individuo.setRuta(ruta);
                    }
                    if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() || tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                        if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                        } else if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() == individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }
                            if (tablero.getCeldas().getElemento(j).getData().getY() > individuo.getRuta().getY()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }

                        } else if (tablero.getCeldas().getElemento(j).getData().getY() == individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                            if (tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }
                            if (tablero.getCeldas().getElemento(j).getData().getX() > individuo.getRuta().getX()) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                    }

                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                            }

                        }
                    }
                }
            }
        }
    }

}
