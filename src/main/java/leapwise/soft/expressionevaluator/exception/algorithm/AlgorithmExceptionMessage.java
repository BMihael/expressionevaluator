package leapwise.soft.expressionevaluator.exception.algorithm;

public interface AlgorithmExceptionMessage {
  String EMPTY_EXPRESSION = "Invalid input: Empty expression";
  String NO_LOGICAL_EXPRESSION = "No logical expression found";
  String NO_TREE_GENERATED = "The generation of the tree has not occurred";
  String HIGHER_OPERATOR_PRESEDANCE_FOUND =
      "An operator with higher precedence has been encountered";
  String NON_COMPARABLE_VALUES = "Comparing values %s and %s is not feasible";
}
