package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.bool.BooleanExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

public class EvaluationHelper {

    public static Node evaluateNodeBackup(Node n1, Node n2, Node n3) {
        if (NodeType.EXPRESSION_NODE.equals(n2.getNodeType())) {
            return evaluateExpressionNode(n1, (ExpressionNode) n2, n3);
        }
        throw new RuntimeException(
                "Ovdje je doslo do situacije da srednji node nije expression node n1:" +
                        n1.getNodeType() + " " + n1.getNodeValue() + " " +
                        n2.getNodeType() + " " + n2.getNodeValue() + " " +
                        n3.getNodeType() + " " + n3.getNodeValue());
    }

    public static Node evaluateExpressionNode(Node n1, ExpressionNode n2, Node n3) {
        return new BooleanExpressionNode(n2.makeResult(n1, n3), NodeType.BOOLEAN_NODE);
    }

    public static Node evaluateNode(Node node) {
        if (NodeType.EXPRESSION_NODE.equals(node.getNodeType())) {
            return evaluateExpressionNode(node.getLeft(), (ExpressionNode) node, node.getRight());
        }
        throw new RuntimeException(
                "Ovdje je doslo do situacije da srednji node nije expression node n1:" +
                        node.getNodeType() + " " + node.getNodeValue() + " " +
                        node.getLeft().getNodeType() + " " + node.getLeft().getNodeValue() + " " +
                        node.getRight().getNodeType() + " " + node.getRight().getNodeValue());
    }

/*
    public static void get_LogicalAND_ExpressionResult(Main.Node node) {
        if (node.left.value.equals("false") && node.right.value.equals("false")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("false")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("false") && node.right.value.equals("true")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("true")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        }
    }

    public static void get_LogicalEQUALS_ExpressionResult(Main.Node node) {
        if(node.left.type == Main.InnerNodeType.STRING && node.right.type == Main.InnerNodeType.STRING ){
            node.value = String.valueOf(node.left.value.equals(node.right.value));
            node.left = null;
            node.right = null;
            return;
        }
        if (node.left.value.equals("false") && node.right.value.equals("false")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("false")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("false") && node.right.value.equals("true")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("true")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        }
    }

    public static void get_LogicalOR_ExpressionResult(Main.Node node) {
        if (node.left.value.equals("false") && node.right.value.equals("false")) {
            node.value = "false";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("false")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("false") && node.right.value.equals("true")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        } else if (node.left.value.equals("true") && node.right.value.equals("true")) {
            node.value = "true";
            node.left = null;
            node.right = null;
        }
    }
    public static void get_LogicalNOTEQUALS_ExpressionResult(Main.Node node) {
        if(node.left.type == Main.InnerNodeType.STRING && node.right.type == Main.InnerNodeType.STRING ){
            node.value = String.valueOf(!(node.left.value.equals(node.right.value)));
            node.left = null;
            node.right = null;
            return;
        }
    }
    public static void get_LogicalGREATERTHAN_ExpressionResult(Main.Node node) {
        Double a = checker.checkIfNumeric(node.left.value);
        Double b = checker.checkIfNumeric(node.right.value);

        node.value = String.valueOf(a>b);
        node.left = null;
        node.right = null;
        return;

    }

    public static void get_LogicalLESSTHAN_ExpressionResult(Main.Node node) {
        Double a = checker.checkIfNumeric(node.left.value);
        Double b = checker.checkIfNumeric(node.right.value);

        node.value = String.valueOf(a<b);
        node.left = null;
        node.right = null;
        return;

    }
*/

    public static boolean hasHigherPresedance(ExpressionNode node1, ExpressionNode node2){
        if(node1.getPresedance() == 0 ||node2.getPresedance() == 0 ){
            throw new RuntimeException("Pogledaj ovdje");
        }
        return node1.getPresedance() > node2.getPresedance();
    }
}




