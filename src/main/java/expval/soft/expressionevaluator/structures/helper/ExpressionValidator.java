package expval.soft.expressionevaluator.structures.helper;

import expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage;
import expval.soft.expressionevaluator.exception.algorithm.tree.EmptyExpressionException;
import expval.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;

public class ExpressionValidator {

  public static String checkValidityOfExpression(String expression) {
    if (expression == null || expression.isEmpty()) {
      throw new EmptyExpressionException(AlgorithmExceptionMessage.EMPTY_EXPRESSION);
    }

    int openCount = 0;
    int closeCount = 0;

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);

      if (c == '(') {
        openCount++;
      } else if (c == ')') {
        closeCount++;

        if (openCount > 0 && openCount == closeCount) {
          return expression.substring(1, i);
        }
      }
    }
    if (openCount == 0 && closeCount == 0) {
      return expression;
    }
    throw new NoLogicalExpressionException(AlgorithmExceptionMessage.NO_LOGICAL_EXPRESSION);
  }
}
