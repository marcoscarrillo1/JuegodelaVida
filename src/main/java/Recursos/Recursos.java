package Recursos;

import Individuo.Individuo;

public  abstract class  Recursos {
    protected int cordenadax;
    protected int cordenaday;
    protected int Tiempo;
    protected int Probabilidad;

    public Recursos(int x, int y, int t,int p){
        x=cordenadax;
        y=cordenaday;
        t=Tiempo;
        p=Probabilidad;

    }
    public Recursos(){

    }

    public int getCordenadax() {
        return cordenadax;
    }

    public int getCordenaday() {
        return cordenaday;
    }



    public void setCordenadax(int cordenadax) {
        this.cordenadax = cordenadax;
    }

    public void setCordenaday(int cordenaday) {
        this.cordenaday = cordenaday;
    }


    public int getTiempo() {
        return Tiempo;
    }

    public int getProbabilidad() {
        return Probabilidad;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    public void setProbabilidad(int probabilidad) {
        Probabilidad = probabilidad;
    }
    public abstract void Propiedad(Individuo x);



}
