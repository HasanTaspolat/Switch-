package com.example.aswitch;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


        Intent intent;
        intent = getIntent();

        //Commons.datatraditional = (ArrayList<Traditional>) TraditionalTable.getAllProducts(tradbhelper);

        recyclerCurrency = findViewById(R.id.recyclerTra);
        tadapter = new MyRecyclerViewAdapter(this, CurrencySys.getConcertsWithArtists("Traditional"));
        recyclerCurrency.setAdapter(tadapter);

        LinearLayoutManager layoutManagertra = new LinearLayoutManager(this);
        recyclerCurrency.setLayoutManager(layoutManagertra);




    }







}