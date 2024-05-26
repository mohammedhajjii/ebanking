package hajji.mohammed.backend.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long customerId) {
        super("customer not found for id: " + customerId);
    }
}
