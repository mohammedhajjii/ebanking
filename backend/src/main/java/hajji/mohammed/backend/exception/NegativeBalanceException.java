package hajji.mohammed.backend.exception;

public class NegativeBalanceException extends IllegalArgumentException{

    public NegativeBalanceException() {
        super("negative balance not authorized");
    }
}
