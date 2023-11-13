package expval.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals;

import expval.soft.expressionevaluator.structures.tree.nodes.Node;

public interface ComparingStrategy {
  boolean comapre(Node n1, Node n2);
}
