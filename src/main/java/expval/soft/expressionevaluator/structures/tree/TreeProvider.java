package expval.soft.expressionevaluator.structures.tree;

import expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage;
import expval.soft.expressionevaluator.exception.algorithm.tree.NoTreeGenerated;
import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.helper.ExpressionValidator;
import org.json.JSONObject;

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
      throw new NoTreeGenerated(AlgorithmExceptionMessage.NO_TREE_GENERATED);
    }
  }
}
