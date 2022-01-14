package spell;

public class Trie implements ITrie {

    private Trie count;
    private int wordCount;
    private int nodeCount;
    private Node child;


    @Override
    public void add(String word) {
        word.toLowerCase(); // Make the word lower case
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (child.getChildren()[currentChar - 'a'] == null) {    // If there is no Node @currentChar's index...
                child.getChildren()[currentChar - 'a'] = new Node(); // Make a new node where the given index is


            }


        }
    }
    @Override
    public INode find(String word) {
        return null;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    public String toString() {
        return "";
    }

    public String toStringHelper(Node n) {
        if (n.getValue() > 0) { // If the Node's count is greater than 0
            // Append the node's word
        }

        for (int i = 0; i < 26; i++) {
            Node child = (Node) n.getChildren()[i]; // at i ?
        }
        return "";
    }
}