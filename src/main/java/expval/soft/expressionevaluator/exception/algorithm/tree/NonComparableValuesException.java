package expval.soft.expressionevaluator.exception.algorithm.tree;

import expval.soft.expressionevaluator.exception.MultipleArgumentsException;

public class NonComparableValuesException extends MultipleArgumentsException {
  private static final int NUM_OF_ARGUMENTS = 2;

  public NonComparableValuesException(String message) {
    super(message);
  }

  public NonComparableValuesException(String message, String... args) {
    super(String.format(message, args[0], args[1]), NUM_OF_ARGUMENTS, args);
  }
}
