/**
 *
 *  @author Reut Anton S24382
 *
 */

package utp5_2;


public class Purchase {
    private String id;
    private String name;
    private String product;
    private double cost;
    private double count;

    public Purchase(String id, String name, String product, double cost, double count) {
        this.id = id;
        this.name = name;
        this.product = product;
        this.cost = cost;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    public double getCount() {
        return count;
    }

    @Override
    public String toString() {
        return  id + ';' + name + ';' + product + ';' + cost + ';' + count;
    }
}
