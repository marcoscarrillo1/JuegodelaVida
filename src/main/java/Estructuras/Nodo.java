package Estructuras;

public class Nodo<Tipodato> {
    private Tipodato data;
    private Nodo<Tipodato> Nododerecha;
    private Nodo<Tipodato> Nodoizquierda;

    public Nodo() {
        this.data = null;
    }

    public Nodo(Tipodato data) {
        this.data = data;
    }

    public Tipodato getData() {
        return data;
    }

    public void setData(Tipodato data) {
        this.data = data;
    }

    public Nodo<Tipodato> getNododerecha() {
        return Nododerecha;
    }

    public Nodo<Tipodato> getNodoizquierda() {
        return Nodoizquierda;
    }

    public void setNododerecha(Nodo<Tipodato> nododerecha) {
        Nododerecha = nododerecha;
    }

    public void setNodoizquierda(Nodo<Tipodato> nodoizquierda) {
        Nodoizquierda = nodoizquierda;
    }

    public int GetAltura(Nodo<Tipodato> t) {
        if (t == null) {
            return 0;
        } else {
            int alturadrcha = GetAltura(t.getNododerecha());
            int alturaizq = GetAltura(t.getNodoizquierda());
            return Math.max(alturadrcha, alturaizq) + 1;
        }


    }

    public void add(Tipodato x) {
        Comparable anterior = (Comparable) this.data;
        Comparable nuevo = (Comparable) x;
        if (anterior.compareTo(nuevo) > 0) {
            if (Nodoizquierda == null) {
                Nodo<Tipodato> y = new Nodo<>(x);
                this.Nodoizquierda = y;
            } else {
                Nodoizquierda.add(x);
            }
        } else {
            if (Nododerecha == null) {
                Nodo<Tipodato> y = new Nodo<>(x);
                this.Nododerecha = y;
            } else {
                Nododerecha.add(x);
            }

        }

    }


}