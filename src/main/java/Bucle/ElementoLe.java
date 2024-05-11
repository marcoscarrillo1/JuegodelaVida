package Bucle;

public class ElementoLe<T> {
    protected ElementoLe<T> siguiente;
    private T data;

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

    protected void setSiguiente(ElementoLe<T> siguiente) {
        this.siguiente = siguiente;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
