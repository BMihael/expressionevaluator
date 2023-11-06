package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.helper.NumericStringCheckerImpl;
import leapwise.soft.expressionevaluator.algorithm.stack.StackHelper;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.nothingnode.NullNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.CleanStringNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.VariableStringNode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static leapwise.soft.expressionevaluator.algorithm.helper.NumericStringCheckerImpl.checkIfNumericReturnBoolean;

public class Tree {
    public Node root;

    public void add(String value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, String value) {
        if (!(value.contains("&&") || value.contains("==") || value.contains("OR") || value.contains("!=") || value.contains(">") || value.contains("<"))) {
            if(value.equals("")){
                current = new CleanStringNode(value, checkIfNumericReturnBoolean(value) ? NodeType.NUMERIC_NODE : NodeType.STRING_NODE);
                return current;
            }
            if(value.equals("null")){
                current = new NullNode(value, NodeType.NULL_NODE);
                return current;
            }
            if(value.contains(".") || !(value.contains("\""))){
                if(!(NumericStringCheckerImpl.checkIfNumericReturnBoolean(value))){
                    current = new VariableStringNode(value, NodeType.VARIABLE_STRING_NODE);
                    return current;
                }
            }

            current = new CleanStringNode(value, checkIfNumericReturnBoolean(value) ? NodeType.NUMERIC_NODE : NodeType.STRING_NODE);
            return current;
        }

        boolean containsOnlyOneExpression = containsOnlyOneExpression(value);
        if(containsOnlyOneExpression){
            String presentLogicalExpression = presentLogicalExpression(value);
            int indexOfLogicalOperator = value.indexOf(presentLogicalExpression);

            String leftExpression = value.substring(0, indexOfLogicalOperator).trim();
            String rightExpression = value.substring(indexOfLogicalOperator + presentLogicalExpression.length()).trim();

            leftExpression = removeZagrade(leftExpression);
            rightExpression = removeZagrade(rightExpression);

            current = decideWhichLogicalExpressionIsParent(presentLogicalExpression);
            current.setLeftNode(addRecursive(current.getLeft(), leftExpression));
            current.setRightNode(addRecursive(current.getRight(), rightExpression));
        }else{
            int indexOfParent = StackHelper.getIndexOfParentFromExpression(value);
            String leftExpression = value.substring(0,indexOfParent);
            String rightExpression = value.substring(indexOfParent + 2);
            current = decideWhichLogicalExpressionIsParent(value.substring(indexOfParent,indexOfParent+2));
            current.setLeftNode(addRecursive(current.getLeft(), leftExpression));
            current.setRightNode(addRecursive(current.getRight(), rightExpression));
        }
        return current;
    }

    private static int containeEx(String value) {
        if (value.contains("&&") || value.contains("OR") || value.contains("==") || value.contains("!="))
            return 2;
        if (value.contains(">") || value.contains("<"))
            return 1;
        throw  new RuntimeException("OOO");
    }

    private boolean containsOnlyOneExpression(String value){
        int g = 0;
        if (value.contains("&&"))
            g++;
        if (value.contains("OR"))
            g++;
        if (value.contains("=="))
            g++;
        if (value.contains("!="))
            g++;
        if (value.contains(">"))
            g++;
        if (value.contains("<"))
            g++;
        return g==1;
    }


