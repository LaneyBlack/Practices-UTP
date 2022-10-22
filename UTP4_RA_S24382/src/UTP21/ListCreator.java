/**
 * @author Reut Anton S24382
 */

package UTP21;


import java.util.ArrayList;
import java.util.List;

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

    public ListCreator<E> when(Selector<E> selector) {
        //List does not support remove method, so I had to create new List
        List<E> result = new ArrayList<>();
        for (E element : mainList)
            if (selector.select(element))
                result.add(element);
        mainList = result;
        return this;
    }

    public <R> List<R> mapEvery(Mapper<E, R> mapper) {
        List<R> result = new ArrayList<R>();
        if (!mainList.isEmpty())
            for (E element : mainList)
                result.add(mapper.map(element));
        return result; //this is final method that returns List<E>
    }
}  