package UTP9_1;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class TravelData {

    private ArrayList<Travel> data;

    public TravelData(File directory) {
        data = new ArrayList();
        for (File file : directory.listFiles()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    String[] values = text.trim().split("\t");
                    String[] tmp = values[0].split("_");
                    Locale locale;
                    if (tmp.length > 1)
                        locale = new Locale(tmp[0], tmp[1]);
                    else
                        locale = new Locale(tmp[0]);
                    data.add(new Travel(locale, values[1], LocalDate.parse(values[2]), LocalDate.parse(values[3]),
                            values[4], NumberFormat.getInstance(locale).parse(values[5]), Currency.getInstance(values[6])));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        ArrayList<String> result = new ArrayList<>();
        for (Travel travel : data)
            result.add(travel.translate(new Locale(locale.split("_")[0], locale.split("_")[1]), dateFormat));
        return result;
    }

    public ArrayList<Travel> getData() {
        return data;
    }
}
