package expval.soft.expressionevaluator.structures.helper;

import expval.soft.expressionevaluator.exception.NonNumericStringException;
import expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage;
import expval.soft.expressionevaluator.structures.tree.nodes.Node;

import static expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.UTILITY_CLASS_CANNOT_BE_INSTANTIATED;

public final class NumericStringCheckerImpl {

  private NumericStringCheckerImpl() {
    throw new UnsupportedOperationException(UTILITY_CLASS_CANNOT_BE_INSTANTIATED);
  }

  public static boolean areNumericEquals(Node n1, Node n2) {
    Double num1 = NumericStringCheckerImpl.checkIfNumeric(n1.getNodeValue());
    Double num2 = NumericStringCheckerImpl.checkIfNumeric(n2.getNodeValue());
    return num1.equals(num2);
  }

  public static Double checkIfNumeric(String input) {
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      throw new NonNumericStringException(AlgorithmExceptionMessage.NON_NUMERIC_STRING, input);
    }
  }

  public static boolean checkIfNumericReturnBoolean(String input) {
    try {
      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
