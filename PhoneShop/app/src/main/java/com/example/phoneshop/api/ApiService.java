package com.example.phoneshop.api;

public class ApiService {

    private static String base_url = "https://mnmobile.000webhostapp.com/Server/";
    public static final String donhang = "https://mnmobile.000webhostapp.com/Server/taoDonhang.php";

    public static String chitietdonhang = "https://mnmobile.000webhostapp.com/Server/chitietdonhang.php";

    public static DataService getService(){
        return ApiRetrofit.getClient(base_url).create(DataService.class);
    }
}
