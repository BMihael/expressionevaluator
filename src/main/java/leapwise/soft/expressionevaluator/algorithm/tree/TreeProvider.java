package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.helper.ExpressionValidator;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoTreeGenerated;
import org.json.JSONObject;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NO_TREE_GENERATED;

public class TreeProvider {
  static Tree tree = null;

  public static void provideExpression(String expression) {
    ExpressionValidator.checkValidityOfExpression(expression);
    generate();
    tree.add(expression);
  }

  public static String printResult() {
    checkIfTreeIsGenerated();
    Node node = Tree.printTree(tree.getRoot());
    tree = null;
    return node.getNodeValue();
  }

  public static void fillTreeHelper(JSONObject input) {
    checkIfTreeIsGenerated();
    Tree.fillTree(tree.getRoot(), input);
  }

  private static void generate() {
    tree = new Tree();
    tree.add("");
  }

  private static void checkIfTreeIsGenerated() {
    if (tree == null) {
      throw new NoTreeGenerated(NO_TREE_GENERATED);
    }
  }
}
