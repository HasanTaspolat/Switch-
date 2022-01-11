package com.example.aswitch;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyIntentService extends IntentService {

    private String jsonStr;
    private JSONArray currencies;
    private JSONObject bookJSONObject;


    public static final String TAG_CUR = "currencies";
    public static final String TAG_NAME = "name";
    public static final String TAG_VALUE = "value";
    public static final String TAG_IMG = "img";

    RequestQueue requestQueue; //VOLLEY STEP 1:
    ArrayList<Currency> currenyItems;

    public MyIntentService() {
        super("MyIntentService");
        Log.d("Service","Service Started");
        jsonStr = loadFileFromAssets("traditional.json");
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

    @Override
    protected void onHandleIntent(Intent intent) {
        requestQueue = Volley.newRequestQueue(this); //VOLLEY STEP 2

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, Commons.urlForjSON, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                                    String imgName = jsonObj.getString(TAG_IMG);
                                    Log.d("name,,",name);
                                    Log.d(",value,",value);
                                    Log.d(",,imgname",imgName);

                                    Currency curr = new Currency(name, value, imgName );
                                    currenyItems.add(curr);
                                }
                                Commons.setDataTrad(currenyItems);
                                Intent broadcastIntent = new Intent();
                                broadcastIntent.setAction("JSON_PARSE_COMPLETED_ACTION");
                                if(currencies.length()>0) {
                                    broadcastIntent.putExtra("result", "FOUND");
                                    broadcastIntent.putExtra("currencyItem", currenyItems);
                                }else
                                    broadcastIntent.putExtra("result", "NOTFOUND");

                                sendBroadcast(broadcastIntent);

                            } catch (JSONException ee) {
                                ee.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //If file cannot be taken, ıf an error returned form server, onErrorResponse will be executed
                        //calling this method handled by Volley's thread
                        Log.d("Error",error.toString());
                    }
                }
        );
        //VOLLEY STEP 4
        requestQueue.add(jsonObjectRequest);

    }
}
