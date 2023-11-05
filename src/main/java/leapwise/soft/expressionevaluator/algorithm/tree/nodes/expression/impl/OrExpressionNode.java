package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

public class OrExpressionNode extends ExpressionNode {
    public OrExpressionNode(String value, NodeType type) {
        super(value, type, 800);
    }
    @Override
    public boolean makeResult(Node n1, Node n2) {
        return Boolean.parseBoolean(n1.getNodeValue()) || Boolean.parseBoolean (n2.getNodeValue());
    }
}
