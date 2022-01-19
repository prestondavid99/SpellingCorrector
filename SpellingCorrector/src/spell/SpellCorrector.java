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
        inputWord = inputWord.toLowerCase();
        if (trie.find(inputWord) == null) { // If null, then the word has not been found
            Set<String> distanceOneWords = new HashSet<>();
            Set<String> distanceTwoWords = new HashSet<>();

            distanceOneWords.addAll(deletionDistance(inputWord));
            distanceOneWords.addAll(transpositionDistance(inputWord));
            distanceOneWords.addAll(alterationDistance(inputWord));
            distanceOneWords.addAll(insertionDistance(inputWord));

            for (String s : distanceOneWords) {
                distanceTwoWords.addAll(deletionDistance(s));
                distanceTwoWords.addAll(transpositionDistance(s));
                distanceTwoWords.addAll(alterationDistance(s));
                distanceTwoWords.addAll(insertionDistance(s));
            }

            distanceOneWords = findValidWords(distanceOneWords);
            distanceTwoWords = findValidWords(distanceTwoWords);

            if (distanceOneWords.size() == 1 || distanceTwoWords.size() == 1) { // If there's only one item...
                for (String s : distanceOneWords) {
                    return s;
                }
                for (String s : distanceTwoWords) {
                    return s;
                }
            }
            else {
                int count = 0;
                String currString;
                TreeMap<Integer, String> possibilities = new TreeMap<Integer, String>();
                for (String s : distanceOneWords) {
                    int wordCount = trie.getCount(s);
                    if (wordCount > count) {
                        count = wordCount;
                        currString = s;
                        possibilities.put(wordCount, currString);
                    }
                }

                for (Map.Entry<Integer, String> entry : possibilities.entrySet()) {
                    if (entry.getKey() == count) {
                        return entry.getValue();
                    }
                }

                int count2 = 0;
                String currString2;
                TreeMap<Integer, String> possibilities2 = new TreeMap<Integer, String>();
                for (String s : distanceTwoWords) {
                    int wordCount = trie.getCount(s);
                    if (wordCount > count2) {
                        count2 = wordCount;
                        currString2 = s;
                        possibilities2.put(wordCount, currString2);
                    }
                }

                for (Map.Entry<Integer, String> entry : possibilities2.entrySet()) {
                    if (entry.getKey() == count2) {
                        return entry.getValue();
                    }
                }
            }

        }
        else {
            return inputWord;
        }
        return null;
    }

    public Set<String> findValidWords(Set<String> inputWords) {
        Set<String> possibleWords = new HashSet<>();
        for (String s : inputWords) {
            if(trie.find(s) != null) {
                possibleWords.add(s);
            }
        }
        return possibleWords;
    }

    public Set<String> deletionDistance(String inputWord) {
        Set<String> deletionSet = new HashSet<>();

        for (int i = 0; i < inputWord.length(); i++) {
            var currWord = new StringBuilder(inputWord);
            deletionSet.add(currWord.deleteCharAt(i).toString());
        }


        return deletionSet;
    }

    public Set<String> transpositionDistance(String inputWord) {
        Set<String> transpositionSet = new HashSet<>();

        for (int i = 0; i < inputWord.length(); i++) {
            var currWord = new StringBuilder(inputWord);

            if (inputWord.length() == 1) {
                transpositionSet.add(currWord.toString());
                return transpositionSet;
            }

            if (i != inputWord.length() - 1) {
                currWord = swap(inputWord, i, i + 1);
            }
            else {
                currWord = swap(inputWord, i, i - 1);
            }
            transpositionSet.add(currWord.toString());
        }
        return transpositionSet;
    }

    public StringBuilder swap(String inputWord, int i, int j) {
        var sb = new StringBuilder(inputWord);
        sb.setCharAt(i, inputWord.charAt(j));
        sb.setCharAt(j, inputWord.charAt(i));

        return sb;
    }

    public Set<String> alterationDistance(String inputWord) {
        Set<String> alterationSet = new HashSet<>();

        for (int i = 0; i < inputWord.length(); i++) {
            for (int j = 0; j < 26; j++) {
                char currLetter = (char) ('a' + j);
                var currWord = new StringBuilder(inputWord);
                currWord.setCharAt(i, currLetter);
                alterationSet.add(currWord.toString());
                }
            }
        return alterationSet;
    }

    public Set<String> insertionDistance(String inputWord) {
        Set<String> insertionSet = new HashSet<>(); // Initialize Set

        for (int i = 0; i < inputWord.length() + 1; i++) { // Char axis
            for (int j = 0; j < 26; j++) { // Alphabet Axis
                char currLetter = (char) ('a' + j);
                var currWord = new StringBuilder(inputWord);

                currWord.insert(i, currLetter);
                insertionSet.add(currWord.toString());
            }
        }
        return insertionSet;
    }
}
