package leapwise.soft.expressionevaluator.algorithm.tree.nodes.string;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;

public abstract class StringNode extends Node {
    public StringNode(String value, NodeType type) {
        super(value, type);
    }
}
