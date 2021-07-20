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
import com.example.phoneshop.R;
import com.example.phoneshop.adapter.CTDonHangAdapter;
import com.example.phoneshop.adapter.DonHangAdapter;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.CTDonHang;
import com.example.phoneshop.model.DonHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDonHangActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    ImageView imageView;
    RecyclerView recyclerView;
    DonHang donHang;
    CTDonHangAdapter ctDonHangAdapter;
    ArrayList<CTDonHang> ctDonHangs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        Anhxa();
        DataIntent();
        init();
        if(donHang != null && !donHang.getId().equals("")){
            GetCTDonHang(donHang.getId());
        }
    }
    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.relative);
    }

    private void GetCTDonHang(String MaDH) {
        DataService dataService = ApiService.getService();
        Call<List<CTDonHang>> callback = dataService.getCTDonHang(MaDH);
        callback.enqueue(new Callback<List<CTDonHang>>() {
            @Override
            public void onResponse(Call<List<CTDonHang>> call, Response<List<CTDonHang>> response) {
                ctDonHangs = (ArrayList<CTDonHang>) response.body();
                ctDonHangAdapter = new CTDonHangAdapter(ChiTietDonHangActivity.this, ctDonHangs);
                recyclerView.setAdapter(ctDonHangAdapter);

            }

            @Override
            public void onFailure(Call<List<CTDonHang>> call, Throwable t) {

            }
        });
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết đơn hàng");
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
            if(intent.hasExtra("donhang")){
                donHang = (DonHang) intent.getSerializableExtra("donhang");
            }
        }
    }
}