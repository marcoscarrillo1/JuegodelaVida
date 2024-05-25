package Estructuras;

import Estructuras.ListaEnlazed;

public class ArbolBinarioAVL<TipoDato> {
    public Nodo<TipoDato> raiz;

    public ArbolBinarioAVL() {
        this.raiz = null;
    }

    public ArbolBinarioAVL(TipoDato raiz) {
        this.raiz = new Nodo<>(raiz);
    }

    public Nodo<TipoDato> getRaiz() {
        return raiz;
    }

    /* public void agregarNodo(int d){
         Avl.Nodo nuevo=new Avl.Nodo(d);
         if(raiz==null){
             raiz=nuevo;
         }else{
             Avl.Nodo auxiliar=raiz;
             Avl.Nodo padre;
             while(true){
                 padre=auxiliar;
                 if(d<auxiliar.getData()){
                     auxiliar=auxiliar.getNodoizquierda();
                     if(auxiliar==null){
                         padre.getNodoizquierda().setData()=nuevo;
                     }
                 }
             }
         }


     }*/
    public int comparar(TipoDato x, TipoDato y) {
        Comparable c = (Comparable) x;
        Comparable d = (Comparable) y;
        return c.compareTo(d);
    }

    public ArbolBinarioAVL<TipoDato> getSubArbolIzquierdo() {

        if (raiz != null && raiz.getNodoizquierda() != null) {
            return new ArbolBinarioAVL<>(this.raiz.getNodoizquierda().getData());
        } else {
            return null;
        }

    }

    public ArbolBinarioAVL<TipoDato> getSubArbolDerecho() {
        if (raiz != null && raiz.getNododerecha() != null) {
            return new ArbolBinarioAVL<>(this.raiz.getNododerecha().getData());
        } else {
            return null;
        }
    }

    public void add(TipoDato e) {
        if (raiz == null) {
            Nodo<TipoDato> x = new Nodo<>(e);
            raiz = x;
        } else {
            raiz.add(e);
        }
    }
    public void add(Nodo<TipoDato> nodo){
        if(raiz==null){
            raiz=nodo;
        }else{
            raiz.add(nodo.getData());
        }
    }

    public int GetGrado(Nodo<TipoDato> t) {
        if (t == null) {
            return 0;
        } else {
            int gradoIzq = GetGrado(t.getNodoizquierda());
            int gradodrcha = GetGrado(t.getNododerecha());
            if (gradodrcha >= gradoIzq) {
                return gradodrcha + 1;
            } else {
                return gradoIzq + 1;
            }
        }
    }

    public int getGrado() {
        return GetGrado(raiz);
    }


    public int getAltura() {
        if(raiz==null){
            return 0;
        }else{
            return raiz.GetAltura(raiz);
        }}

    public ListaEnlazed<TipoDato> camino(Nodo<TipoDato> x, TipoDato y, ListaEnlazed<TipoDato> list) {
        list.add(y);
        if ((x.getNododerecha() != null) && (comparar(y, x.getData()) == -1)) {
            camino(x.getNodoizquierda(), y, list);
        } else if ((x.getNododerecha() != null) && (comparar(y, x.getData()) == 1)) {
            camino(x.getNododerecha(), y, list);
        } else if (comparar(y, x.getData()) == 0) {
            return list;
        }
        return list;
    }



    public ListaEnlazed<TipoDato> getListaPreorden() {
        ListaEnlazed<TipoDato> l = new ListaEnlazed<>();
        return listapreorden(raiz, l);

    }

    public ListaEnlazed<TipoDato> getListaPostorden() {
        ListaEnlazed<TipoDato> l = new ListaEnlazed<>();
        return listapostorden(raiz, l);

    }

    public ListaEnlazed<TipoDato> getListaordencentral() {
        ListaEnlazed<TipoDato> l = new ListaEnlazed<>();
        return listapordencentral(raiz, l);

    }

    public ListaEnlazed<TipoDato> listapordencentral(Nodo<TipoDato> nodo, ListaEnlazed<TipoDato> list) {
        if (nodo != null) {
            listapordencentral(nodo.getNodoizquierda(), list);
            list.add(nodo.getData());
            listapordencentral(nodo.getNododerecha(), list);
        }
        return list;
    }

    public ListaEnlazed<TipoDato> listapostorden(Nodo<TipoDato> nodo, ListaEnlazed<TipoDato> list) {
        if (nodo != null) {
            listapostorden(nodo.getNodoizquierda(), list);
            listapostorden(nodo.getNododerecha(), list);
            list.add(nodo.getData());
        }
        return list;
    }

    public ListaEnlazed<TipoDato> listapreorden(Nodo<TipoDato> nodo, ListaEnlazed<TipoDato> list) {
        if (nodo != null) {
            list.add(nodo.getData());
            listapreorden(nodo.getNodoizquierda(), list);
            listapreorden(nodo.getNododerecha(), list);
        }
        return list;
    }

