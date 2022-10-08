package c1;

import javax.swing.*;

public class Country {
    private final String country;
    private final String capital;
    private final int population;
    private ImageIcon flag;

    public Country(String country, String capital, int population, ImageIcon flag) {
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public ImageIcon getFlag() {
        return flag;
    }
}
