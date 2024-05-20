package Excepciones;

public class IDexistente extends Exception{
    public IDexistente(String message){
        super(message);
        System.out.println("El id generado ya existe");
    }
}
