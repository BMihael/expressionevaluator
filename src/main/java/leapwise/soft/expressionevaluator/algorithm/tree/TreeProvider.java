package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.EmptyExpressionException;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoTreeGenerated;
import org.json.JSONObject;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NO_LOGICAL_EXPRESSION;
import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NO_TREE_GENERATED;

public class TreeProvider {
  static Tree tree = null;

  public static void generate() {
    tree = new Tree();
    tree.add("");
  }

  public static void provideExpression(String expression) {
    checkValidityOfExpression(expression);
    generate();
    tree.add(expression);
  }

  public static String printResult() {
    checkIfTreeIsGenerated();
    Node node = tree.printTree(tree.root);
    tree = null;
    return node.getNodeValue();
  }

  public static void fillTreeHelper(JSONObject input) {
    checkIfTreeIsGenerated();
    tree.fillTree(tree.root, input);
  }

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
    throw new NoLogicalExpressionException(NO_LOGICAL_EXPRESSION);
  }

  private static void checkIfTreeIsGenerated() {
    if (tree == null) {
      throw new NoTreeGenerated(NO_TREE_GENERATED);
    }
  }
}
