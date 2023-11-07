package leapwise.soft.expressionevaluator.exception;

import java.util.List;

public class OperatorMismatchException extends MultipleArgumentsException{
    private static final int NUM_OF_ARGUMENTS = 2;

    public OperatorMismatchException(String message, String... args) {
        super(String.format(message, args[0], args[1]), NUM_OF_ARGUMENTS, args);
    }

    public List getArguments() {
        return super.getArguments();
    }
}