    public void datosnivel(Nodo<TipoDato> x, int nivel, ListaEnlazed<TipoDato> array, int nivel2) {
        if (x == null) {
            return;
        } else if (nivel == nivel2) {
            array.add(x.getData());
        } else {
            datosnivel(x.getNododerecha(), nivel, array, nivel2 + 1);
            datosnivel(x.getNodoizquierda(), nivel, array, nivel2 + 1);
        }
    }

    public ListaEnlazed<TipoDato> getDatosNivel(int e) {
        ListaEnlazed<TipoDato> array = new ListaEnlazed<>();
        datosnivel(raiz, e, array, 1);
        return array;
    }

    public boolean Completo(Nodo<TipoDato> x) {
        if (x.getNododerecha() == null && x.getNodoizquierda() == null) {
            Completo(x.getNodoizquierda());
            Completo(x.getNododerecha());
        } else {
            return false;
        }
        return true;

    }

    public boolean isCompleto() {
        if (raiz != null) {
            return Completo(this.raiz);
        } else {
            return false;
        }
    }

    public TipoDato max(ListaEnlazed<TipoDato> List) {
        int contador = 0;
        TipoDato max = List.getElemento(1).getData();
        for (contador = 0; contador == List.getNumeroElementos(); contador++) {
            if (comparar(max, List.getElemento(contador).getData()) == -1) {
                max = List.getElemento(contador).getData();
            }

        }
        return max;
    }

    public TipoDato min(ListaEnlazed<TipoDato> List) {
        int contador = 0;
        TipoDato min = List.getElemento(1).getData();
        for (contador = 0; contador == List.getNumeroElementos(); contador++) {
            if (comparar(min, List.getElemento(contador).getData()) == -1) {
                min = List.getElemento(contador).getData();
            }

        }
        return min;

    }


