package leapwise.soft.expressionevaluator.algorithm.tree;

import leapwise.soft.expressionevaluator.algorithm.tree.nodes.Node;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

public class TreeProvider {
    static Tree tree = null;

    public static void generateTree() {
        tree = new Tree();
        tree.add("");
    }

    public static void provideExpression(String expression) {
        generateTree();
        tree.add(expression);

        String g = null;
    }

    public static String printResult() {
        if(tree == null){
            throw new RuntimeException("Nije generirano stablo");
        }
        Node node =  tree.printInorder(tree.root);
        tree = null;
        return node.getNodeValue();
    }

    public static String fillTreeHelper(JSONObject jsonInput) {
        if(tree == null){
            throw new RuntimeException("Nije generirano stablo");
        }
          tree.fillTree(tree.root, jsonInput);

        return null;
    }
}
