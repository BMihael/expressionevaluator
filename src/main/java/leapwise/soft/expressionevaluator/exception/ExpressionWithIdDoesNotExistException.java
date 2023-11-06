package leapwise.soft.expressionevaluator.exception;

public class ExpressionWithIdDoesNotExistException extends RuntimeException {
  public ExpressionWithIdDoesNotExistException(String message) {
    super(message);
  }
}
