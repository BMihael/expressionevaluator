package leapwise.soft.expressionevaluator.exception.algorithm.tree;

import leapwise.soft.expressionevaluator.exception.MultipleArgumentsException;

public class NonComparableValuesException extends MultipleArgumentsException { // pogledati ovo jos jednom
  private static final int NUM_OF_ARGUMENTS = 2;

  public NonComparableValuesException(String message) {
    super(message);
  }

  public NonComparableValuesException(String message, String... args) {
    super(String.format(message, args[0], args[1]), NUM_OF_ARGUMENTS, args);
  }
}
