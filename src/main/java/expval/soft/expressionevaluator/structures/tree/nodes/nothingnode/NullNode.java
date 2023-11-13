package expval.soft.expressionevaluator.structures.tree.nodes.nothingnode;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;

public class NullNode extends Node {
  public NullNode(String value, NodeType type) {
    super(value, type);
  }

  public static boolean oneOfNodesIsNullOrNonNull(Node n1, Node n2) {
    return n1.getNodeType() == NodeType.NULL_NODE
        || n2.getNodeType() == NodeType.NULL_NODE
        || n1.getNodeType() == NodeType.NON_NULL_NODE
        || n2.getNodeType() == NodeType.NON_NULL_NODE;
  }
}
