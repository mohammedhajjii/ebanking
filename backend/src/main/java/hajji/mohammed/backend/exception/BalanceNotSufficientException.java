package hajji.mohammed.backend.exception;

public class BalanceNotSufficientException extends RuntimeException{

    public BalanceNotSufficientException() {
        super("balance not sufficient for operation");
    }
}
