package cinema.exceptions;

public class PurchaseTicketErrorException extends RuntimeException {
    public PurchaseTicketErrorException(String message) {
        super(message);
    }
}