package com.example.aswitch;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class ProfileActivity extends AppCompatActivity implements  TopFragment.TopFragmentInterface {

    ImageView tradimg, modernimg;
    TopFragment topFragment;
    BottomFragment bottomFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentNavtrad);
        bottomFragment = new BottomFragment();
        Bundle b = new Bundle();
        b.putInt("position",0);
        bottomFragment.setArguments(b);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
        fragmentTransaction.commit();

    }
    @Override
    public void changeImage(int position) {
        bottomFragment.changeCityImage(position);
    }
}