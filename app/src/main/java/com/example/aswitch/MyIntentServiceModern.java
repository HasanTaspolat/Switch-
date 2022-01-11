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
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyIntentServiceModern extends IntentService {

    JSONObject jsonObject;
    RequestQueue requestQueue; //VOLLEY STEP 1:
    ArrayList<Currency> recipeItems;
    String jsonStr = loadFileFromAssets("modern.json");
    public MyIntentServiceModern() {
        super("MyIntentServiceTraditional");
        Log.d("Service","Service Started");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        requestQueue = Volley.newRequestQueue(this); //VOLLEY STEP 2

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, jsonStr, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON", response.toString());
                        try{
                            JSONArray jsonArr = response.getJSONArray("currencies");
                            Log.d("JSON","array"+jsonArr.toString());
                            recipeItems = new ArrayList<>();
                            for(int i=0; i<jsonArr.length(); i++){

                                JSONObject jsobj = jsonArr.getJSONObject(i);
                                String name = jsobj.getString("name");
                                String img = jsobj.getString("img");
                                String price = jsobj.getString("price");

                                Currency r = new Currency(name, price,img);
                                Log.d("JSON", r.toString());

                                Commons.datacurrency.add(r);
                                recipeItems.add(r);
                            }

                            Log.d("Service","JSON parsed");
                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction("JSON_PARSE_COMPLETED_ACTION");
                            if(jsonArr.length()>0) {
                                broadcastIntent.putExtra("result", "FOUND");
                                broadcastIntent.putExtra("recipeItems", recipeItems);
                            }else
                                broadcastIntent.putExtra("result", "NOTFOUND");

                            sendBroadcast(broadcastIntent);
                        }
                        catch (Exception e){
                            Log.d("JSON", e.getMessage());
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

}
