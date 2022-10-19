/**
 *
 *  @author Reut Anton S24382
 *
 */

package UTP21;


public interface Mapper<V> { // Uwaga: interfejs musi być sparametrtyzowany
    //V - generic value
    default V map (V value){
        return value;
    }
}
