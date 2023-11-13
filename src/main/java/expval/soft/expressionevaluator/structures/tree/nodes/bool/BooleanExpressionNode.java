package expval.soft.expressionevaluator.structures.tree.nodes.bool;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;

public class BooleanExpressionNode extends Node {
  public BooleanExpressionNode(boolean value, NodeType type) {
    super(String.valueOf(value), type);
  }
}
