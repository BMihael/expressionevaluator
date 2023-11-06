package leapwise.soft.expressionevaluator.algorithm.tree.nodes.bool;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;

public class BooleanExpressionNode extends Node {
  public BooleanExpressionNode(boolean value, NodeType type) {
    super(String.valueOf(value), type);
  }
}
