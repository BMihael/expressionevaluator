package leapwise.soft.expressionevaluator.exception.algorithm;

public interface AlgorithmExceptionMessage {
  String EMPTY_EXPRESSION = "Invalid input: Empty expression";
  String NO_LOGICAL_EXPRESSION = "No logical expression found";
  String NO_TREE_GENERATED = "The generation of the tree has not occurred";
  String HIGHER_OPERATOR_PRECEDENCE_FOUND =
      "An operator with higher precedence has been encountered";
  String NON_COMPARABLE_VALUES = "Comparing values %s and %s is not feasible";
  String FIELD_DOES_NOT_EXISTS_IN_JSON =
      "The specified field, '%s' is not found within the JSON dataset";
  String CRITICAL_EXCEPTION_MESSAGE = "Critical Exception Alert";
  String NON_NUMERIC_STRING = "Input is not a numeric string";
  String INVALID_NUMBER_OF_ARGUMENTS = " Invalid number of arguments";
}
