package spell;

public class Trie implements ITrie {

    private Trie count;
    private int wordCount;
    private int nodeCount;

    @Override
    public void add(String word) {
        var trie = new Trie();
        word.toLowerCase(); // Make the word lower case
        for (int i = 0; i < word.length(); i++) {
            /*TODO: if(trie is empty at letter) {
                insert letter?

             */

        }


    }

    @Override
    public INode find(String word) {
        return null;
    }

    @Override
    public int getWordCount() {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
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