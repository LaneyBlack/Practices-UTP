package utp10_1.operations;

import java.math.BigDecimal;

public class Divide implements ICalculator {
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }
}
