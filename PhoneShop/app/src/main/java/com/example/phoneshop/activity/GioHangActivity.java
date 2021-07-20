package com.example.phoneshop.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.phoneshop.R;
import com.example.phoneshop.adapter.GioHangAdapter;
import com.example.phoneshop.model.GioHang;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    ImageView imageView;
    TextView  textViewThongbao;
    static TextView textViewTongtien;
    ListView listView;
    Button buttonThanhtoan,buttonMuatiep;
    Toolbar toolbar;
    String taikhoan = MainActivity.strTaikhoan;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        Anhxa();
        init();
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, MainActivity.mangGiohang);
        listView.setAdapter(gioHangAdapter);
        CheckData();
        EventUltil();
        CatchOnItemListView();
        EventButton();


    }

    private void EventButton() {

        buttonThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGiohang.size() > 0){
                    if(taikhoan != "") {
                        Intent intent = new Intent(getApplicationContext(), XacNhanDonHang.class);
                        startActivity(intent);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn chưa đăng nhập! Hãy đăng nhập để thanh toán");
                        builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent(GioHangActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Giỏ hàng của bạn còn trống! Hãy thêm sản phẩm vào giỏ hàng");
                    builder.setPositiveButton("Tôi hiểu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            textViewThongbao.setVisibility(View.VISIBLE);
                        }
                    });
                    builder.show();
                }
            }
        });

        buttonMuatiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        imageView = findViewById(R.id.imageViewSP);
        textViewThongbao = findViewById(R.id.textViewthongbao);
        textViewTongtien = findViewById(R.id.textViewtongtien);
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listViewgiohang);
        buttonThanhtoan = findViewById(R.id.buttonThanhtoan);
        buttonMuatiep = findViewById(R.id.buttonTieptuc);
    }

    private void CatchOnItemListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xóa sản phẩm khỏi giỏ hàng");
                builder.setMessage("Bạn có muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(MainActivity.mangGiohang.size() <= 0){
                            textViewThongbao.setVisibility(View.VISIBLE);
//                            gioHangAdapter.notifyDataSetChanged();
//                            EventUltil();
                        }else {
                            MainActivity.mangGiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.mangGiohang.size() <= 0){
                                textViewThongbao.setVisibility(View.VISIBLE);
                                textViewTongtien.setText(0 +" Đ");
//                                gioHangAdapter.notifyDataSetChanged();
//                                EventUltil();
                            }else {
                                textViewThongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for(int i =0; i< MainActivity.mangGiohang.size();i++){
            tongtien += MainActivity.mangGiohang.get(i).getGia();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            textViewTongtien.setText(decimalFormat.format(tongtien)+" Đ");
        }
    }

    private void CheckData() {
        if(MainActivity.mangGiohang.size() <=0){
            gioHangAdapter.notifyDataSetChanged();
            textViewThongbao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            textViewThongbao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Giỏ hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
    }
}