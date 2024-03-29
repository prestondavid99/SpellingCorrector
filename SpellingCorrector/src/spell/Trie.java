package spell;

public class Trie implements ITrie {



    private Trie count;
    private int wordCount;
    private int nodeCount;
    private Node root;

    public Trie() {
        root = new Node();
        nodeCount = 1;
    }

    @Override
    public void add(String word) {
        Node tempRoot = root;
        word = word.toLowerCase(); // Make the word lower case
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            int arrayIndex = currentChar - 'a';

            if (tempRoot.getChildren()[arrayIndex] == null) {         // If there is no Node @currentChar's index...
                tempRoot = (Node) (tempRoot.getChildren()[arrayIndex] = new Node()); // Make a new node where the given index is
                ++nodeCount;
            } else {
                tempRoot = (Node) tempRoot.getChildren()[arrayIndex]; // Otherwise, we'll traverse the tree anyway
            }

            if (i == word.length() - 1) {  // This means we've reached the end of a word
                if(tempRoot.getValue() == 0) {
                    wordCount++;
                }
                tempRoot.incrementValue(); // Therefore, increment the node's countValue
            }
        }
    }



    @Override
    public INode find(String word) {
        Node currRoot = root;
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            int arrayIndex = currentChar - 'a';

            if (currRoot.getChildren()[arrayIndex] != null) {
                currRoot = (Node) currRoot.getChildren()[arrayIndex];
                if ((currRoot.getValue() == 0) && (i == word.length() - 1)) { // If the currRoot is not a word after reaching the end of the loop
                    return null;
                }
            }
            else {
                return null;
            }
        }
        return currRoot;
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
        var currWord = new StringBuilder();
        var output = new StringBuilder();

        toStringHelper(root, currWord, output);

        return output.toString();
    }

    public void toStringHelper(Node n, StringBuilder currWord, StringBuilder output) {
        if (n.getValue() > 0) { // If the Node's count is greater than 0, there is a word
            output.append(currWord.toString());
            output.append("\n");
        }

        for (int i = 0; i < n.getChildren().length; i++) {
            Node child = (Node) n.getChildren()[i];
            if (child != null) {
                char childChar = (char)('a' + i);
                currWord.append(childChar);
                toStringHelper(child, currWord, output);
                currWord.deleteCharAt(currWord.length() - 1);
            }
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Trie t = (Trie)o; // This makes typeCasts "o" to a Trie called t

        if ((t.getWordCount() == this.getWordCount()) && (t.getNodeCount() == this.getNodeCount())) {
            return equalsHelper(t.root, this.root);
        }
        return false;
    }

    public boolean equalsHelper(INode n1, INode n2) {

        if (n1.getValue() != n2.getValue()) { // Compare their count values
            return false;
        }

        for (int i = 0; i < n1.getChildren().length; i++) { // Do children all have the same null/non-null children in the same spots?
            if ((n1.getChildren()[i] == null) && (n2.getChildren()[i] == null)) { // If both are null
                continue;
            } else if ((n1.getChildren()[i] == null) || (n2.getChildren()[i] == null)) { // Otherwise if one of them is null and the other is not
                return false;
            } else {
                boolean returnState = equalsHelper(n1.getChildren()[i], n2.getChildren()[i]);
                if(returnState == false) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashInt = 31;
        int temp = 0;
        for (int i = 0; i < root.getChildren().length; i++) {
            if (root.getChildren()[i] != null) {
                temp = temp + i;
            }
        }
        return (this.getWordCount() * this.getNodeCount() * hashInt) + temp;
    }

    public int getCount(String word) {
        return find(word).getValue();
    }
}

