package leapwise.soft.expressionevaluator.algorithm.helper;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.exception.NonNumericStringException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_NUMERIC_STRING;

public class NumericStringCheckerImpl {

  public static boolean areNumericEquals(Node n1, Node n2) {
    Double num1 = NumericStringCheckerImpl.checkIfNumeric(n1.getNodeValue());
    Double num2 = NumericStringCheckerImpl.checkIfNumeric(n2.getNodeValue());
    return num1.equals(num2);
  }

  public static Double checkIfNumeric(String input) {
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      throw new NonNumericStringException(NON_NUMERIC_STRING, input);
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
