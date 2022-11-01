/**
 * @author Reut Anton S24382
 */

package UTP42;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*<-- niezbędne import */
public class Main {
    public static void main(String[] args) throws IOException {
        Function<String, List<String>> flines = (filePath) -> {
            List<String> result = new ArrayList<>();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                String text;
                while ((text = bufferedReader.readLine()) != null)
                    result.add(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result;
        };
        Function<List<String>, String> join = (list) -> {
            StringBuilder result = new StringBuilder();
            for (String element : list)
                result.append(element);
            return result.toString();
        };
        Function<String, List<Integer>> collectInts = (input) -> {
            List<Integer> result = new ArrayList<>();
            StringBuilder number = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char symbol = input.charAt(i);
                if (symbol <= '9' && symbol >= '0') {
                    number.append(symbol);
                } else if (!number.toString().isEmpty()) {
                    result.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
            }
            if (!number.toString().isEmpty())
                result.add(Integer.parseInt(number.toString()));
            return result;
        };
        Function<List<Integer>, Integer> sum = (list) -> {
            int result = 0;
            for (int value : list)
                result += value;
            return result;
        };
        /*<--
         *  definicja operacji w postaci lambda-wyrażeń:
         *  - flines - zwraca listę wierszy z pliku tekstowego
         *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
         *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         *  - sum - zwraca sumę elmentów listy liczb całkowitych
         */

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);

        // Zadania badawcze:
        // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
        // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
        // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
        // I wobec tego musimy pisać w definicji flines try { } catch { }
        // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
        // zadziałała klauzula throws metody main
    }
}
