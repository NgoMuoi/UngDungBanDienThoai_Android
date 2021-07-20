package com.example.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.phoneshop.R;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.Banner;
import com.example.phoneshop.model.CTDonHang;
import com.example.phoneshop.model.GioHang;
import com.example.phoneshop.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSPActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textViewTen, textViewGia, textViewCauHinh;
    Toolbar toolbar;
    Spinner spinner;
    Button button;
    Banner banner;
    SanPham sanPham;
    CTDonHang ctDonHang;
    ArrayList<SanPham> sanPhams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_s_p);

        Anhxa();
        DataIntent();
        CatchEventSpinner();
        EventButton();
        init();

        if(banner != null && !banner.getIDBanner().equals("")){
            GetDataSanPhamTheoQC(banner.getIDBanner());
        }
        if(sanPham != null && !sanPham.getTenSP().equals("")){
            getSanPhamTheoID(sanPham.getId());
        }
        if(ctDonHang != null && !ctDonHang.getMaSP().equals("")){
            GetDataSanPhamTheoDH(ctDonHang.getMaDH());
        }
//        addNotification();
    }

    private void addNotification() {
        if(MainActivity.notification.size() > 0){
            boolean exists = false;
            for(int i  = 0; i < MainActivity.notification.size(); i++){
                if(MainActivity.notification.get(i).getId() == sanPham.getId()){
                    exists = true;
                }
            }
            if(exists == false){
                MainActivity.notification.add(new SanPham(sanPham.getId(),
                        sanPham.getTenSP(),
                        sanPham.getHinhAnh(),
                        sanPham.getGiaBan()));
            }
        }else {
            MainActivity.notification.add(new SanPham(sanPham.getId(),
                    sanPham.getTenSP(),
                    sanPham.getHinhAnh(),
                    sanPham.getGiaBan()));
        }
    }

    private void Anhxa() {
        toolbar =findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imgHinhAnh);
        textViewTen = findViewById(R.id.txtTen);
        textViewGia = findViewById(R.id.txtGia);
        textViewCauHinh = findViewById(R.id.txtCauHinh);
        button = findViewById(R.id.btnThem);
        spinner = findViewById(R.id.spinner);
    }

    private void EventButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGiohang.size() > 0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i  = 0; i < MainActivity.mangGiohang.size(); i++){
                        if(MainActivity.mangGiohang.get(i).getId() == sanPham.getId()){
                            MainActivity.mangGiohang.get(i).setSoluong(MainActivity.mangGiohang.get(i).getSoluong()+sl);
                            if(MainActivity.mangGiohang.get(i).getSoluong() >=10){
                                MainActivity.mangGiohang.get(i).setSoluong(10);
                            }
                            MainActivity.mangGiohang.get(i).setGia(Integer.parseInt(sanPham.getGiaBan())
                                    * MainActivity.mangGiohang.get(i).getSoluong());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int giasp = Integer.parseInt(sanPham.getGiaBan());
                        long gia = soluong * giasp;
                        MainActivity.mangGiohang.add(new GioHang(sanPham.getId(),
                                soluong,
                                sanPham.getTenSP(),
                                sanPham.getHinhAnh(),
                                gia));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    int giasp = Integer.parseInt(sanPham.getGiaBan());
                    long gia = soluong * giasp;
                    MainActivity.mangGiohang.add(new GioHang(sanPham.getId(),
                            soluong,
                            sanPham.getTenSP(),
                            sanPham.getHinhAnh(),
                            gia));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getSanPhamTheoID(String idSanPham) {
        DataService dataService = ApiService.getService();
        Call<List<SanPham>> callback = dataService.getSanPham(idSanPham);
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams = (ArrayList<SanPham>) response.body();
                textViewTen.setText(sanPhams.get(0).getTenSP());
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                textViewGia.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPhams.get(0).getGiaBan()))+" Đ");
                textViewCauHinh.setText(sanPhams.get(0).getMoTa());

                Picasso.get().load(sanPhams.get(0).getHinhAnh())
                        .placeholder(R.drawable.iconloiimage)
                        .error(R.drawable.iconloiimage)
                        .into(imageView);
                MainActivity.notification.add(new SanPham(sanPhams.get(0).getId(),
                        sanPhams.get(0).getTenSP(),
                        sanPhams.get(0).getHinhAnh(),
                        sanPhams.get(0).getGiaBan()));
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> integers = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(integers);
    }

    private void init() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Điện Thoại"+" "+ sanPhams.get(0).getTenSP());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private void GetDataSanPhamTheoQC(String idQuangcao) {

        DataService dataService = ApiService.getService();
        Call<List<SanPham>> callback = dataService.getSanPhamTheoQC(idQuangcao);
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams = (ArrayList<SanPham>) response.body();
                textViewTen.setText(sanPhams.get(0).getTenSP());
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                textViewGia.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPhams.get(0).getGiaBan()))+" Đ");
                textViewCauHinh.setText(sanPhams.get(0).getMoTa());

                Picasso.get().load(sanPhams.get(0).getHinhAnh())
                        .placeholder(R.drawable.iconloiimage)
                        .error(R.drawable.iconloiimage)
                        .into(imageView);

                MainActivity.notification.add(new SanPham(sanPhams.get(0).getId(),
                        sanPhams.get(0).getTenSP(),
                        sanPhams.get(0).getHinhAnh(),
                        sanPhams.get(0).getGiaBan()));
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void GetDataSanPhamTheoDH(String idDH) {

        DataService dataService = ApiService.getService();
        Call<List<SanPham>> callback = dataService.getSanPhamTheoDH(idDH);
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams = (ArrayList<SanPham>) response.body();
                textViewTen.setText(sanPhams.get(0).getTenSP());
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                textViewGia.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPhams.get(0).getGiaBan()))+" Đ");
                textViewCauHinh.setText(sanPhams.get(0).getMoTa());

                Picasso.get().load(sanPhams.get(0).getHinhAnh())
                        .placeholder(R.drawable.iconloiimage)
                        .error(R.drawable.iconloiimage)
                        .into(imageView);
                MainActivity.notification.add(new SanPham(sanPhams.get(0).getId(),
                        sanPhams.get(0).getTenSP(),
                        sanPhams.get(0).getHinhAnh(),
                        sanPhams.get(0).getGiaBan()));
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                banner = (Banner) intent.getSerializableExtra("banner");
                Toast.makeText(this,banner.getIDBanner(),Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("sanpham")){
                sanPham = (SanPham) intent.getSerializableExtra("sanpham");
                Toast.makeText(this,sanPham.getTenSP(),Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("ctdh")){
                ctDonHang = (CTDonHang) intent.getSerializableExtra("ctdh");
                Toast.makeText(this,ctDonHang.getMaSP(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}