package spell;

public class Node implements INode {

    private Node[] children;
    private int countValue = 0;

    Node() {
        children = new Node[26]; // Each node has 26 children who are also nodes
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
