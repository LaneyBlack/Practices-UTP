/**
 *
 *  @author Reut Anton S24382
 *
 */

package utp10_1;


public class Main {

  public static void main(String[] args) {
    Calc c = new Calc();
    String wynik = c.doCalc(args[0]);
    System.out.println(wynik);
  }
}
