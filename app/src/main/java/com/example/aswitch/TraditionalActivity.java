package com.example.aswitch;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class TraditionalActivity extends AppCompatActivity {
    RecyclerView recyclerCurrency;

    MyRecyclerViewAdapter tadapter;
    Dialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_traditional);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        prepareData();
        //Commons.datatraditional = (ArrayList<Traditional>) TraditionalTable.getAllProducts(tradbhelper);
        tadapter = new MyRecyclerViewAdapter(this, Commons.datacurrency);
        recyclerCurrency = findViewById(R.id.recyclerTra);
        recyclerCurrency.setAdapter(tadapter);

        LinearLayoutManager layoutManagertra = new LinearLayoutManager(this);
        recyclerCurrency.setLayoutManager(layoutManagertra);

    }


    public void prepareData(){

        ArrayList <Currency> cryptoitems  = new ArrayList<>();

        Collections.addAll(cryptoitems,
                new Currency("Bitcoin","773.628,23", R.drawable.bitcoin ),
                new Currency("Ethereum", "65.265,34", R.drawable.eth),
                new Currency("Dogecoin", "2,84",R.drawable.doge));
    }




}