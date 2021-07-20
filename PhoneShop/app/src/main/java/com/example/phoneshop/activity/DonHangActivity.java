package com.example.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phoneshop.R;
import com.example.phoneshop.adapter.DonHangAdapter;
import com.example.phoneshop.adapter.SanPhamTheoLoai;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.DonHang;
import com.example.phoneshop.model.LoaiSP;
import com.example.phoneshop.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    DonHangAdapter donHangAdapter;
    ArrayList<DonHang> donHangs;
    public String idkh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        Anhxa();
        init();
        DataIntent();
        if(idkh != null || idkh != ""){
            getDonHang(idkh);
        }
    }
    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.relative);
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đơn hàng đã đặt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplication(),1));
    }
    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("MaKH")){
                idkh = intent.getStringExtra("MaKH");
            }
        }
    }
    private void getDonHang(String MaKH) {
        DataService dataService = ApiService.getService();
        Call<List<DonHang>> callback = dataService.getDonHang(MaKH);
        callback.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                donHangs = (ArrayList<DonHang>) response.body();
                donHangAdapter = new DonHangAdapter(DonHangActivity.this,donHangs);
                recyclerView.setAdapter(donHangAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }
}