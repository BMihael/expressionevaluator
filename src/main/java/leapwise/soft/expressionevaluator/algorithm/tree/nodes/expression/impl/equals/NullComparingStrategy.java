package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.exception.CriticalException;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.CRITICAL_EXCEPTION_MESSAGE;
import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_COMPARABLE_VALUES;

public class NullComparingStrategy implements ComparingStrategy {
  @Override
  public boolean comapre(Node n1, Node n2) {
    if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NULL_NODE) {
      return true;
    }
    if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
      return false;
    }
    if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE) {
      return false;
    }
    if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.STRING_NODE) {
      return n2.getNodeValue() == null;
    }
    if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.STRING_NODE) {
      return n1.getNodeValue() == null;
    }
    if (n1.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    if (n2.getNodeType() == NodeType.NUMERIC_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n2.getNodeValue(), n1.getNodeValue());
    }
    if (n1.getNodeType() == NodeType.STRING_NODE && n2.getNodeType() == NodeType.NON_NULL_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    if (n2.getNodeType() == NodeType.STRING_NODE && n1.getNodeType() == NodeType.NON_NULL_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n2.getNodeValue(), n1.getNodeValue());
    }
    if (n1.getNodeType() == NodeType.NULL_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    if (n2.getNodeType() == NodeType.NULL_NODE && n1.getNodeType() == NodeType.NUMERIC_NODE) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n2.getNodeValue(), n1.getNodeValue());
    }
    throw new CriticalException(CRITICAL_EXCEPTION_MESSAGE);
  }
}
