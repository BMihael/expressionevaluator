package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;

public class LessThanExpressionNode extends ExpressionNode {

    public LessThanExpressionNode(String value, NodeType type){
        super(value, type, 0);
    }

    @Override
    public boolean makeResult(Node n1, Node n3) {
            String num1 = n1.getNodeValue();
            String num2 = n3.getNodeValue();

            int number1 = 0;
            int number2 = 0;
            try{
                number1 = Integer.parseInt(num1);
                number2 = Integer.parseInt(num2);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
            return number1 < number2;
        }
    }
