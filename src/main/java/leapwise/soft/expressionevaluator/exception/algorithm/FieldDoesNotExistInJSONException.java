package leapwise.soft.expressionevaluator.exception.algorithm;

public class FieldDoesNotExistInJSONException extends RuntimeException{
    public FieldDoesNotExistInJSONException(String message) {
        super(message);
    }
    public FieldDoesNotExistInJSONException(String message, String... args) {
        super(String.format(message, args[0]));
    }
}
