package com.example.phoneshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.phoneshop.SharedPreferences.DataLocalManager;
import com.example.phoneshop.fragment.AccountFragment;
import com.example.phoneshop.fragment.DashboardFragment;
import com.example.phoneshop.fragment.HomeFragment;
import com.example.phoneshop.fragment.NotificationsFragment;
import com.example.phoneshop.R;
import com.example.phoneshop.model.GioHang;
import com.example.phoneshop.model.SanPham;
import com.example.phoneshop.model.TaiKhoan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    boolean status = false;
    MenuItem menuItem;
    EditText searchView;
    ImageView imageView;
    RelativeLayout relativeLayout;
    ViewFlipper viewFlipper;
    TaiKhoan taiKhoan;
    public static ArrayList<GioHang> mangGiohang;
    public static ArrayList<SanPham> notification;
    public static String strTaikhoan;
    public static String strmatkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        strTaikhoan = sharedPreferences.getString("username","");
        strmatkhau = sharedPreferences.getString("password","");

        initGiohang();
        initNotification();


        navView = findViewById(R.id.nav_view);
        imageView = findViewById(R.id.imgGiohang);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
        relativeLayout = findViewById(R.id.relative);
        loadFragment(new AccountFragment());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

        searchView = findViewById(R.id.search_vew);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initNotification() {
        if(notification != null){

        }else {
            notification = new ArrayList<>();
        }
    }

    private void initGiohang() {
        if(mangGiohang != null){

        }else {
            mangGiohang = new ArrayList<>();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    fragment = new NotificationsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_account:
                    fragment = new AccountFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
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