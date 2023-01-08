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

    public String[] toParts() {
        return new String[]{locale.toString(), country, dateOut.toString(), dateBack.toString(),
                place, cost.toString(), currency.toString()};
    }

    public String[] toParts(Locale outLang) {
        String[] result = new String[]{locale.toString(), country, dateOut.toString(), dateBack.toString(),
                place, cost.toString(), currency.toString()};
        for (Locale l : Locale.getAvailableLocales())
            if (l.getDisplayCountry(locale).equals(country)) {
                result[1] = l.getDisplayCountry(outLang);
                break;
            }
        result[4] = words.getProperty(locale.getLanguage() + "_" + outLang.getLanguage() + "." + place, place);
        result[5] = NumberFormat.getInstance(outLang).format(cost);
        return result;
    }

    public String translate(Locale outLang, String dateFormat) {
        StringBuilder result = new StringBuilder();
        for (Locale l : Locale.getAvailableLocales())
            if (l.getDisplayCountry(locale).equals(country)) {
                result.append(l.getDisplayCountry(outLang));
                break;
            }
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(dateFormat);
        result.append("\t").append(dateOut.format(dtFormat)).append("\t").append(dateBack.format(dtFormat)).append("\t");
        result.append(words.getProperty(locale.getLanguage() + "_" + outLang.getLanguage() + "." + place, place));
        result.append("\t").append(NumberFormat.getInstance(outLang).format(cost)).append("\t").append(currency);
        return result.toString();
    }
}
