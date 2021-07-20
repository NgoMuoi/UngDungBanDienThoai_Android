package com.example.phoneshop.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.phoneshop.R;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.TaiKhoan;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XacNhanDonHang extends AppCompatActivity {

    EditText edTen, edSDT,edDiachi , edNgaygiao;
    Button btnDat,btnHuy;
    public String idkh = "";
    String taikhoan = MainActivity.strTaikhoan;
    String matkhau = MainActivity.strmatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_don_hang);

        Anhxa();
        if(taikhoan != null && matkhau != null){
            getData();
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edTen.getText().toString().trim();
                final String sdt = edSDT.getText().toString().trim();
                final String diachi = edDiachi.getText().toString().trim();
                final String ngaygiao = edNgaygiao.getText().toString().trim();

                if(ten.length() > 0 && sdt.length() >0 && diachi.length() > 0 && ngaygiao.length() >0 &&idkh != null){

                    DataService dataClient = ApiService.getService();
                    Call<String> callback = dataClient.taodonhang(ten,sdt,diachi,ngaygiao,idkh);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String madonhang = response.body();
                            if(Integer.parseInt(madonhang) > 0)
                            {
                                Toast.makeText(XacNhanDonHang.this, madonhang,Toast.LENGTH_SHORT).show();
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, ApiService.chitietdonhang, new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1"))
                                        {
                                            MainActivity.mangGiohang.clear();
                                            Toast.makeText(XacNhanDonHang.this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(),"Mời bạn tiếp tực mua hàng",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(XacNhanDonHang.this,"Lỗi rồi",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Nullable
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for(int  i =0; i< MainActivity.mangGiohang.size(); i++)
                                        {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("MaDH",madonhang);
                                                jsonObject.put("MaSP",MainActivity.mangGiohang.get(i).getId());
                                                jsonObject.put("TenSP",MainActivity.mangGiohang.get(i).getTensp());
                                                jsonObject.put("DonGia",MainActivity.mangGiohang.get(i).getGia());
                                                jsonObject.put("SoLuong",MainActivity.mangGiohang.get(i).getSoluong());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                            if(madonhang.equals("Lỗi"))
                            {
                                Toast.makeText(XacNhanDonHang.this,"Lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

//                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiService.taodonhang, new com.android.volley.Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Log.d("MaDH",response);
//                            Log.d("MaDH",idkh);
//
//                            if(Integer.parseInt(madonhang) > 0)
//                            {
//                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//                                StringRequest request = new StringRequest(Request.Method.POST, ApiService.chitietdonhang, new com.android.volley.Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//                                        if(response.equals("1"))
//                                        {
//                                            MainActivity.mangGiohang.clear();
//                                            Toast.makeText(XacNhanDonHang.this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                            startActivity(intent);
//                                            Toast.makeText(getApplicationContext(),"Mời bạn tiếp tực mua hàng",Toast.LENGTH_SHORT).show();
//                                        }
//                                        else
//                                        {
//                                            Toast.makeText(XacNhanDonHang.this,"Lỗi rồi",Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }, new com.android.volley.Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//
//                                    }
//                                }){
//                                    @Nullable
//                                    @Override
//                                    protected Map<String, String> getParams() throws AuthFailureError {
//                                        JSONArray jsonArray = new JSONArray();
//                                        for(int  i =0; i< MainActivity.mangGiohang.size(); i++)
//                                        {
//                                            JSONObject jsonObject = new JSONObject();
//                                            try {
//                                                jsonObject.put("MaDH",madonhang);
//                                                jsonObject.put("MaSP",MainActivity.mangGiohang.get(i).getId());
//                                                jsonObject.put("TenSP",MainActivity.mangGiohang.get(i).getTensp());
//                                                jsonObject.put("DonGia",MainActivity.mangGiohang.get(i).getGia());
//                                                jsonObject.put("SoLuong",MainActivity.mangGiohang.get(i).getSoluong());
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                            jsonArray.put(jsonObject);
//                                        }
//                                        HashMap<String,String> hashMap = new HashMap<String,String>();
//                                        hashMap.put("json",jsonArray.toString());
//                                        return hashMap;
//                                    }
//                                };
//                                queue.add(request);
//                            }
//                        }
//                    }, new com.android.volley.Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }){
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            HashMap<String,String> hashMap = new HashMap<String, String>();
//                            hashMap.put("nguoinhan", ten);
//                            hashMap.put("sdtgiao",sdt);
//                            hashMap.put("diachigiao",diachi);
//                            hashMap.put("ngaygiao",ngaygiao);
//                            hashMap.put("idKH",idkh);
//                            return hashMap;
//                        }
//                    };
//                    requestQueue.add(stringRequest);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanDonHang.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn cần nhập đầy đủ thông tin giao hàng ! Hãy kiểm tra lại.");
                    builder.setPositiveButton("Tôi hiểu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    private void Anhxa() {
        btnHuy = findViewById(R.id.btnTrove);
        btnDat = findViewById(R.id.btnDathang);
        edTen = findViewById(R.id.edTenKKH);
        edSDT = findViewById(R.id.edSDT);
        edDiachi = findViewById(R.id.edDiaChi);
        edNgaygiao = findViewById(R.id.edNgaygiao);
    }

    private void getData() {

        DataService dataService = ApiService.getService();
        Call<List<TaiKhoan>> callback = dataService.getTaikhoan(taikhoan,matkhau);
        callback.enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                ArrayList<TaiKhoan> taiKhoans = (ArrayList<TaiKhoan>) response.body();
                if(taiKhoans.size() >0)
                {
                    idkh = taiKhoans.get(0).getId();
                    edTen.setText(taiKhoans.get(0).getHoTen());
                    edSDT.setText(taiKhoans.get(0).getSdt());
                    edDiachi.setText(taiKhoans.get(0).getDiaChi());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c1 = Calendar.getInstance();
                    Date date = Calendar.getInstance().getTime();
                    c1.setTime(date);
                    c1.roll(Calendar.DATE, 3);
                    edNgaygiao.setText(dateFormat.format(c1.getTime()));
                }else {
//                    edTen.setText("Họ tên khách hàng");
                }
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

            }
        });
    }
}