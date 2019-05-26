package exception;

public class OverbookingException extends Exception {
    public OverbookingException(){
        super("Nastąpił overbooking");
    }
}
