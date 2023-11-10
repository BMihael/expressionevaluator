package leapwise.soft.expressionevaluator.structures.tree.nodes.string.impl;

import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.structures.tree.nodes.string.StringNode;

public class VariableStringNode extends StringNode {
  private VariableStringNode child;

  public VariableStringNode(String value, NodeType type) {
    super(value.substring(0, value.indexOf(".") == -1 ? value.length() : value.indexOf(".")), type);
    if (value.contains("."))
      child =
          new VariableStringNode(
              value.substring(value.indexOf(".") + 1), NodeType.VARIABLE_STRING_NODE);
  }

  public void setChild(String value) {
    if (value == null) {
      this.child = null;
      return;
    }

    this.child = getByRecursion(value);
  }

  private VariableStringNode getByRecursion(String value) {
    if (!value.contains(".")) {
      return new VariableStringNode(value, NodeType.VARIABLE_STRING_NODE);
    }
    return getByRecursion(value.substring(value.indexOf(".") + 1));
  }

  public VariableStringNode getChild() {
    return child;
  }
}
