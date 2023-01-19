/**
 * @author Reut Anton S24382
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Set<String>> map = new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream())).lines() //getting lines from internet
                    .collect(Collectors.groupingBy( //group by key, him being alphabetical symbols of the word
                            word -> Arrays.stream(word.split("")).sorted().collect(Collectors.joining()), //making key for map being word symbols sorted by alphabet
                            Collectors.toSet())); //collect all matches into set
            int maxRep = map.values().stream().max(Comparator.comparing(Set::size)).get().size(); //get maximum repetitions
            map.values().stream().filter(el -> el.size() == maxRep).forEach(set -> {    //filter all anagrams by maximum repetitions
                set.forEach(el -> System.out.print(el + " "));  //print them out
                System.out.println();
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
