package leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;

public class NotEqualsExpressionNode extends EqualsExpressionOperator {
  public NotEqualsExpressionNode(String value, NodeType type) {
    super(value, type, 450);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    return !super.makeResult(n1, n2);
  }
}
