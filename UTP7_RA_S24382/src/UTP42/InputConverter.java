package UTP42;

import java.util.function.Function;

public class InputConverter<T> {
    private final T input;

    public InputConverter(T input) {
        this.input = input;
    }

    public <O> O convertBy(Function... functions) {
        Object input = this.input;
        Object result = null;
        for (Function func : functions) {
            result = func.apply(input);
            input = result;
        }
        return (O) result;
    }
}
