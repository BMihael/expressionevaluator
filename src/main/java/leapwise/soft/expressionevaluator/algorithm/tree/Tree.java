package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.helper.NumericStringCheckerImpl;
import leapwise.soft.expressionevaluator.algorithm.stack.StackHelper;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.nothingnode.NullNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.CleanStringNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.VariableStringNode;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;
import org.json.JSONException;
import org.json.JSONObject;

import static leapwise.soft.expressionevaluator.algorithm.helper.NumericStringCheckerImpl.checkIfNumericReturnBoolean;
import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NO_LOGICAL_EXPRESSION;

public class Tree {
  private Node root;

  public void add(String value) {
    root = addRecursive(value);
  }

  private Node addRecursive(String value) {
    Node current;
    if (value.equals("null")) {
      current = new NullNode(value, NodeType.NULL_NODE);
      return current;
    }
    if (!(value.contains("&&")
        || value.contains("==")
        || value.contains("OR")
        || value.contains("!=")
        || value.contains(">")
        || value.contains("<"))) {
      if (value.equals("")) {
        current =
            new CleanStringNode(
                value,
                checkIfNumericReturnBoolean(value) ? NodeType.NUMERIC_NODE : NodeType.STRING_NODE);
        return current;
      }
      if (value.contains(".") || !(value.contains("\""))) {
        if (!(NumericStringCheckerImpl.checkIfNumericReturnBoolean(value))) {
          current = new VariableStringNode(value, NodeType.VARIABLE_STRING_NODE);
          return current;
        }
      }

      current =
          new CleanStringNode(
              value,
              checkIfNumericReturnBoolean(value) ? NodeType.NUMERIC_NODE : NodeType.STRING_NODE);
      return current;
    }

    boolean containsOnlyOneExpression = containsOnlyOneExpression(value);
    if (containsOnlyOneExpression) {
      String presentLogicalExpression = presentLogicalExpression(value);
      int indexOfLogicalOperator = value.indexOf(presentLogicalExpression);

      String leftExpression = value.substring(0, indexOfLogicalOperator).trim();
      String rightExpression =
          value.substring(indexOfLogicalOperator + presentLogicalExpression.length()).trim();

      leftExpression = removeParentheses(leftExpression);
      rightExpression = removeParentheses(rightExpression);

      current = decideWhichLogicalExpressionIsParent(presentLogicalExpression);
      current.setLeftNode(addRecursive(leftExpression));
      current.setRightNode(addRecursive(rightExpression));
    } else {
      int indexOfParent = StackHelper.getIndexOfParentFromExpression(value);
      String leftExpression = value.substring(0, indexOfParent);
      String rightExpression = value.substring(indexOfParent + 2);
      current =
          decideWhichLogicalExpressionIsParent(value.substring(indexOfParent, indexOfParent + 2));
      current.setLeftNode(addRecursive(leftExpression));
      current.setRightNode(addRecursive(rightExpression));
    }
    return current;
  }

  private boolean containsOnlyOneExpression(String value) {
    int g = 0;
    if (value.contains("&&")) g++;
    if (value.contains("OR")) g++;
    if (value.contains("==")) g++;
    if (value.contains("!=")) g++;
    if (value.contains(">")) g++;
    if (value.contains("<")) g++;
    return g == 1;
  }

  public ExpressionNode decideWhichLogicalExpressionIsParent(String value) {
    if (value.contains("&&")) return new LogicalAndExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("==")) return new EqualsExpressionOperator(value, NodeType.EXPRESSION_NODE);
    if (value.contains("OR") | value.contains("or"))
      return new OrExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("!=")) return new NotEqualsExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains(">")) return new GraterThanExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("<")) return new LessThanExpressionNode(value, NodeType.EXPRESSION_NODE);
    throw new NoLogicalExpressionException(NO_LOGICAL_EXPRESSION);
  }

  public String removeParentheses(String value) {
    return value.replaceAll("[()]", "");
  }

  public static String presentLogicalExpression(String value) {
    if (value.contains("&&")) return "&&";
    if (value.contains("OR")) return "OR";
    if (value.contains("==")) return "==";
    if (value.contains("!=")) return "!=";
    if (value.contains(">")) return ">";
    if (value.contains("<")) return "<";
    throw new NoLogicalExpressionException(NO_LOGICAL_EXPRESSION);
  }

  public static Node printTree(Node node) {
    if (node == null) return null;

    node.setLeftNode(printTree(node.getLeft()));
    node.setRightNode(printTree(node.getRight()));

    if (node.getLeft() == null && node.getRight() == null) {
      return node;
    }
    return EvaluationHelper.evaluateNode(node);
  }

  public static void fillTree(Node node, JSONObject jsonObject) {
    if (node == null) return;

    fillTree(node.getLeft(), jsonObject);
    fillTree(node.getRight(), jsonObject);

    if (!(node.getLeft() == null && node.getRight() == null)) {
      return;
    }

    if (node.getNodeType() == NodeType.VARIABLE_STRING_NODE) {
      String recursivelyFoundValue =
          recursivelyGetVariableNames("", (VariableStringNode) node, jsonObject).toString();
      node.setValue(recursivelyFoundValue);
      node.setType(determineNodeType(recursivelyFoundValue));
      ((VariableStringNode) node).setChild(null);
    }
  }

  public static Object recursivelyGetVariableNames(
      String path, VariableStringNode node, JSONObject json) {
    path += node.getNodeValue();
    node.setPath(path);

    Object item;
    try {
      item = json.get(node.getNodeValue());
      if (item == JSONObject.NULL) {
        throw new JSONException("Object is null");
      }
    } catch (JSONException ex) {
      return "null";
    }

    if (node.getChild() == null) {
      if (item instanceof String) {
        return "\"" + item + "\"";
      } else if (item instanceof Number) {
        return item;
      } else if (item == JSONObject.NULL) {
        return "null";
      }
      return "X";
    }

    path += ".";
    return recursivelyGetVariableNames(
        path, node.getChild(), (JSONObject) json.get(node.getNodeValue()));
  }

  public static NodeType determineNodeType(String value) {
    if (value.equals("null")) {
      return NodeType.NULL_NODE;
    } else if (value.equals("X")) {
      return NodeType.NON_NULL_NODE;
    } else if (checkIfNumericReturnBoolean(value)) {
      return NodeType.NUMERIC_NODE;
    }
    return NodeType.STRING_NODE;
  }

  public Node getRoot() {
    return root;
  }
}
