package expval.soft.expressionevaluator.util;

public enum ExpressionStripperValues {
  NAME_FIELD_PREFIX("Name: "),
  VALUE_FIELD_PREFIX("Value: ");

  private final String value;

  ExpressionStripperValues(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
