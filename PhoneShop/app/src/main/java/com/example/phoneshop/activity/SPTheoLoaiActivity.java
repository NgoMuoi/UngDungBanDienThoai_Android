package com.example.phoneshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.phoneshop.R;

import com.example.phoneshop.adapter.SanPhamTheoLoai;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.LoaiSP;
import com.example.phoneshop.model.SanPham;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPTheoLoaiActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    ImageView imageView;
    RecyclerView recyclerView;
    LoaiSP loaiSP;
    SanPhamTheoLoai sanPhamTheoLoai;
    ArrayList<SanPham> sanPhams;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_theo_loai);
        Anhxa();
        DataIntent();
        init();
        if(loaiSP != null && !loaiSP.getTenLoai().equals("")){
            GetDataSanPhamTheoLoai(loaiSP.getIDLoai());
        }
    }

    private void Anhxa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.relative);
    }

    private void GetDataSanPhamTheoLoai(String idLoai) {
        DataService dataService = ApiService.getService();
        Call<List<SanPham>> callback = dataService.getSanPhamTheoLoai(idLoai);
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams = (ArrayList<SanPham>) response.body();
                sanPhamTheoLoai = new SanPhamTheoLoai(SPTheoLoaiActivity.this,sanPhams);
                recyclerView.setAdapter(sanPhamTheoLoai);

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Điện Thoại"+" "+loaiSP.getTenLoai());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplication(),2));
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("loaisp")){
                loaiSP = (LoaiSP) intent.getSerializableExtra("loaisp");
//                Toast.makeText(this,loaiSP.getIDLoai(),Toast.LENGTH_SHORT).show();
            }
//            if(intent.hasExtra("ABC")){
//                banner = (Banner) intent.getSerializableExtra("ABC");
//                Toast.makeText(this,banner.getIDBanner(),Toast.LENGTH_SHORT).show();
//            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_giohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}