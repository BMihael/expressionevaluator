package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;
import leapwise.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;

import static leapwise.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_COMPARABLE_VALUES;

public class StringComparingStrategy implements ComparingStrategy {
  @Override
  public boolean comapre(Node n1, Node n2) {
    if ((n1.getNodeType() == NodeType.STRING_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE)
        || (n1.getNodeType() == NodeType.NUMERIC_NODE
            && n2.getNodeType() == NodeType.STRING_NODE)) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    return n1.getNodeValue().equals(n2.getNodeValue());
  }
}
