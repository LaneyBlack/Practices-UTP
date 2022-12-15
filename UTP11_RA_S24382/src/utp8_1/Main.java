/**
 * @author Reut Anton S24382
 */

package utp8_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {
    private static Thread a;
    private static final Object monitorA = new Object();
    private static Thread b;
    static Boolean completed = false;
    private static final Object monitorB = new Object();

    public static void main(String[] args) throws InterruptedException {
        final ArrayList<Towar> towary = new ArrayList<>();
        a = new Thread(() -> {
            try {
                Scanner input = new Scanner(new File("UTP11_RA_S24382/src/utp8_1/test/test.txt"));
                String[] values;
                double mass;
                int id, count = 0;
                boolean first = true;
                while (input.hasNextLine()) {
                    System.out.println("SizeA-1 " + towary.size());
                    if (!first)
                        synchronized (monitorA) {
                            try {
                                monitorA.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    values = input.nextLine().split(" ");
                    id = Integer.parseInt(values[0]);
                    mass = Double.parseDouble(values[1]);
                    towary.add(new Towar(id, mass));
                    count++;
                    if (count % 200 == 0)
                        System.out.println("utworzono " + count + " obiektów");
                    System.out.println("SizeA-2 " + towary.size());
                    synchronized (monitorB) {
                        monitorB.notify();
                    }
                    first = false;
                }
                completed = true;
                System.out.println("A - exit");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        b = new Thread(() -> {
            double allMass = 0;
            int index = 0;

            while (!completed) {
                System.out.println("SizeB-1 " + towary.size());
                synchronized (monitorB) {
                    try {
                        monitorB.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(index < towary.size()) {
                    System.out.println("SizeB-2 " + towary.size());
                    allMass += towary.get(index).getMass();
                    if (index % 100 == 0) {
                        System.out.println("policzono wage " + index + " towarów");
                    }
                    if (!towary.isEmpty())
                        index++;
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (monitorA) {
                    monitorA.notify();
                }
            }
            System.out.println("OverAll mass is " + allMass);
        });
        //a.start();
        b.start();
        a.start();
        //b.start();
//        synchronized (monitorA) {
//            monitorA.notify();
//        }
//        synchronized (monitorB) {
//            monitorB.notify();
//        }
        a.join();
        b.join();
        System.out.println("Main end");
    }
}
