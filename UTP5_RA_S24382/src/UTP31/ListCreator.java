/**
 * @author Reut Anton S24382
 */

package UTP31;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<E> { // Uwaga: klasa musi być sparametrtyzowana
    //E - generic element
    private List<E> mainList;

    //Should be static so I need second constructor
    public static <E> ListCreator<E> collectFrom(List<E> list) {
        return new ListCreator<>(list);
    }

    public ListCreator(List<E> list) {
        this.mainList = list;
    }

    public ListCreator<E> when(Predicate<E> predicate) {
        //List does not support remove method, so I had to create new List
        List<E> result = new ArrayList<>();
        if (mainList != null)
            for (E element : mainList)
                if (predicate.test(element))
                    result.add(element);
        mainList = result;
        return this;
    }

    public <R> List<R> mapEvery(Function<E,R> mapper) {
        List<R> result = new ArrayList<R>();
        if (mainList != null)
            for (E element : mainList)
                result.add(mapper.apply(element));
        return result; //this is final method that returns List<E>
    }
}  
