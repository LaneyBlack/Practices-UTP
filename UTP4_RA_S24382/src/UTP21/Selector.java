/**
 *
 *  @author Reut Anton S24382
 *
 */

package UTP21;


public interface Selector<V> { // Uwaga: interfejs musi być sparametrtyzowany
    //V - generic value
    default boolean select(V value){
        return true;
    }
}
