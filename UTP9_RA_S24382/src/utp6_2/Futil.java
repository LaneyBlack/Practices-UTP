package utp6_2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String directory, String resultFileName) {
        try {
            Path start = Paths.get(directory);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), Charset.forName("UTF-8")));
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), Charset.forName("Cp1250")));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        bufferedWriter.write(text);
                        bufferedWriter.newLine();
                    }
                    bufferedReader.close();
                    return FileVisitResult.CONTINUE;
                }
            });
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
