package leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_COMPARABLE_VALUES;

public class OrExpressionNode extends ExpressionNode {
  public OrExpressionNode(String value, NodeType type) {
    super(value, type, 800);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    if (n1.getNodeType() != NodeType.BOOLEAN_NODE || n2.getNodeType() != NodeType.BOOLEAN_NODE) {
      throw new NonComparableValuesException(NON_COMPARABLE_VALUES);
    }
    return Boolean.parseBoolean(n1.getNodeValue()) || Boolean.parseBoolean(n2.getNodeValue());
  }
}