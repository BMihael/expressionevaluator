package expval.soft.expressionevaluator;

public enum ExpressionResult {
  TRUE,
  FALSE;

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
