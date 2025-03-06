package cinema.exceptions;

public class ReturnTicketErrorException extends RuntimeException {
    public ReturnTicketErrorException(String message) {
        super(message);
    }
}