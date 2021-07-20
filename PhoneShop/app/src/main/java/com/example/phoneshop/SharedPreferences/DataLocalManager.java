package com.example.phoneshop.SharedPreferences;

import android.content.Context;

import com.example.phoneshop.model.GioHang;
import com.example.phoneshop.model.TaiKhoan;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {

    private static final String PREF_GIO_HANG = "PREF_GIO_HANG";
    private static final String PREF_LOGIN = "PREF_LOGIN";
    private static final String PREF_TaiKhoan = "PREF_TaiKhoan";
    MySharedPreferences mySharedPreferences;
    private static DataLocalManager instance;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setListGioHang(ArrayList<GioHang> listGioHang){
        Gson gson =  new Gson();
        JsonArray jsonArray = gson.toJsonTree(listGioHang).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_GIO_HANG, strJsonArray);
    }

    public static ArrayList<GioHang> getListGioHang(){

        String strJsonArray = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_GIO_HANG);
        ArrayList<GioHang> gioHangs = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            GioHang gioHang;
            Gson gson = new Gson();
            for(int i =0; i < jsonArray.length();i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                gioHang = gson.fromJson(jsonObject.toString(), GioHang.class);
                gioHangs.add(gioHang);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gioHangs;
    }

    public static void setUser(TaiKhoan user){
        Gson gson =  new Gson();
        String strJsoUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_LOGIN, strJsoUser);
    }

    public static void setListTaikhoan(ArrayList<TaiKhoan> taiKhoans){
        Gson gson =  new Gson();
        JsonArray jsonArray = gson.toJsonTree(taiKhoans).getAsJsonArray();
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValueLogin(PREF_TaiKhoan, strJsonArray);
    }

    public static ArrayList<TaiKhoan> getListTaikhoan(){

        String strJsonArray = DataLocalManager.getInstance().mySharedPreferences.getStringValueLogin(PREF_TaiKhoan);
        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            TaiKhoan taiKhoan;
            Gson gson = new Gson();
            for(int i =0; i < jsonArray.length();i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                taiKhoan = gson.fromJson(jsonObject.toString(), TaiKhoan.class);
                taiKhoans.add(taiKhoan);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return taiKhoans;
    }

    public static TaiKhoan getUser(){
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_LOGIN);
        Gson gson =  new Gson();
        TaiKhoan user = gson.fromJson(strJsonUser, TaiKhoan.class);
        return user;
    }

}
