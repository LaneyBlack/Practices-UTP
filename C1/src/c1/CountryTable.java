package c1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountryTable {
    private String sourceFile;
    private String[] columns;
    private String[][] data;
    public CountryTable(String countriesFileName) {
        sourceFile = countriesFileName;
    }
//    public Class <?>
    public JTable create() {
        JTable table = new JTable();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
            data = new String[getDataSize()][];
            String text;
            int index = 0;
            bufferedReader.mark(0);
            bufferedReader.reset();
            while ((text = bufferedReader.readLine()) != null) {
                String[] values = text.trim().split("\t");
                if (index==0){
                } else {
                    data[index] = values;
                }
                index++;
//                countries.add(new Country(values[0], values[1], Integer.parseInt(values[2]), new ImageIcon("flags" + values[3] + ".png")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JTable(data,columns);
    }

    public int getDataSize() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
        int countriesCount = 0;
        while(bufferedReader.readLine()!=null)
            countriesCount++;
        bufferedReader.close();
        return countriesCount;
    }
}
