package Estructuras;

public class ListaSimple<T> {

    /**
     * Programar la lista simplemente enlazada.
     */
        private ElementoLS<T>[] datos;
        private ElementoLS<T> primero;
        private int maximo;

        public ListaSimple(int n ){
            this.maximo=n;
            this.datos= new ElementoLS[n];
            if (n>0){
                primero = datos[0];
            }else {
            primero=null;}
        }
    public ListaSimple(){
        this.maximo=0;
        this.datos=null;
        primero=null;
    }
        public Boolean isVacia() {
            return datos[0] == null;
        }

        public void vaciar() {
            for (int i = 0; this.datos[i] != null; i++) {
                datos[i] = null;
            }
        }
        public int add(ElementoLS elemento){
            int i=0;
            while(this.datos[i]!=null){
                i++;
            }
            if(i<=maximo){
                this.datos[i]=elemento;}
            return i ;
        }

        public int add (String mipalabra){
            ElementoLS e = new ElementoLS();
            e.setData(mipalabra);
            return this.add(e);
        }
        public int add(Object objeto){
            ElementoLS o = new ElementoLS();
            o.setData(objeto);
            return this.add(o);
        }
        public void insert(int pos,String x){
            ElementoLS o = new ElementoLS();
            o.setData(x);
            datos[pos]= o ;
        }
        public void insert(int pos ,Object obj){
            ElementoLS o = new ElementoLS();
            o.setData(obj);
            datos[pos]= o ;
        }
        public int del(int pos){
            this.datos[pos] = null;
            return pos;
        }
        public int getNumeroElementos(){
            int c=0;
            for (int i = 0; this.datos[i] != null; i++) {
                if (this.datos[i]!=null){
                    c++;
                }
            }
            return c ;
        }

        public T getPrimero() {
            primero=this.datos[0];
            return primero.getData();
        }
        public Integer getPosicion(ElementoLS el ){
            int posicion=0;
            for( int i=0;i<maximo;i++){
                if(datos[i]==el){
                    return i;
                }
            }
            return posicion;
        }
        public ElementoLS getUltimo(){
            int contador =0;
            while (datos[contador]!=null){
                contador++;
            }
            return datos[contador-1];
        }
        public ElementoLS getELemento(int pos){
            return datos[pos];
        }
        public T getDatos(int pos){
            return datos[pos].getData();
        }

        public ElementoLS getSiguiente(ElementoLS el){
            getPosicion(el);
            int i= 0;
            while ( el!=datos[i] && i<maximo){
                i++;
            }
            return datos[i+1];
        }
    }

