package leapwise.soft.expressionevaluator.exception.algorithm.stack;

public class HigherPresedanceOperatorFound extends RuntimeException {
  public HigherPresedanceOperatorFound(String message) {
    super(message);
  }
}
