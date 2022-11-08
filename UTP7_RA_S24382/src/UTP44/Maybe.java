package UTP44;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<V> {
    private V value;

    public static <T> Maybe<T> of(T input) {
        return new Maybe<>(input);
    }

    public Maybe(V value) {
        this.value = value;
    }

    public Maybe() {
        this.value = null;
    }

    public void ifPresent(Consumer<V> consumer) {
        if (isPresent())
            consumer.accept(value);
    }

    public <R> Maybe<R> map(Function<V, R> function) {
        if (isPresent())
            return new Maybe(function.apply(value));
        return new Maybe<>();
    }

    public V get() {
        if (isPresent())
            return value;
        throw new NoSuchElementException("maybe is empty");
    }

    public boolean isPresent() {
        return value != null;
    }

    public V orElse(V defaultVal) {
        if (isPresent())
            return value;
        return defaultVal;
    }

    public Maybe<V> filter(Predicate<V> predicate) {
        if (isPresent() && predicate.test(value))
            return this;
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if (isPresent())
            return "Maybe has value " + value;
        else
            return "Maybe is empty";
    }
}
