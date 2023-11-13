package expval.soft.expressionevaluator.structures.tree.nodes.string;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;

public abstract class StringNode extends Node {
  public StringNode(String value, NodeType type) {
    super(value, type);
  }
}
