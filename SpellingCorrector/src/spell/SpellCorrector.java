package spell;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector {
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        var file = new File(dictionaryFileName);
        var scanner = new Scanner(file);

        while(scanner.hasNext()) {
            String str = scanner.next();
            var trie = new Trie();
            trie.add(str);

            // If string has alreay been seen count++



        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        return null;
    }
}
