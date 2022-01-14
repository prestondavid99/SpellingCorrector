package spell;

public class Node implements INode {
    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void incrementValue() {

    }

    @Override
    public INode[] getChildren() { // TODO: Can / should we edit this?
        return new INode[0];
    }
}
