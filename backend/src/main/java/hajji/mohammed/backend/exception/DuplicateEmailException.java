package hajji.mohammed.backend.exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException() {
        super("email is already in use");
    }
}
