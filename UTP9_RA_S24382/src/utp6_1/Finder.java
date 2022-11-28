/**
 * @author Reut Anton S24382
 */

package utp6_1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    private String filepath;

    public Finder(String filepath) {
        this.filepath = filepath;
    }


    public int getIfCount() {
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            String text;
            boolean isCode = true;
            while ((text = bufferedReader.readLine()) != null) {
                text = text.trim();
                if (text.contains("/*"))
                    isCode = false;
                if (text.contains("*/") && !isCode)
                    if (text.contains("*/") && text.lastIndexOf("*/") > text.lastIndexOf("/*"))
                        isCode = true;
                if (isCode) {
                    text = text.replace(" ", "");
                    while (text.contains("if(")) {
                        if (text.contains("//")) {
                            if (text.indexOf("//") > text.indexOf("if("))
                                count++;
                        } else if (text.contains("\"")) {
                            if (countCharMatches('\"', text.substring(0, text.indexOf("if("))) % 2 == 0)
                                count++;
                        } else {
                            count++;
                        }
                        text = text.replaceFirst("if\\(", "");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getStringCount(String string) {
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            Pattern pattern = Pattern.compile(string);
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                text = text.trim();
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    text = text.replaceFirst(string, "");
                    count++;
                    matcher = pattern.matcher(text);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int countCharMatches(char symbol, String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == symbol)
                count++;
        return count;
    }
}