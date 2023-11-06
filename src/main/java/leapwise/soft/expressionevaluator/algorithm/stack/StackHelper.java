package leapwise.soft.expressionevaluator.algorithm.stack;

import leapwise.soft.expressionevaluator.algorithm.tree.EvaluationHelper;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.*;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.EqualsExpressionOperator;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.LogicalAndExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.NotEqualsExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.OrExpressionNode;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.string.impl.CleanStringNode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StackHelper {
  private static Stack<StackNodeWrapper> stack = null;

  public static void initStack() {
    stack = new Stack<>();
  }

  public static int getIndexOfParentFromExpression(String expression) {
    initStack();
    generateStackBaseOnExpression(expression);

    List<StackNodeWrapper> stackItems =
        stack.getStackArray().stream().filter(Objects::nonNull).collect(Collectors.toList());

    List<StackNodeWrapper> list =
        stackItems.stream()
            .filter(item -> item.getNode().getNodeType() != NodeType.STRING_NODE)
            .collect(Collectors.toList());

    StackNodeWrapper stackNodeWrapperWinner = list.get(0);
    Node nodeWinner = stackNodeWrapperWinner.getNode();

    for (StackNodeWrapper item : list) {
      Node innerNode = item.getNode();

      if (innerNode.getLevel() == nodeWinner.getLevel()
          && EvaluationHelper.hasHigherPresedance(
              (ExpressionNode) innerNode, (ExpressionNode) nodeWinner)) {
        nodeWinner = innerNode;
        stackNodeWrapperWinner = item;
      } else if (innerNode.getLevel() < nodeWinner.getLevel()) {
        nodeWinner = item.getNode();
        stackNodeWrapperWinner = item;
      }
    }
    return stackNodeWrapperWinner.getIndex();
  }

  public static void generateStackBaseOnExpression(String expression) {
    StackNodeWrapper stackNodeWrapper;
    for (int i = 0; i < expression.length(); i++) {
      Node node;
      char c = expression.charAt(i);

      // Gate keeper evaluation
      if (c != '(' && c != ')' && c != '=' && c != 'o' && c != 'O' && c != '&' && c != '!') {
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
      if (c == '=') {

        while (!stack.isEmpty()
            && ((StackNodeWrapper) stack.peek()).getNode().getNodeType()
                != NodeType.EXPRESSION_NODE) {
          stack.pop();
        }
        node = new EqualsExpressionOperator("==", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stackNodeWrapper = new StackNodeWrapper(node, i);
        stack.push(stackNodeWrapper);
        i = i + 1;
        continue;
      }
      if (c == 'o' || c == 'O') {
        char nextCharMustBeOr = expression.charAt(i + 1);
        if (!(nextCharMustBeOr == 'r' || nextCharMustBeOr == 'R')) {
          i = i + 1;
          continue;
        }

        while (!stack.isEmpty()
            && ((StackNodeWrapper) stack.peek()).getNode().getNodeType()
                != NodeType.EXPRESSION_NODE) {
          stack.pop();
        }
        node = new OrExpressionNode("or", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stackNodeWrapper = new StackNodeWrapper(node, i);
        stack.push(stackNodeWrapper);
        i = i + 1;
        continue;
      }
      if (c == '&') {
        char nextCharMustBeOr = expression.charAt(i + 1);
        if (!(nextCharMustBeOr == '&')) {
          i = i + 1;
          continue;
        }
        while (!stack.isEmpty()
            && ((StackNodeWrapper) stack.peek()).getNode().getNodeType()
                != NodeType.EXPRESSION_NODE) {
          stack.pop();
        }
        node = new LogicalAndExpressionNode("&&", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stackNodeWrapper = new StackNodeWrapper(node, i);
        stack.push(stackNodeWrapper);
        i = i + 1;
        continue;
      }
      if (c == '!') {
        char nextCharMustBeOr = expression.charAt(i + 1);
        if (!(nextCharMustBeOr == '=')) {
          i = i + 1;
          continue;
        }

        while (!stack.isEmpty()
            && ((StackNodeWrapper) stack.peek()).getNode().getNodeType()
                != NodeType.EXPRESSION_NODE) {
          stack.pop();
        }
        node = new NotEqualsExpressionNode("!=", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stackNodeWrapper = new StackNodeWrapper(node, i);
        stack.push(stackNodeWrapper);
        i = i + 1;
      }
    }
  }
}
