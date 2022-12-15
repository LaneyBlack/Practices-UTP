/**
 * @author Reut Anton S24382
 */

package utp8_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ArrayList<Towar> towary = new ArrayList<>();
        AtomicBoolean completed = new AtomicBoolean(false);
        Semaphore semA = new Semaphore(1);
        Semaphore semB = new Semaphore(1);
        Thread b = new Thread(() -> {
            double allMass = 0;
            int index = 0;
            do {
                try {
                    semB.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index >= 0 && index < towary.size()) {
                    allMass += towary.get(index++).getMass();
                    if (index % 100 == 0)
                        System.out.println("policzono wage " + index + " towarów");
                }
                semA.release();
            } while (!completed.get());
            System.out.println(allMass);
        });
        Thread a = new Thread(() -> {
            try {
                Scanner input = new Scanner(new File("UTP11_RA_S24382/src/utp8_1/test/test.txt"));
                String[] values;
                double mass;
                int id, count = 0;
                boolean first = true;
                while (input.hasNextLine()) {
                    try {
                        semA.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    values = input.nextLine().trim().split(" ");
                    id = Integer.parseInt(values[0]);
                    mass = Double.parseDouble(values[1]);
                    towary.add(new Towar(id, mass));
                    count++;
                    if (count % 200 == 0)
                        System.out.println("utworzono " + count + " obiektów");
                    semB.release();
                    if (first)
                        b.start();
                    first = false;
                }
                completed.set(true);
                semB.release();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        a.start();
        a.join();
        b.join();
    }
}
