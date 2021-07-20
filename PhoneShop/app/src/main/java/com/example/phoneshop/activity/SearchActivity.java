package com.example.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoneshop.R;
import com.example.phoneshop.adapter.NotificationAdapter;
import com.example.phoneshop.adapter.SanPhamAdapter;
import com.example.phoneshop.adapter.SearchAdapter;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.GioHang;
import com.example.phoneshop.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<GioHang> gioHangs;
    NotificationAdapter notificationAdapter;
    SearchAdapter searchAdapter;
    TagGroup tagGroup;
    TextView textViewThoat, textViewthongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerView);
        textViewThoat = findViewById(R.id.btnThoat);
        textViewthongbao = findViewById(R.id.textViewKhongcodulieu);
        textViewThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
                finish();
            }
        });

        searchView = findViewById(R.id.search_vew);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String key) {
                searchSanPham(key);
                return false;
            }
        });
        tagGroup =  findViewById(R.id.tag_group);
        tagGroup.setTags(new String[]{"iPhone", "Samsung", "Oppo","iPhone 12","Samsung Galaxy"});
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }


    private void searchSanPham(String key) {
        DataService dataService = ApiService.getService();
        Call<List<SanPham>> callback = dataService.getSearch(key);
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                ArrayList<SanPham> sanPhams = (ArrayList<SanPham>) response.body();
                if(sanPhams.size() > 0){
                    searchAdapter = new SearchAdapter(SearchActivity.this,sanPhams);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    textViewthongbao.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    textViewthongbao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}