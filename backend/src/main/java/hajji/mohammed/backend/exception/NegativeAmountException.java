package hajji.mohammed.backend.exception;

public class NegativeAmountException extends IllegalArgumentException{

    public NegativeAmountException() {
        super("Negative amount not authorized for operation");
    }
}
