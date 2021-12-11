package by.epam.jwdcomposite.exception;

public class UnknownComponentException extends IllegalArgumentException {
    public UnknownComponentException() {
    }

    public UnknownComponentException(String s) {
        super(s);
    }

    public UnknownComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownComponentException(Throwable cause) {
        super(cause);
    }
}