    public boolean isCasiCompleto() {
        ListaEnlazed<TipoDato> nivel = getDatosNivel(getAltura());
        ListaEnlazed<TipoDato> nivel2 = getDatosNivel(getAltura() - 1);
        TipoDato minarriba = min(nivel2);
        TipoDato maxabajo = max(nivel);
        if (comparar(maxabajo, minarriba) == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean homogeneo(Nodo<TipoDato> x) {

        if (x.getNodoizquierda() == null && x.getNododerecha() == null || x.getNododerecha() != null && x.getNodoizquierda() != null) {
            return homogeneo(x.getNododerecha()) & homogeneo(x.getNodoizquierda());
        } else {
            return false;
        }
    }

    public boolean isHomogeneo() {
        return homogeneo(raiz);
    }

    public ListaEnlazed<TipoDato> getCamino(TipoDato x) {
        ListaEnlazed<TipoDato> camino=new ListaEnlazed<>();
        Nodo<TipoDato> y=raiz;
        while(y!=null&&y.getNododerecha()!=x){
            camino.add(y.getData());
            if(comparar(y.getData(),x)==1){
                y=y.getNodoizquierda();
            }else if(comparar(y.getData(),x)==-1){
                y=y.getNododerecha();
            }
        }if(y!=null){
            camino.add(y.getData());
        }
        return camino;
    }

    public void Eliminar(Nodo<TipoDato> y,ArbolBinarioAVL<TipoDato> arbol) {
        if (y == null) {
            return;
        } else if ((getCamino(y.getData()) == null)) {
            return;
        } else if (y.getNodoizquierda() == null && y.getNododerecha() == null) {
            y = null;
        } else if (y.getNododerecha().getNodoizquierda() != null && y.getNododerecha() == null || (y.getNododerecha().getNodoizquierda() != null && y.getNododerecha() != null)) {
            y.setData(y.getNododerecha().getNodoizquierda().getData());
            y.getNododerecha().getNodoizquierda().setData(null);
        } else if (y.getNododerecha().getNodoizquierda() == null && y.getNododerecha().getNodoizquierda() != null) {
            y.setData(y.getNododerecha().getData());
            y.getNododerecha().setData(y.getNododerecha().getNododerecha().getData());
            y.getNododerecha().getNododerecha().setData(null);
        }
        if (isequilibradorecurssivo(getSubArbolDerecho()) && isequilibradorecurssivo(getSubArbolIzquierdo())) {
            System.out.println("El arbol ha sido equilibrado");
        } else {
            int contador = 0;
            Nodo<TipoDato> x = raiz;
            if (getSubArbolIzquierdo().getAltura() < getSubArbolDerecho().getAltura()) {
                while (contador <= getAltura() - 2) {
                    x = x.getNododerecha();
                }
            } else {

                equilibrar(arbol);
            }
        }
    }

    public void insercion(Nodo<TipoDato> x,ArbolBinarioAVL<TipoDato> arbol) {
        if (x == null) {
            return;
        } else if (getCamino(x.getData()) != null) {
            return;
        }if (isequilibradorecurssivo(getSubArbolDerecho()) && isequilibradorecurssivo(getSubArbolIzquierdo())) {
            System.out.println("El elemento ha sido eliminado y el arbol esta equilibrado");
        } else {
            int contador = 0;
            Nodo<TipoDato> y = raiz;
            if (getSubArbolIzquierdo().getAltura() < getSubArbolDerecho().getAltura()) {
                while (contador <= getAltura() - 2) {
                    y = y.getNododerecha();
                }
            } else {
                while (contador <= getAltura() - 2) {
                    y = y.getNodoizquierda();
                }
            }
            equilibrar(arbol);

        }
    }

    public boolean isequilibradorecurssivo(ArbolBinarioAVL<TipoDato> arbol) {
        if (arbol.getSubArbolDerecho() != null && arbol.getSubArbolDerecho() != null) {
            if (Math.abs(getSubArbolDerecho().getAltura() - getSubArbolIzquierdo().getAltura()) < 2) {
                return true;
            } else {
                return this.getSubArbolIzquierdo().isequilibradorecurssivo(this.getSubArbolIzquierdo()) && this.getSubArbolDerecho().isequilibradorecurssivo(this.getSubArbolDerecho());
            }
        }
        else {
            return false;
        }

    }


    public void RotacionIzqIzq(Nodo<TipoDato> x) {
        Nodo<TipoDato> nodo=new Nodo<>();
        x.setNododerecha(nodo);
        x.getNododerecha().setData(x.getData());
        x.setData(x.getNodoizquierda().getData());
        x.getNodoizquierda().setData(x.getNodoizquierda().getNodoizquierda().getData());
        x.getNodoizquierda().setNodoizquierda(null);
    }

    public void RotacionDerder(Nodo<TipoDato> x) {
        Nodo<TipoDato> nodo=new Nodo<>();
        x.setNodoizquierda(nodo);
        x.getNodoizquierda().setData(x.getData());
        x.setData(x.getNododerecha().getData());
        x.getNododerecha().setData(x.getNododerecha().getNododerecha().getData());
        x.getNododerecha().setNododerecha(null);
    }

    public void RotacionDerIzq(Nodo<TipoDato> x) {
        Nodo<TipoDato> nodo=new Nodo<>();
        x.setNodoizquierda(nodo);
        x.getNodoizquierda().setData(x.getData());
        x.setData(x.getNododerecha().getData());
        x.getNododerecha().setData(x.getNododerecha().getNodoizquierda().getData());
        x.getNododerecha().setNodoizquierda(null);
    }

    public void RotacionIzqder(Nodo<TipoDato> x) {
        Nodo<TipoDato> nodo=new Nodo<>();
        x.setNododerecha(nodo);
        x.getNododerecha().setData(x.getData());
        x.setData(x.getNodoizquierda().getData());
        x.getNodoizquierda().setData(x.getNodoizquierda().getNododerecha().getData());
        x.getNodoizquierda().setNododerecha(null);
    }

    public String Verdesequilibrio(ArbolBinarioAVL<TipoDato> x) {
        ArbolBinarioAVL<TipoDato> arbolizq = x.getSubArbolIzquierdo();
        ArbolBinarioAVL<TipoDato> arbolder = x.getSubArbolDerecho();
        if (arbolizq.getAltura() < arbolder.getAltura()) {
            if(arbolder.getRaiz().getNododerecha()!=null){
                if (arbolder.getRaiz().getNododerecha().getNododerecha() != null) {
                    return "DerDer";
                } else if (arbolder.getRaiz().getNododerecha().getNodoizquierda() != null) {
                    return "DerIzq";
                }
            }else {
                if (arbolder.getRaiz().getNodoizquierda().getNodoizquierda() != null) {
                    return "IzqIzq";
                } else if (arbolder.getRaiz().getNodoizquierda().getNododerecha() != null) {
                    return "IzqDer";
                }
            }
        }

        else {
            if (arbolizq.getRaiz().getNodoizquierda() != null) {
                if (arbolizq.getRaiz().getNodoizquierda().getNodoizquierda() != null) {
                    return "IzqIzq";
                } else if (arbolizq.getRaiz().getNodoizquierda().getNododerecha() != null) {
                    return "IzqDer";
                }
            } else {
                if (arbolizq.getRaiz().getNododerecha().getNodoizquierda() != null) {
                    return "DerIzq";
                } else if (arbolizq.getRaiz().getNododerecha().getNododerecha() != null) {
                    return "DerDer";
                }
            }
        }


        return null;
    }

    public void equilibrar(ArbolBinarioAVL<TipoDato> arbol) {
        Nodo<TipoDato> x=new Nodo<>();
        if(arbol.getSubArbolDerecho().getAltura()<arbol.getSubArbolIzquierdo().getAltura()){
            x=arbol.getRaiz().getNodoizquierda();
        }else{
            x=arbol.getRaiz().getNododerecha();
        }
        if (Verdesequilibrio(arbol) == "DerDer") {
            RotacionDerder(x);
        } else if (Verdesequilibrio(arbol) == "DerIzq") {
            RotacionDerIzq(x);
        } else if (Verdesequilibrio(arbol) == "IzqIzq") {
            RotacionIzqIzq(x);
        } else if (Verdesequilibrio(arbol) == "IzqDer") {
            RotacionIzqder(x);
        }
    }


}