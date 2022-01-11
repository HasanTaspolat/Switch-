package com.example.aswitch;

import android.database.Cursor;

import java.util.ArrayList;

public class Commons {

        public static Currency currency;
        public static Cursor cursor;
        public static ArrayList<Currency> datatrad;
        public static ArrayList<Currency> datacurrency;

    public static void setDatacrypto(ArrayList<Currency> datacrypto) {
        Commons.datacurrency = datacrypto;
    }
    public static void setDataTrad(ArrayList<Currency> datatrad) {
        Commons.datatrad = datatrad;
    }
    public static ArrayList<Currency> getData() {
        return datacurrency;
    }
    public static ArrayList<Currency> getDatatrad() {
        return datatrad;
    }
    public static void setData(ArrayList<Currency> data) {
        Commons.datacurrency = data;
    }
    public static String urlForjSON = "http://ctis.bilkent.edu.tr/ctis487/recipes.php";








    public static Currency getCurrency() {
        return currency;
    }

    public static void setCrypto(Currency crypto) {
        Commons.currency = crypto;
    }

    public static Cursor getCursor() {
        return cursor;
    }

    public static void setCursor(Cursor cursor) {
        Commons.cursor = cursor;
    }

    public static ArrayList<Currency> getDatacrypto() {
        return datacurrency;
    }

}
