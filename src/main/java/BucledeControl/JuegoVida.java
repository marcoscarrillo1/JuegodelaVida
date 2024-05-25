package BucledeControl;


import Estructuras.Generacion;
import Estructuras.ListaEnlazed;
import Excepciones.IDexistente;
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
    public ListaEnlazed<Celdas> celdas;

    public void setModel(ParameterDataModelProperties model) {
        this.model = model;
    }

    public TableroController tablero;
    private boolean juego;

    ParameterDataModelProperties model;
    private Timeline control = new Timeline(1);

    public JuegoVida(TableroController tablero, boolean juego, ParameterDataModelProperties model) {
        this.tablero = tablero;
        this.juego = juego;
        this.model = model;
    }

    public JuegoVida(ListaEnlazed<Celdas> celdas) {
        this.celdas = celdas;
    }

    public boolean getJuego() {
        return juego;
    }

    public void setJuego(boolean juego) {
        this.juego = juego;
    }

    private void addTipo() {
    }

    private void reproduccion() throws IDexistente {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() >= 2) {
                    Individuo paco = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(0).getData();
                    Individuo jose = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(1).getData();
                    int probabilidad1 = paco.getReproducion();
                    int probabilidad2 = jose.getReproducion();
                    Random random = new Random();
                    int x = random.nextInt(101);
                    if (x < probabilidad1 && x < probabilidad2) {
                        Integer tipo1 = paco.getTipo();
                        Integer tipo2 = jose.getTipo();
                        Integer tipohijo;
                        int generacion = 0;
                        if (tipo1 <= tipo2) {
                            tipohijo = tipo2;
                        } else {
                            tipohijo = tipo1;
                        }
                        Individuo elnuevo;

                        if (tipohijo == 1) {
                            Generacion generacion2= new Generacion();
                            generacion2.setMadre(paco);
                            generacion2.setPadre(jose);
                            elnuevo = new IndividuoBasico(model,generacion2);
                        } else if (tipohijo == 2) {
                            Generacion generacion1=new Generacion();
                            generacion1.setMadre(paco);
                            generacion1.setPadre(jose);
                            elnuevo = new IndividuoNormal(model,generacion1);
                        } else {
                            Generacion generacion3=new Generacion();
                            generacion3.setMadre(paco);
                            generacion3.setPadre(jose);
                            elnuevo = new IndividuoAvanzado(model,generacion3);
                        }
                        generarID(elnuevo);
                        elnuevo.getGeneracion().setMadre(paco);
                        elnuevo.getGeneracion().setPadre(jose);
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().add(elnuevo);
                        int vida = 90000;
                        for (int y = 0; y < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); y++) {
                            int vidaloca = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(y).getData().getTurnosVida();
                            if (vidaloca < vida) {
                                vida = vidaloca;
                            }
                        }
                        for (int f = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; f > -1; f--) {
                            if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(f).getData().getTurnosVida() == vida) {
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(f);
                            }
                        }


                    } else {
                        for (int r = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; r > -1; r--) {
                            tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(r);
                        }

                    }
                }
            }
        }
    }

    public void generarID(Individuo indi) throws IDexistente {
        int id = 0;
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                for (int x = 0; x < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); x++) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(x).getData();
                    if (individuo.getIdentificador() > id) {
                        id = individuo.getIdentificador();
                    }
                }
            }
        }
        indi.setIdentificador(id + 1);
    }

    /*public void generargeneracion(Individuo indi) {
        int id = 0;
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = 0; i < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i++) {
                for (int x = 0; x < tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); x++) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(x).getData();
                    if (individuo.getGeneracion() > id) {
                        id = individuo.getGeneracion();
                    }
                }
            }
        }
        indi.setGeneracion(id + 1);
    }*/

    public void eliminarIndividuos() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos(); i > 0; i--) {
                Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                if (individuo.getTurnosVida() == 0) {
                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                }
            }
        }

    }

    /*public void eliminarIndividuos(ListaEnlazed<Individuo> lista) {
        ListaEnlazed<Individuo> todos = new ListaEnlazed<Individuo>();
        for (int i = 0; i < lista.getNumeroElementos(); i++) {
            for (int j = 0; j < todos.getNumeroElementos(); j++) {
                if (lista.getDatos(i).getIdentificador() == todos.getDatos(j).getIdentificador()) {
                    todos.del(j);
                }
            }
        }
    }*/

    public void bucledecontrol() throws IDexistente {
        if (juego) {
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
            tablero.actualizarColor();

        } else {
            control.stop();
        }
    }

    public void colorear() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().isVacia()) {
                    //blanca
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Biblioteca.class) {
                    //morada
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Comida.class) {
                    //verde
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Montaña.class) {
                    //marron
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Agua.class) {
                    //azul
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Pozo.class) {
                    //negro
                } else if (tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(0).getData().getClass() == Tesoro.class) {
                    //gris
                }
            } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() == 1) {
                //verde
            } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() == 2) {
                //amarillo
            } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() == 3) {
                //rojo
            }
        }
    }

    public void movimiento() {
        ListaEnlazed<Individuo> listamovidos = new ListaEnlazed<>();
        boolean porquesi = true;
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    boolean pertenece = false;
                    if (!listamovidos.isVacia()) {
                        for (int q = listamovidos.getNumeroElementos() - 1; q > -1; q--) {
                            if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData() == listamovidos.getElemento(q).getData()) {
                                pertenece = true;
                            }
                        }
                    }
                    System.out.println(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos());
                    if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 1 && !pertenece) {
                        System.out.println("moviendome");
                        Random random = new Random();
                        int dir = random.nextInt(1, 8);
                        if (dir == 1) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            if (newY >= model.tableroFilasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }
                        } else if (dir == 2) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                            if (newY >= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }


                        } else if (dir == 3) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                            if (newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }


                        } else if (dir == 4) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                            if (newY <= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }

                        } else if (dir == 5) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX();
                            if (newY <= model.tableroFilasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }

                        } else if (dir == 6) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                            if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }


                        } else if (dir == 7) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY();
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                            if (newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }


                        } else if (dir == 8) {
                            int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                            int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                            if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                    if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                        tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                    }
                                }
                                tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                            }


                        }
                    } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 2 && !pertenece) {

                        Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                        if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                            Celdas ruta = tablero.getCeldas().getElemento(0).getData();
                            Random random = new Random();
                            while (ruta.getRecursosListaEnlazed().isVacia() && porquesi) {
                                int x = random.nextInt(model.tableroColumnasProperty().getValue().intValue() * model.tableroFilasProperty().getValue().intValue());
                                for (int u = x; u < tablero.getCeldas().getNumeroElementos() && ruta == tablero.getCeldas().getElemento(0).getData(); u++) {
                                    if (!tablero.getCeldas().getElemento(u).getData().getRecursosListaEnlazed().isVacia()) {
                                        ruta = tablero.getCeldas().getElemento(u).getData();
                                    }
                                }
                                if (ruta.getRecursosListaEnlazed().isVacia()) {
                                    for (int v = 0; v < x && ruta == tablero.getCeldas().getElemento(0).getData(); v++) {
                                        if (!tablero.getCeldas().getElemento(v).getData().getRecursosListaEnlazed().isVacia()) {
                                            ruta = tablero.getCeldas().getElemento(v).getData();
                                        }
                                    }
                                }
                                if (ruta.getRecursosListaEnlazed().isVacia()) {
                                    porquesi = false;
                                }
                            }
                            if (ruta != null) {
                                individuo.setRuta(ruta);
                            }
                        }
                        if (individuo.getRuta() != null) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY()) {
                                if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY()) {
                                    int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                    int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                            listamovidos.add(individuo);
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
                                            listamovidos.add(individuo);
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
                                            listamovidos.add(individuo);
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
                                            listamovidos.add(individuo);
                                        }

                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);

                                }
                            }
                        } else {
                            Random random = new Random();
                            int dir = random.nextInt(1, 8);
                            if (dir == 1) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                if (newY >= model.tableroFilasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }
                            } else if (dir == 2) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newY >= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 3) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 4) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }

                            } else if (dir == 5) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                if (newY <= model.tableroFilasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }

                            } else if (dir == 6) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 7) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 8) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }
                            }


                        }


                    } else if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData().getTipo() == 3 && !pertenece) {
                        Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                        if (individuo.getRuta() == null || individuo.getRuta().getRecursosListaEnlazed() == null) {
                            Celdas ruta = tablero.getCeldas().getElemento(0).getData();
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
                            if (!ruta.getRecursosListaEnlazed().isVacia()) {
                                individuo.setRuta(ruta);
                            }

                        }
                        if (individuo.getRuta() != null) {
                            if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() || tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                                if (tablero.getCeldas().getElemento(j).getData().getY() != individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() != individuo.getRuta().getX()) {
                                    if (tablero.getCeldas().getElemento(j).getData().getY() < individuo.getRuta().getY() && tablero.getCeldas().getElemento(j).getData().getX() < individuo.getRuta().getX()) {
                                        int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                        int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                        for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                            if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                                tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(individuo);
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
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
                                                listamovidos.add(individuo);
                                            }

                                        }
                                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                    }
                                }
                            }
                        } else {
                            Random random = new Random();
                            int dir = random.nextInt(1, 8);
                            if (dir == 1) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                if (newY >= model.tableroFilasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }
                            } else if (dir == 2) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() - 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newY >= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 3) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 4) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() + 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX <= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }

                            } else if (dir == 5) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX();
                                if (newY <= model.tableroFilasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }

                            } else if (dir == 6) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 7) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY();
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                        }
                                    }
                                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                                }


                            } else if (dir == 8) {
                                int newY = tablero.getCeldas().getElemento(j).getData().getY() + 1;
                                int newX = tablero.getCeldas().getElemento(j).getData().getX() - 1;
                                if (newY <= model.tableroFilasProperty().getValue().intValue() || newX >= model.tableroColumnasProperty().getValue().intValue()) {
                                    for (int x = 0; x < tablero.getCeldas().getNumeroElementos(); x++) {
                                        if (tablero.getCeldas().getElemento(x).getData().getY() == newY && tablero.getCeldas().getElemento(x).getData().getX() == newX) {
                                            tablero.getCeldas().getElemento(x).getData().getIndividuoListaEnlazed().add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
                                            listamovidos.add(tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i));
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
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() > 3) {
                for (int k = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; k > -1 && tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() > 3; k--) {
                    tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(k);
                }
            }
        }
    }


    public void crearrecursos() {
        Random random = new Random();
        Agua agua = new Agua(model);
        Biblioteca biblio = new Biblioteca(model);
        Comida comida = new Comida(model);
        Montaña montaña = new Montaña(model);
        Pozo pozo = new Pozo(model);
        Tesoro tesoro = new Tesoro(model);
        for (int i = 0; i < tablero.getCeldas().getNumeroElementos(); i++) {
            int x = random.nextInt(101);
            if (x < model.porpApariconProperty().getValue().intValue()) {
                if (x < agua.getProbabilidadagua() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(agua);
                    System.out.println("aguita");
                }
                if (x < biblio.getProbabilidadbiblio() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(biblio);
                    System.out.println("biblio");
                }
                if (x < comida.getProbabilidadcomida() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(comida);
                    System.out.println("comida");
                }
                if (x < montaña.getProbabilidadmontaña() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(montaña);
                    System.out.println("montaña");
                }
                if (x < pozo.getProbabilidadpozo() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(pozo);
                    System.out.println("pozo");
                }
                if (x < tesoro.getProbabilidadtesoro() && tablero.getcelditas(i).getRecursosListaEnlazed().getNumeroElementos() < 3) {
                    tablero.getcelditas(i).getRecursosListaEnlazed().add(tesoro);
                    System.out.println("tesoro");
                }
            }
        }
    }

    public void mejora() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().isVacia() && !tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    // System.out.println("Antes if");
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(0).getData();
                    Recursos recurso = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(i).getData();

                    if (recurso instanceof Agua || recurso instanceof Pozo || recurso instanceof Montaña ||
                            recurso instanceof Biblioteca || recurso instanceof Comida || recurso instanceof Tesoro) {
                        recurso.Propiedad(individuo);
                        tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().del(i);
                    }
                }
            }

        }
    }

    public ListaEnlazed<Individuo> longevidad(ListaEnlazed<Individuo> listalongeva) {
        int muerte = listalongeva.getElemento(0).getData().getMuerte();
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                    individuo.setMuerte(individuo.getMuerte() + 1);
                    if (individuo.getMuerte() > muerte) {
                        muerte = individuo.getMuerte();
                        listalongeva.add(individuo);
                    }
                    if (listalongeva.getNumeroElementos() > 1) {
                        listalongeva.del(0);
                    }
                }
            }
        }
        return listalongeva;
    }


    public void individuoactualizado() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                    individuo.setTurnosVida(individuo.getTurnosVida() - 1);
                    individuo.setClonacion(individuo.getClonacion() - 10);
                    individuo.setReproducion(individuo.getReproducion() - 10);
                    if (individuo.getTurnosVida() <= 0) {
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(i);
                    }
                }
            }
        }
        ListaEnlazed<Individuo> listaindividuos = new ListaEnlazed<>();
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                    listaindividuos.add(individuo);
                }
            }
        }
        if (listaindividuos.getNumeroElementos() < 2) {
            setJuego(false);
        }
    }


    public void recursoactivo() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (!tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().isVacia()) {

                for (int i = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    Recursos recurso = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(i).getData();
                    recurso.setTiempo(recurso.getTiempo() - 1);
                    if (recurso.getTiempo() == 0) {
                        tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().del(i);
                    }
                }
            }
        }
    }

    public void eliminarrecurso() {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            for (int i = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getNumeroElementos(); i > 0; i--) {
                Recursos recurso = tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().getElemento(i).getData();
                if (recurso.getTiempo() == 0) {
                    tablero.getCeldas().getElemento(j).getData().getRecursosListaEnlazed().del(i);
                }
            }
        }
    }


    /*private void eliminarrecurso(ListaEnlazed<Recursos> list) {
        ListaEnlazed<Recursos> todos = new ListaEnlazed<>();
        for (int i = 0; i < list.getNumeroElementos(); i++) {
            for (int j = 0; j < todos.getNumeroElementos(); j++) {
                if (list.getDatos(i) == todos.getDatos(j)) {
                    todos.del(j);
                }
            }
        }
    }*/

    public void clonacion() throws IDexistente {
        for (int j = 0; j < tablero.getCeldas().getNumeroElementos(); j++) {
            if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() >= 1) {
                for (int i = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; i > -1; i--) {
                    Random random = new Random();
                    int x = random.nextInt(101);
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
                    Individuo individuonuevo=null;
                    if (individuo.getClonacion() > x) {
                        if (individuo.getTipo() == 1) {
                            Generacion generacion=new Generacion();
                            generacion.setPadre(individuo);
                            individuonuevo = new IndividuoBasico(model,generacion);
                        } else if (individuo.getTipo() == 2) {
                            Generacion generacion=new Generacion();
                            generacion.setPadre(individuo);
                            individuonuevo = new IndividuoNormal(model,generacion);
                        } else {
                            Generacion generacion=new Generacion();
                            generacion.setPadre(individuo);
                            individuonuevo = new IndividuoAvanzado(model,generacion);
                        }
                        generarID(individuonuevo);
                    }
                    if (individuonuevo != null) {
                        individuonuevo.getGeneracion().setPadre(individuo);
                        tablero.getcelditas(j).getIndividuoListaEnlazed().add(individuonuevo);
                    }
                }
                if (tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() > 3) {
                    for (int k = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() - 1; k > -1 && tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getNumeroElementos() > 3; k--) {
                        tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().del(k);
                    }
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
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
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
                    Individuo individuo = tablero.getCeldas().getElemento(j).getData().getIndividuoListaEnlazed().getElemento(i).getData();
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

