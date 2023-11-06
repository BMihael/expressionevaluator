package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.bool.BooleanExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.exception.algorithm.stack.HigherPresedanceOperatorFound;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.HIGHER_OPERATOR_PRESEDANCE_FOUND;

public class EvaluationHelper {

  public static Node evaluateExpressionNode(Node n1, ExpressionNode n2, Node n3) {
    return new BooleanExpressionNode(n2.makeResult(n1, n3), NodeType.BOOLEAN_NODE);
  }

  public static Node evaluateNode(Node node) {
    if (NodeType.EXPRESSION_NODE.equals(node.getNodeType())) { // ovo mozda nikada nece biti false
      return evaluateExpressionNode(node.getLeft(), (ExpressionNode) node, node.getRight());
    }
    throw new RuntimeException(
        "Ovdje je doslo do situacije da srednji node nije expression node n1:"
            + node.getNodeType()
            + " "
            + node.getNodeValue()
            + " "
            + node.getLeft().getNodeType()
            + " "
            + node.getLeft().getNodeValue()
            + " "
            + node.getRight().getNodeType()
            + " "
            + node.getRight().getNodeValue());
  }

  public static boolean hasHigherPresedance(ExpressionNode node1, ExpressionNode node2) {
    if (node1.getPresedance() == 0 || node2.getPresedance() == 0) {
      throw new HigherPresedanceOperatorFound(HIGHER_OPERATOR_PRESEDANCE_FOUND);
    }
    return node1.getPresedance() > node2.getPresedance();
  }
}
