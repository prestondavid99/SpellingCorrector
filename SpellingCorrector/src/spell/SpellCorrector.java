package spell;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
            Set<String> possibleWords = new HashSet<>();
            Iterator<String> itr = possibleWords.iterator();

            possibleWords.addAll(deletionDistance(inputWord));
            possibleWords.addAll(transpositionDistance(inputWord));
            possibleWords.addAll(alterationDistance(inputWord));
            possibleWords.addAll(insertionDistance(inputWord));

            if (possibleWords.size() == 1) { // If there's only one item...
                for (String s : possibleWords) {
                    return s;
                }
            }
            else {
                int count = 0;
                String currString;
                TreeMap<Integer, String> possibilities = new TreeMap<Integer, String>();
                while (itr.hasNext()) {
                    int wordCount = trie.getCount(itr.next());
                    if (wordCount > count) {
                        count = wordCount;
                        currString = itr.next(); // TODO: MAYBE A PROBLEM
                        possibilities.put(wordCount, currString);
                    }
                }

                for (Map.Entry<Integer, String> entry :
                     possibilities.entrySet()) {
                    if (possibilities.containsKey(count)) {
                        return possibilities.get(count);
                    }
                }
            }

        }
        else {
            return inputWord;
        }
        return null;
    }

    public Set<String> deletionDistance(String inputWord) {
        Set<StringBuilder> deletionSet = new HashSet<>();
        Set<String> possibleWords = new HashSet<>();

        for (int i = 0; i < inputWord.length(); i++) {
            var currWord = new StringBuilder(inputWord);
            deletionSet.add(currWord.deleteCharAt(i));
        }

        for (StringBuilder sb :
             deletionSet) {
            if(trie.find(sb.toString()) != null) {
                possibleWords.add(sb.toString());
            }
        }
        return possibleWords;
    }

    public Set<String> transpositionDistance(String inputWord) {
        Set<StringBuilder> transpositionSet = new HashSet<>();
        Set<String> possibleWords = new HashSet<>();


        for (int i = 0; i < inputWord.length(); i++) {
            var currWord = new StringBuilder(inputWord);

            if (i != inputWord.length() - 1) {
                currWord = swap(inputWord, i, i + 1);
            }
            else {
                currWord = swap(inputWord, i, i - 1);
            }
            transpositionSet.add(currWord);
        }

        for (StringBuilder sb : transpositionSet) {
            if(trie.find(sb.toString()) != null) {
                possibleWords.add(sb.toString());
            }
        }
        return possibleWords;
    }

    public StringBuilder swap(String inputWord, int i, int j) {
        var sb = new StringBuilder(inputWord);
        sb.setCharAt(i, inputWord.charAt(j));
        sb.setCharAt(j, inputWord.charAt(i));

        return sb;
    }


    public Set<String> alterationDistance(String inputWord) {
        Set<StringBuilder> alterationSet = new HashSet<>();
        Set<String> possibleWords = new HashSet<>();



        for (int i = 0; i < inputWord.length(); i++) {
            for (int j = 0; j < 26; j++) {
                char currLetter = (char) ('a' + j);
                var currWord = new StringBuilder(inputWord);
                currWord.setCharAt(i, currLetter);
                alterationSet.add(currWord);
                }
            }

        for (StringBuilder sb : alterationSet) {
            if (trie.find(sb.toString()) != null) {
                possibleWords.add(sb.toString());
            }
        }
        return possibleWords;
    }

    public Set<String> insertionDistance(String inputWord) {
        Set<StringBuilder> insertionSet = new HashSet<>(); // Initialize Set
        Set<String> possibleWords = new HashSet<>();



        for (int i = 0; i < inputWord.length() + 1; i++) { // Char axis
            for (int j = 0; j < 26; j++) { // Alphabet Axis
                char currLetter = (char) ('a' + j);
                var currWord = new StringBuilder(inputWord);

                currWord.insert(i, currLetter);
                insertionSet.add(currWord);
            }
        }

        for (StringBuilder sb : insertionSet) {
            if(trie.find(sb.toString()) != null) {
                possibleWords.add(sb.toString());
            }
        }
        return possibleWords;
    }


}
