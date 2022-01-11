package com.example.aswitch;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;



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

    ArrayList<Currency> currenyItems;

    public MyIntentService() {
        super("MyIntentService");
        Log.d("Service","Service Started");
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
        Log.d("ONHANDLECALISIYOR","sdfsdfsdf");
        jsonStr = loadFileFromAssets("traditional.json");
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
                                    Log.d("servicelogishere",curr.toString());
                                }
                                Intent broadcastIntent = new Intent();
                                broadcastIntent.setAction("JSON_PARSE_COMPLETED_ACTION");
                                broadcastIntent.putExtra("currencyItem", currenyItems);
                                sendBroadcast(broadcastIntent);

                            } catch (JSONException ee) {
                                ee.printStackTrace();
                            }
                        }

                    }
                }


