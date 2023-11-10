package leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;

import leapwise.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_COMPARABLE_VALUES;

public class GraterThanExpressionNode extends ExpressionNode {
  public GraterThanExpressionNode(String value, NodeType type) {
    super(value, type, 1);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    if (n1.getNodeType() != NodeType.NUMERIC_NODE || n2.getNodeType() != NodeType.NUMERIC_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    double num1 = parseNumber(n1.getNodeValue());
    double num2 = parseNumber(n2.getNodeValue());

    return num1 > num2;
  }
}
