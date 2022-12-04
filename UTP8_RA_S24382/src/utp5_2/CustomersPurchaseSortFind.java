/**
 * @author Reut Anton S24382
 */

package utp5_2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CustomersPurchaseSortFind {
    ArrayList<Purchase> customers;

    public void readFile(String fileName) {
        customers = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                String[] values = text.trim().split(";");
                customers.add(new Purchase(values[0], values[1], values[2],
                        Double.valueOf(values[3]), Double.valueOf(values[4])));
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showSortedBy(String sortCategory) {
        System.out.println(sortCategory);
        sortCategory = sortCategory.trim().toUpperCase(Locale.ROOT);
        switch (sortCategory) {
            case "ID":
                Collections.sort(customers, Comparator.comparing(Purchase::getId));
                break;
            case "NAZWISKA":
                Collections.sort(customers, Comparator.comparing(Purchase::getName));
                break;
            case "KOSZTY":
                Collections.sort(customers, (p1, p2) -> (int) (p2.getCost() * p2.getCount() - p1.getCost() * p1.getCount()));
                break;
            case "PRODUKTY":
                Collections.sort(customers, Comparator.comparing(Purchase::getProduct));
                break;
            case "ILOSCI":
                Collections.sort(customers, Comparator.comparingDouble(Purchase::getCount));
                break;
        }
        customers.forEach(System.out::println);
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (Purchase purchase : customers) {
            if (purchase.getId().equals(id))
                System.out.println(purchase);
        }
        System.out.println();
    }
}
