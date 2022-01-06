package com.example.aswitch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class ProfileActivity extends AppCompatActivity {

    ImageView tradimg, modernimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        tradimg = findViewById(R.id.tradimg);
        modernimg = findViewById(R.id.modernimg);
        Picasso.with(this)
                .load("https://images-media.currency.com/6e89780f/1959/5495/93cc/37aa5e222ba9/on_page/shutterstock-367050494.jpg")
                .into(tradimg);
        Picasso.with(this)
                .load("https://www.worldfinance.com/wp-content/uploads/2021/06/GettyImages-1248674199_rt.jpg")
                .into(modernimg);
        Toast.makeText(this, "Please long click on the currency type you want", Toast.LENGTH_SHORT).show();

        tradimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, TraditionalActivity.class);
                startActivity(intent);

                return false;
            }
        });
        modernimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ModernActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }
}