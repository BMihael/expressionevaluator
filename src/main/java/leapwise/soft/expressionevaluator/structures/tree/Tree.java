package leapwise.soft.expressionevaluator.structures.tree;

import leapwise.soft.expressionevaluator.structures.helper.NumericStringCheckerImpl;
import leapwise.soft.expressionevaluator.structures.stack.StackHelper;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals.EqualsExpressionOperator;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals.NotEqualsExpressionNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.nothingnode.NullNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.number.NumberNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.string.impl.CleanStringNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.string.impl.VariableStringNode;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NoLogicalExpressionException;
import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;
import leapwise.soft.expressionevaluator.structures.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.GraterThanExpressionNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.LessThanExpressionNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.LogicalAndExpressionNode;
import leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.OrExpressionNode;
import org.json.JSONException;
import org.json.JSONObject;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NO_LOGICAL_EXPRESSION;

public class Tree {
  private Node root;

  public Node getRoot() {
    return root;
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
          recursivelyGetVariableNames((VariableStringNode) node, jsonObject).toString();
      node.setValue(recursivelyFoundValue);
      node.setType(determineNodeType(recursivelyFoundValue));
      ((VariableStringNode) node).setChild(null);
    }
  }

  public void add(String value) {
    root = addRecursive(value);
  }

  private Node addRecursive(String value) {
    if (value.equals("null")) {
      return new NullNode(value, NodeType.NULL_NODE);
    }
    if (!(value.contains("&&")
            || value.contains("==")
            || value.toLowerCase().matches("(.*\\b)or{1,2}(\\b.*)")
            || value.contains("!=")
            || value.contains(">")
            || value.contains("<"))) {
      if (value.equals("")) {
        return new CleanStringNode(
                value,
                NumericStringCheckerImpl.checkIfNumericReturnBoolean(value)
                        ? NodeType.NUMERIC_NODE
                        : NodeType.STRING_NODE);
      }
      if (value.contains(".") || !(value.contains("\""))) {
        if (!(NumericStringCheckerImpl.checkIfNumericReturnBoolean(value))) {
          return new VariableStringNode(value, NodeType.VARIABLE_STRING_NODE);
        }
      }
      if (NumericStringCheckerImpl.checkIfNumericReturnBoolean(value)) {
        return new NumberNode(value, NodeType.NUMERIC_NODE);
      }
      return new CleanStringNode(value, NodeType.STRING_NODE);
    }

    boolean containsOnlyOneExpression = containsOnlyOneLogicalExpression(value);
    if (containsOnlyOneExpression) {
      return processExpressionWithOneLogicalExpression(value);
    }
    return partitionExpressionFurther(value);
  }

  /**
   * This method processes a string expression that exclusively contains a single logical
   * expression, ready for division into nodes. The divided nodes are categorized into a parent
   * node, which captures the remaining logical expression, while the children nodes receive values
   * corresponding to specific data types through recursive processing.
   *
   * @param expression String containing only one logical expression
   * @return Parent node containing logical expression
   */
  private Node processExpressionWithOneLogicalExpression(String expression) {
    Node current;
    String presentLogicalExpression = getRemainingLogicalExpression(expression);
    int indexOfLogicalOperator = expression.indexOf(presentLogicalExpression);

    String leftExpression = expression.substring(0, indexOfLogicalOperator).trim();
    String rightExpression =
        expression.substring(indexOfLogicalOperator + presentLogicalExpression.length()).trim();

    leftExpression = removeParentheses(leftExpression);
    rightExpression = removeParentheses(rightExpression);

    current = getLogicalExpressionAsNodeByValue(presentLogicalExpression);
    current.setLeftNode(addRecursive(leftExpression));
    current.setRightNode(addRecursive(rightExpression));
    return current;
  }

  /**
   * This method handles a string expression containing multiple logical expressions that require
   * partitioning. The partitioning process utilizes a stack to identify the parent logical
   * expression. The string is then divided into two parts, with both segments undergoing recursion
   * for further partitioning. Middle part is recognized as parent node.
   *
   * @param expression String containing more than one logical expression
   * @return Parent node containing logical expression
   */
  private Node partitionExpressionFurther(String expression) {
    Node current;
    int indexOfParentExpression = StackHelper.getIndexOfParentExpression(expression);
    String leftExpression = expression.substring(0, indexOfParentExpression);
    String rightExpression = expression.substring(indexOfParentExpression + 2);
    current =
        getLogicalExpressionAsNodeByValue(
            expression.substring(
                indexOfParentExpression,
                indexOfParentExpression + 2)); // što ako nije +2 npr nije "&&" nego ">"??
    current.setLeftNode(addRecursive(leftExpression));
    current.setRightNode(addRecursive(rightExpression));
    return current;
  }

  /**
   * Method used to evaluate if expression contains more than one logical expression.
   *
   * @param value String expression
   * @return true if there is more than one logical expression in given string
   */
  private boolean containsOnlyOneLogicalExpression(String value) {
    int g = 0;
    String[] logicalOperators = {"&&", "or", "==", "!=", ">", "<"};

    for (String operator : logicalOperators) {
      if (operator.equals("or")) {
        if (value.toLowerCase().matches("(.*\\b)or{1,2}(\\b.*)")) {
          g++;
          if (g > 1) {
            return false;
          }
        }
      } else {
        if (value.toLowerCase().contains(operator)) {
          g++;
          if (g > 1) {
            return false;
          }
        }
      }
    }
    return true;
  }

  private ExpressionNode getLogicalExpressionAsNodeByValue(String value) {
    if (value.contains("&&")) return new LogicalAndExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("==")) return new EqualsExpressionOperator(value, NodeType.EXPRESSION_NODE);
    if (value.contains("OR") | value.contains("or"))
      return new OrExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("!=")) return new NotEqualsExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains(">")) return new GraterThanExpressionNode(value, NodeType.EXPRESSION_NODE);
    if (value.contains("<")) return new LessThanExpressionNode(value, NodeType.EXPRESSION_NODE);
    throw new NoLogicalExpressionException(NO_LOGICAL_EXPRESSION);
  }

  private String removeParentheses(String value) {
    return value.replaceAll("[()]", "");
  }

  private static String getRemainingLogicalExpression(String value) {
    if (value.contains("&&")) return "&&";
    if (value.contains("OR")) return "OR";
    if (value.contains("==")) return "==";
    if (value.contains("!=")) return "!=";
    if (value.contains(">")) return ">";
    if (value.contains("<")) return "<";
    throw new NoLogicalExpressionException(NO_LOGICAL_EXPRESSION);
  }

  private static Object recursivelyGetVariableNames(VariableStringNode node, JSONObject json) {
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
      }
      return "X";
    }

    return recursivelyGetVariableNames(node.getChild(), (JSONObject) json.get(node.getNodeValue()));
  }

  private static NodeType determineNodeType(String value) {
    if (value.equals("null")) {
      return NodeType.NULL_NODE;
    } else if (value.equals("X")) {
      return NodeType.NON_NULL_NODE;
    } else if (NumericStringCheckerImpl.checkIfNumericReturnBoolean(value)) {
      return NodeType.NUMERIC_NODE;
    }
    return NodeType.STRING_NODE;
  }
}
