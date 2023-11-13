package expval.soft.expressionevaluator.structures.tree;

import expval.soft.expressionevaluator.exception.CriticalException;
import expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage;
import expval.soft.expressionevaluator.exception.algorithm.stack.HigherPrecedenceOperatorFound;
import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;
import expval.soft.expressionevaluator.structures.tree.nodes.bool.BooleanExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;

public class EvaluationHelper {

  public static Node evaluateNode(Node node) {
    if (NodeType.EXPRESSION_NODE.equals(node.getNodeType())) {
      return evaluateExpressionNode(node.getLeft(), (ExpressionNode) node, node.getRight());
    }
    throw new CriticalException(AlgorithmExceptionMessage.CRITICAL_EXCEPTION_MESSAGE);
  }

  public static boolean hasHigherPresedance(ExpressionNode node1, ExpressionNode node2) {
    if (node1.getPresedance() == 0 || node2.getPresedance() == 0) {
      throw new HigherPrecedenceOperatorFound(AlgorithmExceptionMessage.HIGHER_OPERATOR_PRECEDENCE_FOUND);
    }
    return node1.getPresedance() > node2.getPresedance();
  }

  private static Node evaluateExpressionNode(Node n1, ExpressionNode n2, Node n3) {
    return new BooleanExpressionNode(n2.makeResult(n1, n3), NodeType.BOOLEAN_NODE);
  }
}
