package UTP42;

import java.util.function.Function;

public interface MyFunction<T,R> extends Function<T,R> {

    @Override
    default R apply(T t){
        try {
            return applyThrowing(t);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    R applyThrowing(T type) throws Exception;
}
