package spell;

public class Trie implements ITrie {

    private Trie count;
    private int wordCount;
    private int nodeCount;
    private Node root;

    public Trie() {
        root = new Node();
    }

    @Override
    public void add(String word) {
        Node tempRoot = root;
        word = word.toLowerCase(); // Make the word lower case
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            int arrayIndex = currentChar - 'a';
            if (tempRoot.getChildren()[arrayIndex] == null) {         // If there is no Node @currentChar's index...
                tempRoot.getChildren()[arrayIndex] = new Node();      // Make a new node where the given index is
                tempRoot = (Node) tempRoot.getChildren()[arrayIndex]; // Set the root to this new location
                if (i == word.length() - 1) {                            // This means we've reached the end of a word
                    tempRoot.getChildren()[arrayIndex].incrementValue(); // Therefore, increment the node's countValue
                }
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