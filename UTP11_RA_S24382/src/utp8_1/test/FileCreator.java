package utp8_1.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("UTP11_RA_S24382/src/utp8_1/test/test.txt"));
        double mass = 0;
        for (int i = 0; i < 12_000; i++) {
            mass = (Math.round((Math.random() * 1000)));
            mass/=100;
            writer.write(i + " " + mass +"\n");
        }
        writer.close();
        System.out.println("Writing done");
    }
}
