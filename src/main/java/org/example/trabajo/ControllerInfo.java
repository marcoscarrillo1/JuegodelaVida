package org.example.trabajo;

import Estructuras.ListaEnlazed;
import Individuo.Individuo;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ControllerInfo {
    private static Logger log = LogManager.getLogger(ControllerInfo.class);
    public Label Individuo1;
    public Label id1;
    public Label generacion1;
    public Label turnosvida1;
    public Label reproduccion1;
    public Label clonacion1;
    public Label Individuo2;
    public Label id2;
    public Label generacion2;
    public Label turnosvida2;
    public Label reproduccion2;
    public Label muerte1;
    public Label clonacion2;
    public Label Individuo3;
    public Label id3;
    public Label generacion3;
    public Label turnosvida3;
    public Label reproduccion3;
    public Label clonacion3;
    public Label muerte3;
    public Label muerte2;
    private ListaEnlazed<Individuo> info;

    public ControllerInfo() {
    }

    public ListaEnlazed<Individuo> getInfo() {
        return info;
    }

    public void setInfo(ListaEnlazed<Individuo> info) {
        this.info = info;
    }

    public void setText() {
        if(info != null){
            ListaEnlazed<Label> indi = new ListaEnlazed<>();
        ListaEnlazed<Label> muerte = new ListaEnlazed<>();
        ListaEnlazed<Label> clona = new ListaEnlazed<>();
        ListaEnlazed<Label>  id = new ListaEnlazed<>();
        ListaEnlazed<Label> repro = new ListaEnlazed<>();
        ListaEnlazed<Label> vida = new ListaEnlazed<>();
        ListaEnlazed<Label> generacion = new ListaEnlazed<>();
        indi.add(Individuo3);indi.add(Individuo2);indi.add(Individuo1);
        muerte.add(muerte3);muerte.add(muerte2);muerte.add(muerte1);
        clona.add(clonacion3);clona.add(clonacion2);clona.add(clonacion1);
        id.add(id3);id.add(id2);id.add(id1);
        repro.add(reproduccion3);repro.add(reproduccion2);repro.add(reproduccion1);
        vida.add(turnosvida3);vida.add(turnosvida2);vida.add(turnosvida1);
        generacion.add(generacion3);generacion.add(generacion2);generacion.add(generacion1);
        for (int i = 0; i< 3;i++){
            if(i<info.getNumeroElementos()) {
                indi.getElemento(i).getData().setText("Individuo: " + (i + 1));
                muerte.getElemento(i).getData().setText("Muerte: " + info.getElemento(i).getData().getMuerte());
                clona.getElemento(i).getData().setText("Clonacion: " + info.getElemento(i).getData().getClonacion());
                id.getElemento(i).getData().setText("Id: " + info.getElemento(i).getData().getIdentificador());
                repro.getElemento(i).getData().setText("Reproducion: " + info.getElemento(i).getData().getReproducion());
                vida.getElemento(i).getData().setText("Clona: " + info.getElemento(i).getData().getTurnosVida());
                generacion.getElemento(i).getData().setText("Generacion: " + info.getElemento(i).getData().getGeneracion());
            }else {
                indi.getElemento(i).getData().setText("");
                muerte.getElemento(i).getData().setText("");
                clona.getElemento(i).getData().setText("");
                id.getElemento(i).getData().setText("");
                repro.getElemento(i).getData().setText("");
                vida.getElemento(i).getData().setText("");
                generacion.getElemento(i).getData().setText("");

            }


        }}
    }
}
