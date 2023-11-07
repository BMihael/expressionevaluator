package leapwise.soft.expressionevaluator.algorithm.helper;

import leapwise.soft.expressionevaluator.exception.NonNumericStringException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_NUMERIC_STRING;

public class NumericStringCheckerImpl {

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
