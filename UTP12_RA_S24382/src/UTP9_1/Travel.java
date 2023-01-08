package UTP9_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;

public class Travel {
    private Locale locale;
    private String country;
    private LocalDate dateOut;
    private LocalDate dateBack;
    private String place;
    private Number cost;
    private Currency currency;
    private Properties words;

    public Travel(Locale locale, String country, LocalDate dateOut, LocalDate dateBack, String place, Number cost, Currency currency) {
        this.locale = locale;
        this.country = country;
        this.dateOut = dateOut;
        this.dateBack = dateBack;
        this.place = place;
        this.cost = cost;
        this.currency = currency;
        try (InputStream file = new FileInputStream("words.properties")) {
            words = new Properties();
            words.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return locale + "\t" + country + "\t" + dateOut + "\t" + dateBack +
                "\t" + place + "\t" + cost + "\t" + currency;
    }

    public String translate(Locale outLang, String dateFormat){
        StringBuilder result = new StringBuilder();
        for (Locale l: Locale.getAvailableLocales())
            if (l.getDisplayCountry(locale).equals(country)) {
                result.append(l.getDisplayCountry(outLang));
                break;
            }
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(dateFormat);
        result.append("\t").append(dateOut.format(dtFormat)).append("\t").append(dateBack.format(dtFormat)).append("\t");
        result.append(words.getProperty(locale.getLanguage() + "-" + outLang.getLanguage() + "." + place, place));
        NumberFormat numberFormat = NumberFormat.getInstance(outLang);
        result.append("\t").append(numberFormat.format(cost)).append("\t").append(currency);
        return result.toString();
    }
}
