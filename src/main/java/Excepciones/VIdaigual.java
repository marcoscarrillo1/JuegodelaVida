package Excepciones;

public class VIdaigual extends Exception{
    public VIdaigual(String message){
        super(message);
        System.out.println("Todos tienen la misma vida");
    }

}
