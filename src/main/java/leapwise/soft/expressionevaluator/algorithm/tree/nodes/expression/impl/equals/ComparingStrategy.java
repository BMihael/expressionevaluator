package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;

public interface ComparingStrategy {
  boolean comapre(Node n1, Node n2);
}