    public static String getParentLogicalExpression(String input) {
        Stack<Integer> stack = new Stack<>();
        int startIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    startIndex = stack.pop();
                    if (stack.isEmpty()) {
                        return input.substring(startIndex, i + 1);
                    }
                } else {
                    // Mismatched parentheses
                    throw new RuntimeException("Invalid input: Mismatched parentheses");
                }
            }
        }

        if (!stack.isEmpty()) {
            // Mismatched parentheses
            throw new RuntimeException("Invalid input: Mismatched parentheses");
        }

        if (startIndex != -1) {
            return input.substring(startIndex);
        }

          throw new RuntimeException( "No logical expression found");
    }

    public static String calculate(String input) {
        if (input == null || input.isEmpty()) {
             throw new RuntimeException("Invalid input: Empty expression");
        }

        int openCount = 0;
        int closeCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                closeCount++;

                if (openCount > 0 && openCount == closeCount) {
                    // Found the most outer parentheses
                    return input.substring(1, i); // Exclude the outer parentheses
                }
            }
        }

        throw new RuntimeException( "No logical expression found");
    }

    public ExpressionNode decideWhichLogicalExpressionIsParent(String value) {
        if (value.contains("&&"))
            return new LogicalAndExpressionNode(value, NodeType.EXPRESSION_NODE);
        if (value.contains("=="))
            return new EqualsExpressionOperator(value, NodeType.EXPRESSION_NODE);
        if (value.contains("OR") | value.contains("or"))
            return new OrExpressionNode(value, NodeType.EXPRESSION_NODE);
        if (value.contains("!="))
            return new NotEqualsExpressionNode(value, NodeType.EXPRESSION_NODE);
        if (value.contains(">"))
            return new GraterThanExpressionNode(value, NodeType.EXPRESSION_NODE);
        if (value.contains("<"))
            return new LessThanExpressionNode(value, NodeType.EXPRESSION_NODE);
        throw new RuntimeException("Ovjde sam nesto nije dobro: " + value);
    }

    public String removeZagrade(String value) {
        return value.replaceAll("[()]", "");
    }

    public static String removeOuterParentheses(String expression) {
        int openCount = 0;
        StringBuilder result = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                if (openCount > 0) {
                    result.append(c);
                }
                openCount++;
            } else if (c == ')') {
                openCount--;
                if (openCount > 0) {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }


    public static String presentLogicalExpression(String value) {
        if (value.contains("&&"))
            return "&&";
        if (value.contains("OR"))
            return "OR";
        if (value.contains("=="))
            return "==";
        if (value.contains("!="))
            return "!=";
        if (value.contains(">"))
            return ">";
        if (value.contains("<"))
            return "<";
        throw new RuntimeException("Nije pronađen logički izraz");
    }

    public static Node printInorder(Node node) {
        if (node == null)
            return null;

        node.setLeftNode(printInorder(node.getLeft()));
        node.setRightNode(printInorder(node.getRight()));

        if (node.getLeft() == null && node.getRight() == null) {
            return node;
        }
        return EvaluationHelper.evaluateNode(node);
    }

    public static void fillTree(Node node, JSONObject obj) {
        if (node == null)
            return;

        fillTree(node.getLeft(), obj);
        fillTree(node.getRight(), obj);

        if (node.getLeft() == null && node.getRight() == null) {
            if(node.getNodeType() == NodeType.VARIABLE_STRING_NODE){

                String hh = maloRekurzije((VariableStringNode) node, obj).toString();
                node.setValue(hh);
                ((VariableStringNode) node).setChild(null);
                ((VariableStringNode) node).setType(odrediTipNoda(hh));
            }
        }
    }

    public static NodeType odrediTipNoda(String value) {
        if (value.equals("null")) {
            return NodeType.NULL_NODE;
        } else if (value.equals("X")) {
            return NodeType.NON_NULL_NODE;
        }
        if (checkIfNumericReturnBoolean(value)) {
            return NodeType.NUMERIC_NODE;
        }
        return NodeType.STRING_NODE;
    }

    public static Object maloRekurzije(VariableStringNode node, JSONObject json) {
        Object a = null;

        if (node.getChild() == null) {
            try {
                Object item = json.get(node.getNodeValue());
                if(item instanceof String){
                    a = (String)item;
                    a = "\""+a + "\"";
                    return a;
                }else if(item instanceof  Number){
                    a = (Number)item;
                    return  a;
                }else if(item == JSONObject.NULL){
                    return "null";
                }
               return "X";
            } catch (JSONException ex) {
                return "null";
               // throw new RuntimeException("Nije pronađena vrijednost " + node.getNodeValue());
            }
        }

        return maloRekurzije(node.getChild(), (JSONObject) json.get(node.getNodeValue()));
    }

    public static ArrayList<String> parseString(String input) {
        ArrayList<String> elements = new ArrayList<>();

        // Use regular expressions to split the input string
        Pattern pattern = Pattern.compile("([0-9]+|\\>|\\<|\\=\\=|\\w+(\\.\\w+)*)"); // This pattern matches numbers, '>', '<', and '='
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            elements.add(matcher.group());
        }

        return elements;
    }

}
