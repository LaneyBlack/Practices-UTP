/**
 * @author Reut Anton S24382
 */

package utp5_1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Anagrams {
    private List<List<String>> anagrams;

    public Anagrams(String wordsFilePath) {
        anagrams = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(wordsFilePath));
            while (scanner.hasNext()) {
                String word = scanner.next();
                boolean isNewAnagram = true;
                if (!anagrams.isEmpty())
                    for (List<String> anagram : anagrams) {
                        if (isAnagram(anagram.get(0), word)) {
                            anagram.add(word);
                            isNewAnagram = false;
                            break;
                        }
                    }

                if (isNewAnagram) {
                    ArrayList<String> newAnagram = new ArrayList<>();
                    newAnagram.add(word);
                    anagrams.add(newAnagram);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> getSortedByAnQty() {
        Collections.sort(anagrams, (l1, l2) -> l2.size() - l1.size());
        return anagrams;
    }

    public String getAnagramsFor(String word) {
        StringBuilder result = new StringBuilder();
        result.append(word + ": ");
        for (List<String> list : anagrams) {
            if (isAnagram(word, list.get(0))) {
                ArrayList tmp = new ArrayList(list);
                tmp.remove(word);
                result.append(tmp);
                return result.toString();
            }
        }
        result.append(" []");
        return result.toString();
    }

    public boolean isAnagram(String s1, String s2) {
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++)
                if (s2.indexOf(s1.charAt(i)) < 0)
                    return false;
                else
                    s2 = s2.replaceFirst(s1.charAt(i) + "", "");
            //remove the seen letter, ex: stretts - streets
        } else
            return false;
        return true;
    }
}
