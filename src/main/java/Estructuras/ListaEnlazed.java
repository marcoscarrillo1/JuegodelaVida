package Estructuras;


import Estructuras.ElementoLe;

public class ListaEnlazed<T> {
    public ElementoLe<T> primero;

    public boolean isVacia() {
        return primero == null;

    }

    public void vaciar() {
        primero = null;


    }

    public int add(ElementoLe<T> el) {
        ElementoLe<T> e=primero;
        if (isVacia()) {
            e = new ElementoLe<T>();
            e.setData(el.getData());
            this.primero = e;
        } else {;
            el.setSiguiente(this.primero);
            this.primero = el;
        }
        return getPosicion(el);
    }

    public int add(String mipalabra) {
        ElementoLe j = new ElementoLe();
        j.setData(mipalabra);
        return this.add(j);
    }

    public int add(T objeto) {
        ElementoLe<T> e = new ElementoLe<T>();
        e.setData(objeto);
        return this.add(e);
    }


    public void insert(String s, int pos) {
        ElementoLe e = new ElementoLe();
        e.setData(s);
        if (pos!=0 && pos<getNumeroElementos()){
            e.insertarmeEn(getElemento(pos - 1));
        }else if (pos==0){
            this.primero=e;
        }
    }

    public void insert(ElementoLe<T> o, int pos) {
        ElementoLe<T> e = new ElementoLe<T>();
        e.setData(o.getData());
        if (pos!=0) {
            e.insertarmeEn(getElemento(pos - 1));
        }else if (pos==0){
            this.primero=e;
        }

    }
    public int getNumeroElementos() {
        int contador = 0;
        ElementoLe<T> e = this.primero;
        if (isVacia()) {
            return 0;
        } else {
            while (e != null) {
                contador++;
                e = e.getSiguiente();
            }
            return contador;
        }

    }

    public ElementoLe<T> getPrimero() {
        return this.primero;
    }

    public int getPosicion(ElementoLe<T> el) {
        int pos = 0;
        ElementoLe<T> e = this.primero;
        while (e!=el&&pos<getNumeroElementos()) {
            e = e.getSiguiente();
            pos++;
        }
        return pos;
    }
    public ElementoLe<T> getUltimo() {
        int x = getNumeroElementos();
        ElementoLe<T> r = new ElementoLe<T>();
        r.setData(getElemento(x-1).getData());
        return r;
    }

    public ElementoLe<T> getSiguiente(ElementoLe<T> el) {
        ElementoLe<T> e = el;
        e.setSiguiente(e.getSiguiente().getSiguiente());
        return e;
    }
    public ElementoLe<T> getElemento( int pos){
        if (pos<0||pos>getNumeroElementos()) {
            return null;
        }else {
            ElementoLe<T> Elemento = this.primero;
            int contador=0;
            while (contador<pos && Elemento!=null) {
                Elemento = Elemento.getSiguiente();
                contador++;
            }
            return Elemento;
        }

    }
    public ElementoLe<T> getDatos(int pos){
        if (pos<0||pos>getNumeroElementos()) {
            return null;
        }else {
            ElementoLe<T> Elemento = this.primero;
            int contador=0;
            while (contador<pos && Elemento!=null) {
                Elemento = Elemento.getSiguiente();
                contador++;
            }
            return Elemento;
        }
    }
    public T[] toArray(T[] array) {
        ElementoLe<T> actual = primero;
        int index = 0;
        while (actual != null) {
            array[index++] = actual.getData();
            actual = actual.getSiguiente();
        }
        return array;
    }
    public void del(int pos){
    if(isVacia()){

        return;
    }
    ElementoLe temp = primero;
    if(pos == 0){
        primero= temp.siguiente;
        return;
    }
    for (int i = 0; temp != null && i < pos-1 ;i++){
        temp= temp.siguiente;
    }
    if(temp == null || temp.getSiguiente() == null){
        return;
    }
    ElementoLe sig = temp.siguiente.siguiente;
    temp.siguiente = sig;



    }
}
