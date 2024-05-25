package Estructuras;

import java.util.List;

public class Cola<T> {
    public T data;
    private ListaEnlazed<T> datos;
    public Cola(){
        datos=new ListaEnlazed<>();

    }
    public ListaEnlazed<T> getDatos(){
        return datos;
    }
    public void push(T elemento){
        datos.add(elemento);
    }
    public T pull(){
        T borrado= datos.getElemento(0).getData();
        datos.del(0);
        return borrado;
    }
    public boolean isVacia(){
        return datos.isVacia();
    }

}
