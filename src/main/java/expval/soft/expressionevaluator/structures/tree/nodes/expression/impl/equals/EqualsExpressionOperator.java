package expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;
import expval.soft.expressionevaluator.structures.tree.nodes.expression.ExpressionNode;

import java.util.HashMap;
import java.util.Map;

public class EqualsExpressionOperator extends ExpressionNode {

  static final Map<String, ComparingStrategy> nullStrategyMap = new HashMap<>();
  static final Map<String, ComparingStrategy> nonNullStrategyMap = new HashMap<>();

  static {
    nullStrategyMap.put(NodeType.NULL_NODE.name().concat(NodeType.NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NodeType.NON_NULL_NODE.name().concat(NodeType.NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NodeType.NULL_NODE.name().concat(NodeType.NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(
        NodeType.NUMERIC_NODE.name().concat(NodeType.NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(
        NodeType.STRING_NODE.name().concat(NodeType.NON_NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NodeType.STRING_NODE.name().concat(NodeType.NULL_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NodeType.NULL_NODE.name().concat(NodeType.STRING_NODE.name()), new NullComparingStrategy());
    nullStrategyMap.put(NodeType.NULL_NODE.name().concat(NodeType.NUMERIC_NODE.name()), new NullComparingStrategy());
  }

  static {
    nonNullStrategyMap.put(
        NodeType.STRING_NODE.name().concat(NodeType.STRING_NODE.name()), new StringComparingStrategy());
    nonNullStrategyMap.put(
        NodeType.STRING_NODE.name().concat(NodeType.NUMERIC_NODE.name()), new StringComparingStrategy());
    nonNullStrategyMap.put(
        NodeType.NUMERIC_NODE.name().concat(NodeType.STRING_NODE.name()), new StringComparingStrategy());

    nonNullStrategyMap.put(
        NodeType.NUMERIC_NODE.name().concat(NodeType.NUMERIC_NODE.name()), new NumberComparingStrategy());
    nonNullStrategyMap.put(
        NodeType.NUMERIC_NODE.name().concat(NodeType.STRING_NODE.name()), new NumberComparingStrategy());
    nonNullStrategyMap.put(
        NodeType.STRING_NODE.name().concat(NodeType.NUMERIC_NODE.name()), new NumberComparingStrategy());
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
