package com.example.phoneshop.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    Context context;
    SharedPreferences sharedPreferences;
    private static final String My_Share_Preferences = "My_Share_Preferences";
    private static final String DATA_LOGIN = "dataLogin";

    public MySharedPreferences(Context context) {
        this.context = context;
    }
    public void putStringValue(String key, String values){
        sharedPreferences  = context.getSharedPreferences(My_Share_Preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,values);
        editor.apply();
    }

    public String getStringValue(String key){
        sharedPreferences  = context.getSharedPreferences(My_Share_Preferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public void putStringValueLogin(String key, String values){
        sharedPreferences  = context.getSharedPreferences(DATA_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,values);
        editor.apply();
    }

    public String getStringValueLogin(String key){
        sharedPreferences  = context.getSharedPreferences(DATA_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
