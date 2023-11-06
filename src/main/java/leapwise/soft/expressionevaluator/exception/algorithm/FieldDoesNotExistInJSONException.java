package leapwise.soft.expressionevaluator.exception.algorithm;

import leapwise.soft.expressionevaluator.exception.MultipleArgumentsException;

import java.util.List;

public class FieldDoesNotExistInJSONException extends MultipleArgumentsException {

  private static final int NUM_OF_ARGUMENTS = 1;

  public FieldDoesNotExistInJSONException(String message, String... args) {
    super(String.format(message, args[0]), NUM_OF_ARGUMENTS, args);
  }

  public List getArguments() {
    return super.getArguments();
  }
}
