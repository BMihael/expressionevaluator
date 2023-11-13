package expval.soft.expressionevaluator.structures.stack;

import expval.soft.expressionevaluator.structures.tree.EvaluationHelper;
import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.GraterThanExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.LessThanExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.LogicalAndExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.OrExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals.EqualsExpressionOperator;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals.NotEqualsExpressionNode;
import expval.soft.expressionevaluator.structures.tree.nodes.string.impl.CleanStringNode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StackHelper {
  private static Stack<StackNodeWrapper> stack = null;

  public static int getIndexOfParentExpression(String expression) {
    initStack();
    generateStackBasedOnExpression(expression);

    List<StackNodeWrapper> stackItems =
        stack.getStackArray().stream().filter(Objects::nonNull).collect(Collectors.toList());

    List<StackNodeWrapper> filteredOperatorsOnStack =
        stackItems.stream()
            .filter(item -> item.getNode().getNodeType() != NodeType.STRING_NODE)
            .collect(Collectors.toList());

    return findWinnerNode(filteredOperatorsOnStack).getIndex();
  }

  public static void initStack() {
    stack = new Stack<>();
  }

  private static void generateStackBasedOnExpression(String expression) {
    for (int index = 0; index < expression.length(); index++) {
      char character = expression.charAt(index);

      // Gate keeper evaluation
      if (character != '('
          && character != ')'
          && character != '='
          && character != 'o'
          && character != 'O'
          && character != '&'
          && character != '>'
          && character != '<'
          && character != '!') {
        continue;
      }

      if (character == '(') {
        Node node = new CleanStringNode("(", NodeType.STRING_NODE);
        stack.push(new StackNodeWrapper(node, index));
      } else if (character == ')') {
        Node node = new CleanStringNode(")", NodeType.STRING_NODE);
        stack.push(new StackNodeWrapper(node, index));
      } else if (character == '=') {
        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new EqualsExpressionOperator("==", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
        index = index + 1;
      } else if (character == 'o' || character == 'O') {
        if (!(isNextCharacter(expression.charAt(index + 2), ' '))) {
          continue;
        }
        if (!(isNextCharacter(expression.charAt(index + 1), 'r')
            || isNextCharacter(expression.charAt(index + 1), 'R'))) {
          index = index + 1;
          continue;
        }

        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new OrExpressionNode("or", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
        index = index + 1;
      } else if (character == '&') {
        char nextCharMustBeOr = expression.charAt(index + 1);
        if (!(nextCharMustBeOr == '&')) {
          index = index + 1;
          continue;
        }

        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new LogicalAndExpressionNode("&&", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
        index = index + 1;
      } else if (character == '!') {
        char nextCharMustBeOr = expression.charAt(index + 1);
        if (!(nextCharMustBeOr == '=')) {
          index = index + 1;
          continue;
        }

        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new NotEqualsExpressionNode("!=", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
        index = index + 1;
      } else if (character == '>') {
        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new GraterThanExpressionNode(">", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
      } else if (character == '<') {
        emptyStackTillItselfIsEmptyOrExpressionIsEncountered();
        Node node = new LessThanExpressionNode("<", NodeType.EXPRESSION_NODE);
        node.setLevel(StackNodeWrapper.level);
        stack.push(new StackNodeWrapper(node, index));
      }
    }
  }

  private static StackNodeWrapper findWinnerNode(List<StackNodeWrapper> filteredOperatorsOnStack) {
    StackNodeWrapper stackNodeWrapperWinner = filteredOperatorsOnStack.get(0);
    Node nodeWinner = stackNodeWrapperWinner.getNode();

    for (StackNodeWrapper item : filteredOperatorsOnStack) {
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
    return stackNodeWrapperWinner;
  }

  private static void emptyStackTillItselfIsEmptyOrExpressionIsEncountered() {
    while (!stack.isEmpty()
        && ((StackNodeWrapper) stack.peek()).getNode().getNodeType() != NodeType.EXPRESSION_NODE) {
      stack.pop();
    }
  }

  private static boolean isNextCharacter(char current, char next) {
    return current == next;
  }
}
