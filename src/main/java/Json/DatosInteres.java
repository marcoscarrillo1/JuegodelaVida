package Json;

import Estructuras.ListaEnlazed;
import Tablero.Celdas;
import com.google.gson.annotations.Expose;

public class DatosInteres {
    @Expose
    public int turnosVida;
    @Expose
    public int mutacion;
    @Expose
    public int reproducion;
    @Expose

    public int tableroFilas;
    @Expose
    public int tableroColumnas;
    @Expose
    public int tiempoApracion;
@Expose
    public int probAparicion;
@Expose
    public int propAgua;
@Expose
    public int propBiblioteca;
@Expose
    public int propMontana;
@Expose
    public int propComida;
@Expose
    public int propPozo;
@Expose
    public int propTesoro;
@Expose
public ListaEnlazed<Celdas> celda;

    public DatosInteres(int turnosVida, int mutacion, int reproducion, int tableroFilas, int tableroColumnas, int tiempoApracion, int probAparicion, int propAgua, int propBiblioteca, int propMontana, int propComida, int propPozo, int propTesoro, ListaEnlazed<Celdas> celda) {
        this.turnosVida = turnosVida;
        this.mutacion = mutacion;
        this.reproducion = reproducion;
        this.tableroFilas = tableroFilas;
        this.tableroColumnas = tableroColumnas;
        this.tiempoApracion = tiempoApracion;
        this.probAparicion = probAparicion;
        this.propAgua = propAgua;
        this.propBiblioteca = propBiblioteca;
        this.propMontana = propMontana;
        this.propComida = propComida;
        this.propPozo = propPozo;
        this.propTesoro = propTesoro;
        this.celda = celda;
    }

    public int getTurnosVida() {
        return turnosVida;
    }

    public int getMutacion() {
        return mutacion;
    }

    public int getReproducion() {
        return reproducion;
    }

    public int getTableroFilas() {
        return tableroFilas;
    }

    public int getTableroColumnas() {
        return tableroColumnas;
    }

    public int getTiempoApracion() {
        return tiempoApracion;
    }

    public int getProbAparicion() {
        return probAparicion;
    }

    public int getPropAgua() {
        return propAgua;
    }

    public int getPropBiblioteca() {
        return propBiblioteca;
    }

    public int getPropMontana() {
        return propMontana;
    }

    public int getPropComida() {
        return propComida;
    }

    public int getPropPozo() {
        return propPozo;
    }

    public int getPropTesoro() {
        return propTesoro;
    }

    public ListaEnlazed<Celdas> getCelda() {
        return celda;
    }
}
