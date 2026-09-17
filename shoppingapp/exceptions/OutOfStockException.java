package exceptions;

public class OutOfStockException extends ProductException {
    public OutOfStockException(String message) { super(message); }
}