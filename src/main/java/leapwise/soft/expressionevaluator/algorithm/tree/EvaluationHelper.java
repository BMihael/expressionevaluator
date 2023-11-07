package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.bool.BooleanExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.exception.CriticalException;
import leapwise.soft.expressionevaluator.exception.algorithm.stack.HigherPrecedenceOperatorFound;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.CRITICAL_EXCEPTION_MESSAGE;
import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.HIGHER_OPERATOR_PRECEDENCE_FOUND;

public class EvaluationHelper {

  public static Node evaluateExpressionNode(Node n1, ExpressionNode n2, Node n3) {
    return new BooleanExpressionNode(n2.makeResult(n1, n3), NodeType.BOOLEAN_NODE);
  }

  public static Node evaluateNode(Node node) {
    if (NodeType.EXPRESSION_NODE.equals(node.getNodeType())) {
      return evaluateExpressionNode(node.getLeft(), (ExpressionNode) node, node.getRight());
    }
    throw new CriticalException(CRITICAL_EXCEPTION_MESSAGE);
  }

  public static boolean hasHigherPresedance(ExpressionNode node1, ExpressionNode node2) {
    if (node1.getPresedance() == 0 || node2.getPresedance() == 0) {
      throw new HigherPrecedenceOperatorFound(HIGHER_OPERATOR_PRECEDENCE_FOUND);
    }
    return node1.getPresedance() > node2.getPresedance();
  }
}
