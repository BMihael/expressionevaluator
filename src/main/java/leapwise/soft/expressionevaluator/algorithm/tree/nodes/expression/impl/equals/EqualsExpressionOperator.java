package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.ExpressionNode;
import java.util.HashMap;
import java.util.Map;

import static leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType.*;

public class EqualsExpressionOperator extends ExpressionNode {

  static final Map<String, ComparingStrategy> nullStrategyMap = new HashMap<>();
  static final Map<String, ComparingStrategy> nonNullStrategyMap = new HashMap<>();

  static {
    nullStrategyMap.put(NULL_NODE.name().concat(NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NON_NULL_NODE.name().concat(NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NULL_NODE.name().concat(NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(
        NUMERIC_NODE.name().concat(NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(
        STRING_NODE.name().concat(NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(STRING_NODE.name().concat(NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NULL_NODE.name().concat(STRING_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NULL_NODE.name().concat(NUMERIC_NODE.name()), new NullComparingStrategy());
  }

  static {
    nonNullStrategyMap.put(
        STRING_NODE.name().concat(STRING_NODE.name()), new StringComparingStrategy());
    nonNullStrategyMap.put(
        STRING_NODE.name().concat(NUMERIC_NODE.name()), new StringComparingStrategy());
    nonNullStrategyMap.put(
        NUMERIC_NODE.name().concat(STRING_NODE.name()), new StringComparingStrategy());

    nonNullStrategyMap.put(
        NUMERIC_NODE.name().concat(NUMERIC_NODE.name()), new NumberComparingStrategy());
    nonNullStrategyMap.put(
        NUMERIC_NODE.name().concat(STRING_NODE.name()), new NumberComparingStrategy());
    nonNullStrategyMap.put(
        STRING_NODE.name().concat(NUMERIC_NODE.name()), new NumberComparingStrategy());
  }

  public EqualsExpressionOperator(String value, NodeType type) {
    super(value, type, 700);
  }

  public EqualsExpressionOperator(String value, NodeType type, int presedence) {
    super(value, type, presedence);
  }

  @Override
  public boolean makeResult(Node n1, Node n2) {
    String key = n1.getNodeType().name() + n2.getNodeType().name();
    ComparingStrategy strategy = nullStrategyMap.get(key);
    if (!(strategy == null)) {
      return strategy.comapre(n1, n2);
    }
    strategy = nonNullStrategyMap.get(key);
    if (!(strategy == null)) {
      return strategy.comapre(n1, n2);
    }

    return Boolean.parseBoolean(n1.getNodeValue()) == Boolean.parseBoolean(n2.getNodeValue());
  }
}
