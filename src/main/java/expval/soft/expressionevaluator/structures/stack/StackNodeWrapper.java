package expval.soft.expressionevaluator.structures.stack;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;

public class StackNodeWrapper {

  public static int level;

  Node node;
  final int index;

  public StackNodeWrapper(Node node, int index) {
    this.node = node;
    this.index = index;

    if (node.getNodeValue().equals("(")) {
      increaseLevel();
    } else if (node.getNodeValue().equals(")")) {
      decreaseLevel();
    }
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public Node getNode() {
    return this.node;
  }

  public int getIndex() {
    return index;
  }

  public void increaseLevel() {
    level++;
  }

  public void decreaseLevel() {
    level--;
  }
}
