/**
 * @author Reut Anton S24382
 */

package utp10_1;


import utp10_1.operations.*;

import java.math.BigDecimal;
import java.util.HashMap;

public class Calc {
    HashMap<Character, ICalculator> operations = new HashMap<>();

    public Calc() {
        operations.put('*', new Multiply());
        operations.put('/', new Divide());
        operations.put('-', new Subtract());
        operations.put('+', new Summary());
    }

    public String doCalc(String input) {
        String[] values = input.trim().split("\\s+", 3);
        return operations.get(values[1].charAt(0)).calculate(new BigDecimal(values[0]), new BigDecimal(values[2])).toString();
    }
}  
