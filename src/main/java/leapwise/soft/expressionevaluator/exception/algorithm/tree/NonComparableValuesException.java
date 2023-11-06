package leapwise.soft.expressionevaluator.exception.algorithm.tree;

public class NonComparableValuesException extends RuntimeException {
  public NonComparableValuesException(String message) {
    super(message);
  }

  public NonComparableValuesException(String message, String... arr) {
    super(String.format(message, arr[0], arr[1]));
  }
}
