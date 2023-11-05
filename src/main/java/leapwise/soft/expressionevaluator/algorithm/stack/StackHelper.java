package leapwise.soft.expressionevaluator.algorithm.stack;

import leapwise.soft.expressionevaluator.algorithm.tree.EvaluationHelper;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.EqualsExpressionOperator;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.LogicalAndExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.NotEqualsExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.OrExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.CleanStringNode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StackHelper {
    private static Stack stack = null;

    public static void initStack(){
         stack = new Stack(50);
    }

    public static int getIndexOfParentFromExpression(String expression){
        initStack();
        generateStackBaseOnExpression(expression);

        List<Object> a = Arrays.stream(stack.stackArray).collect(Collectors.toList());

        List<Object> b = a.parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<Object> list = b.parallelStream()
                .filter(item ->{
                    return (((StackNodeWrapper)item).getNode().getNodeType()!=NodeType.STRING_NODE);
                })
                .collect(Collectors.toList());


        StackNodeWrapper node22 = (StackNodeWrapper) list.get(0);
        Node nodeWinner = node22.getNode();

        for(Object item : list){
            Node innerNode = ((StackNodeWrapper)item).getNode();
            if(innerNode.getLevel() == nodeWinner.getLevel()){
                if(EvaluationHelper.hasHigherPresedance((ExpressionNode) innerNode, (ExpressionNode)nodeWinner)){
                    nodeWinner = innerNode;
                    node22 = (StackNodeWrapper) item;
                }
            }
            if(innerNode.getLevel() < nodeWinner.getLevel()){
                nodeWinner =  ((StackNodeWrapper)item).getNode();
                node22 = (StackNodeWrapper) item;
            }
        }
        return node22.getIndex();
    }

    public static void generateStackBaseOnExpression(String expression){
        StackNodeWrapper stackNodeWrapper = null;
        for(int i=0;i<expression.length();i++){
            Node node = null;

            char c = expression.charAt(i);

            //Gate keeper evaluation

            if(c != '(' && c!=')' && c != '=' && c!= 'o' && c!='O' && c != '&' && c!= '!'/* && c!= '|'*/){
                continue;
            }

            if (c == '(') {
                node = new CleanStringNode("(", NodeType.STRING_NODE);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                continue;
            } else if (c == ')') {
                node = new CleanStringNode(")", NodeType.STRING_NODE);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                continue;
            }
            if(c == '='){
                StackNodeWrapper stackNodeWrapper1 = null;
                while(!stack.isEmpty() && ((StackNodeWrapper)stack.peek()).getNode().getNodeType()!=NodeType.EXPRESSION_NODE){
                    stackNodeWrapper1 = (StackNodeWrapper)stack.pop();
                }
                node = new EqualsExpressionOperator("==", NodeType.EXPRESSION_NODE);
                node.setLevel(stackNodeWrapper.level);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                i = i+1;
                continue;
            }
            if(c == 'o' || c =='O'){
                char nextCharMustBeOr = expression.charAt(i+1);
                if(!(nextCharMustBeOr == 'r' || nextCharMustBeOr == 'R')){
                    i = i+1;
                    continue;
                }
                StackNodeWrapper stackNodeWrapper1 = null;
                while(!stack.isEmpty() && ((StackNodeWrapper)stack.peek()).getNode().getNodeType()!=NodeType.EXPRESSION_NODE){
                    stackNodeWrapper1 = (StackNodeWrapper)stack.pop();
                }
                node = new OrExpressionNode("or", NodeType.EXPRESSION_NODE);
                node.setLevel(stackNodeWrapper.level);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                i = i+1;
                continue;
            }
            if(c == '&'){
                char nextCharMustBeOr = expression.charAt(i+1);
                if(!(nextCharMustBeOr == '&')){
                    i = i+1;
                    continue;
                }
                StackNodeWrapper stackNodeWrapper1 = null;
                while(!stack.isEmpty() && ((StackNodeWrapper)stack.peek()).getNode().getNodeType()!=NodeType.EXPRESSION_NODE){
                    stackNodeWrapper1 = (StackNodeWrapper)stack.pop();
                }
                node = new LogicalAndExpressionNode("&&", NodeType.EXPRESSION_NODE);
                node.setLevel(stackNodeWrapper.level);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                i = i+1;
                continue;
            }
            if(c == '!'){
                char nextCharMustBeOr = expression.charAt(i+1);
                if(!(nextCharMustBeOr == '=')){
                    i = i+1;
                    continue;
                }
                StackNodeWrapper stackNodeWrapper1 = null;
                while(!stack.isEmpty() && ((StackNodeWrapper)stack.peek()).getNode().getNodeType()!=NodeType.EXPRESSION_NODE){
                    stackNodeWrapper1 = (StackNodeWrapper)stack.pop();
                }
                node = new NotEqualsExpressionNode("!=", NodeType.EXPRESSION_NODE);
                node.setLevel(stackNodeWrapper.level);
                stackNodeWrapper = new StackNodeWrapper(node, i);
                stack.push(stackNodeWrapper);
                i = i+1;
                continue;
            }
            /*
            if(c == '|'){
                char nextCharMustBeOr = expression.charAt(i+1);
                if(!(nextCharMustBeOr == '|')){
                    i = i+1;
                    continue;
                }
                NodeWrapper nodeWrapper1 = null;
                while(!stack.isEmpty() && ((NodeWrapper)stack.peek()).getNode().getNodeType()!=NodeType.EXPRESSION_NODE){
                    nodeWrapper1 = (NodeWrapper)stack.pop();
                }
                node = new OrExpressionNode("||", NodeType.EXPRESSION_NODE);
                node.setLevel(nodeWrapper.level);
                nodeWrapper = new NodeWrapper(node, i);
                stack.push(nodeWrapper);
                i = i+1;
                continue;
            }*/


        }
    }
}
