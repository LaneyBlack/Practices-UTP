/**
 * @author Reut Anton S24382
 */

package UTP21;


import java.util.ArrayList;
import java.util.List;

public class ListCreator1<E> { // Uwaga: klasa musi być sparametrtyzowana
    //E - generic element
    private List<E> mainList;

    //Should be static so I need second constructor
    public static <E> ListCreator1<E> collectFrom(List<E> list) {
        return new ListCreator1<>(list);
    }

    public ListCreator1(List<E> list) {
        this.mainList = list;
    }

    public ListCreator1<E> when(Selector<E> selector) {
        //List does not support remove method, so I had to create new List
        List<E> result = new ArrayList<>();
        if (mainList!=null)
            for (E element : mainList)
                if (selector.select(element))
                    result.add(element);
        mainList = result;
        return this;
    }

    public <R> List<R> mapEvery(Mapper<E, R> mapper) {
        List<R> result = new ArrayList<R>();
        if (mainList!=null)
            for (E element : mainList)
                result.add(mapper.map(element));
        return result; //this is final method that returns List<E>
    }
}  
