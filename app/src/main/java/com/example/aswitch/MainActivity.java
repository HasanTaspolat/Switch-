package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ImageView logo;
    Button register,btnLogin;
    ArrayList<User> UserList;
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

    }


    public void onClick(View view) {
        if(view.getId() == R.id.Login){
        displayDialog();
        }
        else if(view.getId() == R.id.Register){
        displayDialogRegister();
        }
    }

    public void displayDialog(){
        final TextView tv;
        EditText email, surname;
        Button btnlogin;
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);
        btnlogin = customDialog.findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }

        public void displayDialogRegister(){
        final TextView tv;
        EditText username, surname;
        Button btnlogin;
        
        Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.diaolog_register);
        btnlogin = customDialog.findViewById(R.id.btnClose);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent intent = new Intent(MainActivity.this, FindActivity.class);
               // startActivity(intent);
            }
        });
        customDialog.show();
    }
}


