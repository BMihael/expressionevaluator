package leapwise.soft.expressionevaluator.structures.tree.nodes.string;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;

public abstract class StringNode extends Node {
  public StringNode(String value, NodeType type) {
    super(value, type);
  }
}
