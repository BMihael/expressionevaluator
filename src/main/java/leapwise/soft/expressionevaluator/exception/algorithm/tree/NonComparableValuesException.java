package leapwise.soft.expressionevaluator.exception.algorithm.tree;

public class NonComparableValuesException extends RuntimeException {
  public NonComparableValuesException(String message) {
    super(message);
  }

  public NonComparableValuesException(String message, String... args) {
    super(String.format(message, args[0], args[1]));
  }
}
