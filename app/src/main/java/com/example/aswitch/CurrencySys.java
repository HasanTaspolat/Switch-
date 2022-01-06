package com.example.aswitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CurrencySys {


    private static ArrayList<Currency> concertList = new ArrayList<>();
    public static HashMap<String,ArrayList<Currency>> currencyMap = new HashMap<>();



    public static void prepareConcerts(){
        ArrayList <Currency> Modern = new ArrayList<>();
        Collections.addAll(Modern,
                new Currency("Bitcoin","773.628,23", R.drawable.bitcoin ),
                new Currency("Ethereum", "65.265,34", R.drawable.eth),
                new Currency("Dogecoin", "2,84",R.drawable.doge)
        );

        ArrayList <Currency> Traditional = new ArrayList<>();
        Collections.addAll(Traditional,
                new Currency("Turkish Lira","0,072", R.drawable.bitcoin ),
                new Currency("Dollar", "1", R.drawable.eth),
                new Currency("Euro", "1,13",R.drawable.doge)

        );


        currencyMap.put("Modern",Modern);
        currencyMap.put("Traditional",Traditional);


    }


    public static ArrayList<Currency> getConcertsWithArtists(String selectedCurrency) {
        ArrayList<Currency> currencySelected =currencyMap.get(selectedCurrency);
        return currencySelected;
    }

    //this method returns the category list which will be used to fill custom spinner
    public static ArrayList<Currency> getCityList() {
        return concertList;
    }

}
