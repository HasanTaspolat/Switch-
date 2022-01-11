package com.example.aswitch;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ModernActivity extends AppCompatActivity {
    public static Context context;
    private RecyclerView recyclerCurrency;
    private MyRecyclerViewAdapterModern tadapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Dialog customDialog;
    private ArrayList<Currency> mArrayList;

    // JSON related
    private String jsonStr;
    private JSONArray currencies;
    private JSONObject bookJSONObject;

    String key="";


    public static final String TAG_CUR = "currencies";
    public static final String TAG_NAME = "name";
    public static final String TAG_VALUE = "value";
    public static final String TAG_IMG = "img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_traditional);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        recyclerCurrency = (RecyclerView) findViewById(R.id.recyclerTra);


        mLayoutManager = new LinearLayoutManager(this);
        recyclerCurrency.setLayoutManager(mLayoutManager);

        //JSON RELATED

        mArrayList = new ArrayList<Currency>();

        jsonStr = loadFileFromAssets("modern.json");
        Log.d("TAG", "\n" + jsonStr);

        // Call to AsyncTask
        new ModernActivity.GetCurr().execute();

        IntentFilter filter = new IntentFilter();
        filter.addAction("JSON_PARSE_COMPLETED_ACTION");
        registerReceiver(mbroadcastreciver, filter);

        Intent intent = new Intent(this, MyIntentServiceTraditional.class);
        startService(intent);

        Toast.makeText(this, "Without waiting the result of json request", Toast.LENGTH_SHORT).show();

    }

    private BroadcastReceiver mbroadcastreciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Service", "Broadcast BroadcastReceiver");
            Toast.makeText(ModernActivity.this, intent.getStringExtra("result"),Toast.LENGTH_SHORT).show();
            Log.d("Service", "Broadcast BroadcastReceiver");

            //MyRecyclerViewAdapterTraditional adapter = new MyRecyclerViewAdapterTraditional(MainActivity.this, Commons.recipeList);

            ArrayList<Currency> items = intent.getParcelableArrayListExtra("recipeItems");
            MyRecyclerViewAdapterTraditional adapter = new MyRecyclerViewAdapterTraditional(ModernActivity.this,items);
            recyclerCurrency.setAdapter(adapter);
        }
    };

    private class GetCurr extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Creating and showing the progress Dialog
        }

        // Main job should be done here
        @Override
        protected Void doInBackground(Void... params) {
            //Log.d("TAG", "HERE.....");

            if (jsonStr != null) {
                try {
                    bookJSONObject = new JSONObject(jsonStr);
                    // Getting JSON Array
                    currencies = bookJSONObject.getJSONArray(TAG_CUR);



                    for (int i = 0; i < currencies.length(); i++) {

                        JSONObject jsonObj = currencies.getJSONObject(i);

                        //Thread.sleep(2000);//This is here only to simulate parsing json takes time so that ProgressBar execution can be displayed better

                        String name = jsonObj.getString(TAG_NAME);
                        String value = jsonObj.getString(TAG_VALUE);
                        String imgName = "http://ctis.bilkent.edu.tr/ctis487/imgJsons/"+jsonObj.getString(TAG_IMG);

                        //String imgName = jsonObj.getString(TAG_IMG); --> normally that should be used, because we do not have any endpoint. 

                        Log.d("name,,",name);
                        Log.d(",value,",value);
                        Log.d(",,imgname",imgName);



                        Currency curr = new Currency(name, value, imgName );

                        if(key.isEmpty())
                            mArrayList.add(curr);
                        else if(name.toLowerCase().contains(key.toLowerCase()))
                            mArrayList.add(curr);

                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                }
            }

            return null;
        }



        // What do you want to do after doInBackground() finishes
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the progress dialog


            if (mArrayList != null) {
                tadapter = new MyRecyclerViewAdapterModern(ModernActivity.this, mArrayList);
                recyclerCurrency.setAdapter(tadapter);
            } else
                Toast.makeText(ModernActivity.this, "Not Found", Toast.LENGTH_LONG).show();
        }

    }


    private String loadFileFromRaw(String fileName) {
        String fileContent = null;
        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier(fileName,
                            "raw", getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;
    }

    private String loadFileFromAssets(String fileName) {
        String fileContent = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;
    }

    public void displayDialog(final String msg){
        final TextView tv;
        Button btnClose;

        customDialog = new Dialog(this);

        customDialog.setContentView(R.layout.dialogcurr);
        tv =  customDialog.findViewById(R.id.tvDialogName);
        btnClose = customDialog.findViewById(R.id.btnClose);

        YoYo.with(Techniques.Wobble)
                .duration(700)
                .repeat(20)
                .playOn(btnClose);

        tv.setText(msg+"");

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }


}