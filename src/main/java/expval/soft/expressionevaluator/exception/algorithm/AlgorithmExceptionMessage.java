package expval.soft.expressionevaluator.exception.algorithm;

public final class AlgorithmExceptionMessage {

  private AlgorithmExceptionMessage() {
    throw new UnsupportedOperationException(UTILITY_CLASS_CANNOT_BE_INSTANTIATED);
  }

  public static String EMPTY_EXPRESSION = "Invalid input: Empty expression";
  public static String NO_LOGICAL_EXPRESSION = "No logical expression found";
  public static String NO_TREE_GENERATED = "The generation of the tree has not occurred";
  public static String HIGHER_OPERATOR_PRECEDENCE_FOUND =
      "An operator with higher precedence has been encountered";
  public static String NON_COMPARABLE_VALUES = "Comparing values %s and %s is not feasible";
  public static String FIELD_DOES_NOT_EXISTS_IN_JSON =
      "The specified field, '%s' is not found within the JSON dataset";
  public static String CRITICAL_EXCEPTION_MESSAGE = "Critical Exception Alert";
  public static String NON_NUMERIC_STRING = "Input %s is not a numeric string";
  public static String INVALID_NUMBER_OF_ARGUMENTS = " Invalid number of arguments";
  public static String UTILITY_CLASS_CANNOT_BE_INSTANTIATED =
      "This is a utility class and cannot be instantiated";
}
