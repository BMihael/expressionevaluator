package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

import static leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.EqualsExpressionOperator.areNumericEquals;

public class NotEqualsExpressionNode extends ExpressionNode {
    public NotEqualsExpressionNode(String value, NodeType type) {
        super(value, type, 450);
    }

    @Override
    public boolean makeResult(Node n1, Node n2) {
        return !executeExpression(n1, n2);
    }

    private boolean executeExpression(Node n1, Node n2) {
        if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE) {
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.NUMERIC_NODE) {
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if (n1.getNodeType() == NodeType.NUMERIC_NODE &&
                n2.getNodeType() == NodeType.STRING_NODE &&
                !(n1.getNodeType() == NodeType.NON_NULL_NODE || n1.getNodeType() == NodeType.NULL_NODE)) {
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if (n2.getNodeType() == NodeType.NUMERIC_NODE &&
                n1.getNodeType() == NodeType.STRING_NODE &&
                !(n2.getNodeType() == NodeType.NON_NULL_NODE || n2.getNodeType() == NodeType.NULL_NODE)) {
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.STRING_NODE) {
            return n2.getNodeValue() == null;
        }
        if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.STRING_NODE) {
            return n1.getNodeValue() == null;
        }
        if (n1.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE) {
            return areNumericEquals(n1, n2);
        }
        if (n1.getNodeType() == NodeType.STRING_NODE && n2.getNodeType() == NodeType.STRING_NODE) {
            return n1.getNodeValue().equals(n2.getNodeValue());
        }
        if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NULL_NODE) {
            return true;
        }
        if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
            return false;
        }
        if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE) {
            return false;
        }
        if (n1.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if (n2.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        if (n1.getNodeType() == NodeType.STRING_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if (n2.getNodeType() == NodeType.STRING_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE) {
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        return (Boolean.parseBoolean(n1.getNodeValue()) != Boolean.parseBoolean(n2.getNodeValue()));
    }
}
