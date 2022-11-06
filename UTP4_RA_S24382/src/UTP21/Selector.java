/**
 * @author Reut Anton S24382
 */

package UTP21;


public interface Selector<V> { // Uwaga: interfejs musi być sparametrtyzowany
    //V - generic value
    boolean select(V value);
}
