package com.example.aswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrencySys {


    private static ArrayList<Currency> concertList = new ArrayList<>();
    public static HashMap<String,ArrayList<Currency>> currencyMap = new HashMap<>();



    public static ArrayList<Currency> getConcertsWithArtists(String selectedCurrency) {
        ArrayList<Currency> currencySelected =currencyMap.get(selectedCurrency);
        return currencySelected;
    }

    //this method returns the category list which will be used to fill custom spinner
    public static ArrayList<Currency> getCityList() {
        return concertList;
    }

}
