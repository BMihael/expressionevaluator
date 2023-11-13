package expval.soft.expressionevaluator.structures.tree.nodes;

public abstract class Node {
  private String value;
  Node left;
  Node right;
  private NodeType type;
  private int level;

  public Node(String value, NodeType type) {
    this.value = value;
    this.type = type;
  }

  public String getNodeValue() {
    return this.value;
  }

  public NodeType getNodeType() {
    return this.type;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public void setLeftNode(Node node) {
    this.left = node;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  public void setType(NodeType type) {
    this.type = type;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setRightNode(Node node) {
    this.right = node;
  }
}
