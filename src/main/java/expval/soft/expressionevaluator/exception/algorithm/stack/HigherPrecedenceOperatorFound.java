package expval.soft.expressionevaluator.exception.algorithm.stack;

public class HigherPrecedenceOperatorFound extends RuntimeException {
  public HigherPrecedenceOperatorFound(String message) {
    super(message);
  }
}
