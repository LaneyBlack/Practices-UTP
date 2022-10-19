/**
 * @author Reut Anton S24382
 */

package UTP21;

import java.util.*;

import static UTP21.ListCreator.collectFrom;

public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(54, 62, 44, 11, 26, 48, 32, 21);/*<-- tu dopisać inicjację elementów listy */
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("Richard", "Alex", "Patrick", "Adrian", "Poly", "Anton"); /*<-- tu dopisać inicjację elementów listy */
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = new Selector<>() {
            @Override
            public boolean select(Integer value) {
                return value > 30;
            }
        }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
        Mapper<Integer> map = new Mapper<>() {
            @Override
            public Integer map(Integer value) {
                return value + 14;
            }
        };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
        return collectFrom(src).when(sel).mapEvery(map);  /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */
    }

    public List<String> test2(List<String> src) {
        Selector<String> sel = new Selector<>() {
            @Override
            public boolean select(String value) {
                return value.toLowerCase().startsWith("a");
            }
        }; /*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
        Mapper<String> map = new Mapper<>() {
            @Override
            public String map(String value) {
                return value.toUpperCase();
            }
        };   /*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
        return collectFrom(src).when(sel).mapEvery(map); /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */
    }

    public static void main(String[] args) {
        new Main();
    }
}
