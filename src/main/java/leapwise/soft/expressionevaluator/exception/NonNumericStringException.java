package leapwise.soft.expressionevaluator.exception;

public class NonNumericStringException extends MultipleArgumentsException {

    private static final int NUM_OF_ARGUMENTS = 1;

    public NonNumericStringException(String message) {
        super(message);
    }

    public NonNumericStringException(String message, String... args) {
        super(String.format(message, args[0]), NUM_OF_ARGUMENTS, args);
    }
}
