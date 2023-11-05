package leapwise.soft.expressionevaluator.algorithm.helper;

public class NumericStringCheckerImpl {

    public static Double checkIfNumeric(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Input is not a numeric string: " + input);
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