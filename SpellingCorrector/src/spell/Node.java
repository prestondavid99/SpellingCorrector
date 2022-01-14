package spell;

public class Node implements INode {

    private Node[] children;
    private int countValue;

    Node() {
        children = new Node[26]; // Each node has 26 children who are also nodes
        countValue = 0;          // How many times the node completes a word
    }

    @Override
    public int getValue() {
        return countValue;
    }

    @Override
    public void incrementValue() {
        countValue++;
    }

    @Override
    public INode[] getChildren() {
        return children;
    }
}
