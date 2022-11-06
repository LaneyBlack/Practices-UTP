/**
 *
 *  @author Reut Anton S24382
 *
 */

package UTP21;


public interface Mapper<V,R> { // Uwaga: interfejs musi być sparametrtyzowany
    //V - generic value
    default R map (V value){
        return (R) value;
    }
}
