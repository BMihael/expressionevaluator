package leapwise.soft.expressionevaluator.algorithm.tree.nodes.expression;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import leapwise.soft.expressionevaluator.algorithm.tree.nodes.NodeType;

public abstract class ExpressionNode extends Node {
    private final int presedance;
    public ExpressionNode(String item, NodeType type, int presedance){
        super(item, type);
        this.presedance = presedance;
    }

    public abstract boolean makeResult(Node n1, Node n2);

    public int getPresedance(){
        return this.presedance;
    }
}
