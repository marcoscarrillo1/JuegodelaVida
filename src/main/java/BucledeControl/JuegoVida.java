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
import javafx.scene.layout.GridPane;
import org.example.trabajo.ParameterDataModel;
import org.example.trabajo.TableroController;

import java.util.Random;

public class JuegoVida {


    public TableroController tablero;
    private boolean juego;
    ParameterDataModel model;

    public void JuegoVida(GridPane tablero) {
        this.juego = false;

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
                    ahora.getIndividuoListaEnlazed().add(elnuevo);


                } else {
                    //mueren
                }
            }
        }
    }

    public int generarID() {
        return 4;
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
        while (juego) {

            individuoactualizado();
            recursoactivo();
            movimiento();
            mejora();
            reproduccion();
            clonacion();
            crearrecursos();
        }
    }

    public void movimiento() {
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
                } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 2) {

                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getDatos(i);
                    if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                        Celdas ruta = new Celdas();
                        Random random = new Random();
                        while (ruta.getRecursosListaEnlazed().isVacia()) {
                            int x = random.nextInt(model.getTableroColumnas());
                            int y = random.nextInt(model.getTableroFilas());
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
                            int x = random.nextInt(model.getTableroColumnas());
                            int y = random.nextInt(model.getTableroFilas());
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
            } else if (x < biblio.getProbabilidadbiblio()) {
                tablero.getcelditas(i).addRecurso(biblio);
            } else if (x < comida.getProbabilidadcomida()) {
                tablero.getcelditas(i).addRecurso(comida);
            } else if (x < montaña.getProbabilidadmontaña()) {
                tablero.getcelditas(i).addRecurso(montaña);
            } else if (x < pozo.getProbabilidadpozo()) {
                tablero.getcelditas(i).addRecurso(pozo);
            } else if (x < tesoro.getProbabilidadtesoro()) {
                tablero.getcelditas(i).addRecurso(tesoro);
            }
        }
    }

    public void mejora() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas actual = listastakis.getDatos(i);
            Individuo individuo = actual.getIndividuoListaEnlazed().getDatos(0);
            Recursos recurso = actual.getRecursosListaEnlazed().getDatos(0);
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


    public void individuoactualizado() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        ListaEnlazed<Individuo> listaaborrar = new ListaEnlazed<>();
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas actual = listastakis.getDatos(i);
            Individuo individuo = actual.getIndividuoListaEnlazed().getDatos(0);
            individuo.setTurnosVida(individuo.getTurnosVida() - 1);
            individuo.setClonacion(individuo.getClonacion() - 10);
            individuo.setReproducion(individuo.getReproducion() - 10);
            if (individuo.getTurnosVida() == 0) {
                listaaborrar.add(individuo);
            }
        }
        eliminarIndividuos(listaaborrar);
    }

    public void recursoactivo() {
        ListaEnlazed<Celdas> listastakis = tablero.getCeldas();
        ListaEnlazed<Recursos> listarecurso = new ListaEnlazed<>();
        for (int i = 0; i < listastakis.getNumeroElementos(); i++) {
            Celdas actual = listastakis.getDatos(i);
            Recursos recurso = actual.getRecursosListaEnlazed().getDatos(0);
            recurso.setTiempo(recurso.getTiempo() - 1);
            if (recurso.getTiempo() == 0) {
                listarecurso.add(recurso);
            }
        }
        eliminarrecurso(listarecurso);
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

    public void acabar() {

    }

}
