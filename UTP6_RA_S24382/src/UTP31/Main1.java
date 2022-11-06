/**
 *
 *  @author Reut Anton S24382
 *
 */

package UTP31;

import java.util.stream.Collectors;
import java.util.*; /*<-- niezbędne importy */


public class Main1 {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream().filter(element->element.startsWith("WAW")).map(element->{
              String[] values = element.split(" ",3);
              int price = Integer.parseInt(values[2]);
              return "to "+values[1]+" - "+"price in PLN: " + (int)(price*ratePLNvsEUR);
            }).collect(Collectors.toList() /*<-- lambda wyrażenie
             *  wyliczenie ceny przelotu w PLN
             *  i stworzenie wynikowego napisu
             */
    );
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */

    for (String r : result) System.out.println(r);
  }
}
