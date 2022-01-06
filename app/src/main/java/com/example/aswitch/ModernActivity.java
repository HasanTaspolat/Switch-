package com.example.aswitch;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ModernActivity extends AppCompatActivity {
    RecyclerView recyclerCurrency;
    MyRecyclerViewAdapter tadapter;
    Dialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modern);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();


        //Commons.datatraditional = (ArrayList<Traditional>) TraditionalTable.getAllProducts(tradbhelper);

        recyclerCurrency = findViewById(R.id.recyclerMod);
        tadapter = new MyRecyclerViewAdapter(this, CurrencySys.getConcertsWithArtists("Modern"));
        recyclerCurrency.setAdapter(tadapter);
        Toast.makeText(this, "You Choose Modern Currency!", Toast.LENGTH_SHORT).show();
//a
        LinearLayoutManager layoutManagertra = new LinearLayoutManager(this);
        recyclerCurrency.setLayoutManager(layoutManagertra);

    }




}