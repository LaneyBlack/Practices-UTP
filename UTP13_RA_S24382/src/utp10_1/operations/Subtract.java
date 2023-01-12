package utp10_1.operations;

import java.math.BigDecimal;

public class Subtract implements ICalculator {
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}
