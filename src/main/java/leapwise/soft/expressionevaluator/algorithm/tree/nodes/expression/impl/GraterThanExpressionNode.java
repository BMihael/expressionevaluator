package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

public class GraterThanExpressionNode extends ExpressionNode {
    public GraterThanExpressionNode(String value, NodeType type) {
        super(value, type, 0);
    }

    @Override
    public boolean makeResult(Node n1, Node n2) {
        int number1 = parseInt(n1.getNodeValue());
        int number2 = parseInt(n2.getNodeValue());

        return number1 > number2;
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw ex;
        }
    }

}
