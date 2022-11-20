package utp5_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    private Map<String, LinkedHashSet<Character>> languages;
    private Map<Character, LinkedHashSet<String>> emps;

    public ProgLang(String filePath) throws IOException {
        languages = new LinkedHashMap<>();
        emps = new LinkedHashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String text;
        while ((text = bufferedReader.readLine()) != null) {
            String[] values = text.trim().split("\t");
            //value[0] is the language, other are emp initials
            LinkedHashSet<Character> names = new LinkedHashSet<>();
            for (int i = 1; i < values.length; i++) {
                names.add(values[i].charAt(0));
                if (!emps.containsKey(values[i].charAt(0)))
                    emps.put(values[i].charAt(0), new LinkedHashSet<>());
                emps.get(values[i].charAt(0)).add(values[0]);
            }
            languages.put(values[0], names);
        }
        bufferedReader.close();
    }

    public Map<String, LinkedHashSet<Character>> getLangsMap() {
        return languages;
    }

    public Map<Character, LinkedHashSet<String>> getProgsMap() {
        return emps;
    }

    public Map<String, LinkedHashSet> getLangsMapSortedByNumOfProgs() {
//        LinkedHashMap<String, LinkedHashSet> tmp = new LinkedHashMap<>(languages);
//        LinkedHashMap<String, LinkedHashSet> result = new LinkedHashMap<>();
//        tmp.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue((l1, l2) -> l2.size() - l1.size()))
//                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
//        return result;
        return sort(languages, (l1, l2) -> l2.getValue().size() - l1.getValue().size());
    }

    public Map<Character, LinkedHashSet> getProgsMapSortedByNumOfLangs() {
        return sort(emps, (l1, l2) -> l2.getValue().size() - l1.getValue().size());
    }

    public Map<Character, LinkedHashSet> getProgsMapForNumOfLangsGreaterThan(int i) {
        return filter(emps, (l1) -> l1.getValue().size() > i);
    }

    public <K, V> Map<K, LinkedHashSet> sort(Map<K, LinkedHashSet<V>> map, Comparator<Map.Entry<K, LinkedHashSet<V>>> comparator) {
        LinkedList<Map.Entry<K, LinkedHashSet<V>>> elements = new LinkedList<>(map.entrySet());
        Collections.sort(elements, comparator);
        Map<K, LinkedHashSet> result = new LinkedHashMap<>();
        for (Map.Entry<K, LinkedHashSet<V>> element : elements)
            result.put(element.getKey(), element.getValue());
        return result;
    }

    public <K, V> Map<K, LinkedHashSet> filter(Map<K, LinkedHashSet<V>> map, Predicate<Map.Entry<K, LinkedHashSet<V>>> predicate) {
        LinkedList<Map.Entry<K, LinkedHashSet<V>>> elements = new LinkedList<>(map.entrySet());
        Map<K, LinkedHashSet> result = new LinkedHashMap<>();
        for (Map.Entry<K, LinkedHashSet<V>> element : elements)
            if (predicate.test(element))
                result.put(element.getKey(), element.getValue());
        return result;
    }
}
