package utp10_1.operations;

import java.math.BigDecimal;

public class Multiply implements ICalculator {
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }
}
