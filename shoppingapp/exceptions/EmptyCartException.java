
package exceptions;

public class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}