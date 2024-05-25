package Estructuras;

import com.google.gson.annotations.Expose;

public class ElementoLe<T> {
    @Expose
    public T data;
    @Expose
    public ElementoLe<T> siguiente;


    public ElementoLe(T data) {
        this.data = data;
        this.siguiente = null;
    }
    public ElementoLe(){
        this.data=null;
        this.siguiente=null;
    }

    protected void insertarmeEn(ElementoLe<T> el) {
        el.siguiente=this.siguiente;
        this.siguiente=el;
    }

    public ElementoLe<T> getSiguiente() {
        return siguiente;
    }
    public T getsigui(){
        return siguiente.getData();
    }
    public void setSiguiente(ElementoLe<T> siguiente) {
        this.siguiente = siguiente;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
