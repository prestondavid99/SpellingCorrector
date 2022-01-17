package spell;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    Trie trie = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        var file = new File(dictionaryFileName);
        var scanner = new Scanner(file);

        while(scanner.hasNext()) {
            String str = scanner.next();
            trie.add(str);





        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        if (trie.find(inputWord) == null) { // If null, then the word has not been found

        }
        return null;
    }
}
