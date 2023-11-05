package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.helper.NumericStringCheckerImpl;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

public class EqualsExpressionOperator extends ExpressionNode {

    public EqualsExpressionOperator(String value, NodeType type) {
        super(value, type, 700);
    }

    public static boolean areNumericEquals(Node n1, Node n2) {
        Double a = NumericStringCheckerImpl.checkIfNumeric(n1.getNodeValue());
        Double b = NumericStringCheckerImpl.checkIfNumeric(n2.getNodeValue());
        return a.equals(b);
    }
//https://www.google.com/search?q=can+enum+implement+interface&sca_esv=579669660&sxsrf=AM9HkKl0g3GJfXsaUxDR14dFynGO-4x8Kw%3A1699222933826&ei=lRVIZamDMsuMi-gPptqW6As&udm=&oq=can+enum+imple&gs_lp=Egxnd3Mtd2l6LXNlcnAiDmNhbiBlbnVtIGltcGxlKgIIADIIEAAYywEYgAQyCBAAGMsBGIAEMggQABjLARiABDIIEAAYywEYgAQyBhAAGBYYHjIGEAAYFhgeSPpCUABY8D1wAXgAkAEDmAG0CqABpFWqAQ8wLjguNS4zLjQuMS4yLjO4AQPIAQD4AQHCAgUQABiABMICCBAuGMsBGIAEwgIFECEYoAHCAggQIRgWGB4YHcICCBAAGBYYHhgPwgIHECMYigUYJ8ICCxAAGIoFGLEDGIMBwgIREC4YgAQYsQMYgwEYxwEY0QPCAhEQLhiABBixAxiDARjHARivAcICCBAAGIAEGLEDwgIREC4YgwEYrwEYxwEYsQMYgATCAgQQIxgnwgILEAAYgAQYsQMYgwHCAgsQLhiABBixAxiDAcICBRAuGIAE4gMEGAAgQYgGAQ&sclient=gws-wiz-serp
    @Override
    public boolean makeResult(Node n1, Node n2) { // ovo sve pretvoriti u enumeraciju
        if(n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE){
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if(n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.NUMERIC_NODE){
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if(n1.getNodeType() == NodeType.NUMERIC_NODE &&
                n2.getNodeType() == NodeType.STRING_NODE &&
                !(n1.getNodeType() == NodeType.NON_NULL_NODE || n1.getNodeType() == NodeType.NULL_NODE)){
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if(n2.getNodeType() == NodeType.NUMERIC_NODE &&
                n1.getNodeType() == NodeType.STRING_NODE &&
                !(n2.getNodeType() == NodeType.NON_NULL_NODE || n2.getNodeType() == NodeType.NULL_NODE)){
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        if(n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.STRING_NODE){
            return n2.getNodeValue() == null;
        }
        if(n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.STRING_NODE){
            return n1.getNodeValue() == null;
        }
        if (n1.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE) {
            return areNumericEquals(n1, n2);
        }
        if(n1.getNodeType()==NodeType.STRING_NODE && n2.getNodeType()==NodeType.STRING_NODE){
            return n1.getNodeValue().equals(n2.getNodeValue());
        }
        if(n1.getNodeType()==NodeType.NULL_NODE && n2.getNodeType() == NodeType.NULL_NODE){
            return true;
        }
        if(n1.getNodeType()==NodeType.NULL_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE){
            return false;
        }
        if(n2.getNodeType()==NodeType.NULL_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE){
            return false;
        }
        if(n1.getNodeType()==NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE){
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if(n2.getNodeType()==NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE){
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        if(n1.getNodeType()==NodeType.STRING_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE){
            throw new RuntimeException("Operand " + n1.getNodeValue() + " nije usporediv s vrijednosti " + n2.getNodeValue());
        }
        if(n2.getNodeType()==NodeType.STRING_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE){
            throw new RuntimeException("Operand " + n2.getNodeValue() + " nije usporediv s vrijednosti " + n1.getNodeValue());
        }
        return Boolean.parseBoolean(n1.getNodeValue()) == Boolean.parseBoolean(n2.getNodeValue());
    }

    public static void operandNijeNull(Node node) {
        if (!(node.getNodeType() == NodeType.NULL_NODE)) {
            throw new RuntimeException("Operand " + node.getNodeValue() + " nije usporediv s null vrijednosti");
        }
    }
}
