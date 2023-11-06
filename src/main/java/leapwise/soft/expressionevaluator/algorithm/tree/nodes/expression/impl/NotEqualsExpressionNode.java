package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;

public class NotEqualsExpressionNode extends EqualsExpressionOperator {
  public NotEqualsExpressionNode(String value, NodeType type) {
    super(value, type, 450);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    return !super.makeResult(n1, n2);
  }
}
