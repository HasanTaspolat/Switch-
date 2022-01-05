package com.example.aswitch;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class UserTable {
    public static String TABLE_NAME="user";
    public static String FIELD_USERNAME = "username";
    public static String FIELD_PASSWORD = "password";
    public static String FIELD_EMAIL = "email";

    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+" ( "+FIELD_USERNAME+" text, "+FIELD_PASSWORD+" text, "+ FIELD_EMAIL +" text)";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<User> getAllusers(DatabaseHelper dbHelper){
        User anItem;
        ArrayList<User> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            String username=cursor.getString(0);
            String name = cursor.getString(1);
            String email= cursor.getString(2);
            anItem = new User(username, name, email);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }
    public static boolean insert(DatabaseHelper dbHelper, String id, String name, String email) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_USERNAME, id);
        contentValues.put(FIELD_PASSWORD, name);
        contentValues.put(FIELD_EMAIL, email);

        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }
}
