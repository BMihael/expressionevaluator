package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.exception.OperatorMismatchException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.OPERATOR_MISMATCH;

public class GraterThanExpressionNode extends ExpressionNode {
  public GraterThanExpressionNode(String value, NodeType type) {
    super(value, type, 0);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    if (n1.getNodeType() != NodeType.NUMERIC_NODE || n2.getNodeType() != NodeType.NUMERIC_NODE) {
      throw new OperatorMismatchException(OPERATOR_MISMATCH, n1.getNodeValue(), n2.getNodeValue());
    }
    int number1 = parseInt(n1.getNodeValue());
    int number2 = parseInt(n2.getNodeValue());

    return number1 > number2;
  }

  private int parseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException ex) {
      throw ex;
    }
  }
}
