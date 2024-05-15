package Estructuras;

public class ElementoLS<T> {

        private T dato;


        public ElementoLS() {
            dato = null;

        }

        public T getData() {
            return dato;
        }

        public void setData(T dato) {
            this.dato = dato;
        }
    }

