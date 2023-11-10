package leapwise.soft.expressionevaluator.structures.tree.nodes.expression.impl.equals;

import leapwise.soft.expressionevaluator.structures.tree.nodes.Node;

public interface ComparingStrategy {
  boolean comapre(Node n1, Node n2);
}
