package expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;
import expval.soft.expressionevaluator.structures.tree.nodes.NodeType;
import expval.soft.expressionevaluator.exception.algorithm.tree.NonComparableValuesException;

import static expval.soft.expressionevaluator.structures.helper.NumericStringCheckerImpl.areNumericEquals;
import static expval.soft.expressionevaluator.exception.algorithm.AlgorithmExceptionMessage.NON_COMPARABLE_VALUES;

public class NumberComparingStrategy implements ComparingStrategy {
  @Override
  public boolean comapre(Node n1, Node n2) {
    if ((n1.getNodeType() == NodeType.STRING_NODE && n2.getNodeType() == NodeType.NUMERIC_NODE)
        || (n1.getNodeType() == NodeType.NUMERIC_NODE
            && n2.getNodeType() == NodeType.STRING_NODE)) {
      throw new NonComparableValuesException(
          NON_COMPARABLE_VALUES, n1.getNodeValue(), n2.getNodeValue());
    }
    return areNumericEquals(n1, n2);
  }
}
