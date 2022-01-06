package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*  Starting of the music file for other buttons or parts.

 mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.button);
             mPlayer.start();

 */


public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ImageView logo;
    Button register;
    ArrayList<User> UserList;

    MediaPlayer mPlayer;
    MediaPlayer mPlayer2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        dbHelper = new DatabaseHelper(this);
        UserList = UserTable.getAllusers(dbHelper);
        logo = findViewById(R.id.imageView3) ;
        logo.setImageResource(R.drawable.logo);


        mPlayer2 = MediaPlayer.create(this,R.raw.menu);
        mPlayer2.start();

    }


    public void onClick(View view) {
        if(view.getId() == R.id.Login){
            mPlayer = MediaPlayer.create(this,R.raw.button);
            mPlayer.start();

        displayDialog();
        }
        else if(view.getId() == R.id.Register){
            //soundEffect
            mPlayer = MediaPlayer.create(this,R.raw.button);
            mPlayer.start();

        displayDialogRegister();
        }
    }

    public void displayDialog(){
        final TextView tv;
        EditText email, password;
        Button btnlogin;
        String em,ps;
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);
        email = customDialog.findViewById(R.id.editTextTextEmailAddress);
        password = customDialog.findViewById(R.id.editTextTextPassword);
        btnlogin = customDialog.findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserList = UserTable.getAllusers(dbHelper);
                for(User a: UserList)
                {
                    mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.button);
                    mPlayer.start();

                    Log.d("DATABASE OPERATIONS", " " + a.toString());
                    if(a.getEmail().equals(email.getText().toString()) && a.getPassword().equals(password.getText().toString())){
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);


                    }
                    else{

                    }

                }
            }
        });
        customDialog.show();
    }

        public void displayDialogRegister(){

        final TextView tv;
        EditText username, password, email;
        Button btnlogin;
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.diaolog_register);
        email= customDialog.findViewById(R.id.emReg);
        password= customDialog.findViewById(R.id.psReg);
        username= customDialog.findViewById(R.id.usReg);
        btnlogin = customDialog.findViewById(R.id.btnClose);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.button);
                mPlayer.start();
                String us = username.getText().toString();
                String ps = password.getText().toString();
                String em = email.getText().toString();
                UserTable.insert(dbHelper, us, ps, em);
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }
}


