package leapwise.soft.expressionevaluator.structures.tree.nodes.number;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;

public class NumberNode extends Node {
  public NumberNode(String value, NodeType type) {
    super(String.valueOf(value), type);
  }
}
