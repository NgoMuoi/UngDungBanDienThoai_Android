 package com.example.phoneshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.phoneshop.R;
import com.example.phoneshop.activity.TatCaSanPhamActivity;
import com.example.phoneshop.adapter.BannerAdapter;
import com.example.phoneshop.adapter.SanPhamAdapter;
import com.example.phoneshop.adapter.SanPhamHotAdapter;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.Banner;
import com.example.phoneshop.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // khai báo  biến quảng cáo
    ViewPager viewPager;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    //khai báo biến sp mới nhất
    GridView gridView;
    View view;
    // khai báo biến sản phẩm hot
    HorizontalScrollView horizontalScrollView;
    CardView cardView ;
    TextView xemthem, xemthem2;
    ArrayList<SanPham> sanPhams;
    SanPhamHotAdapter sanPhamHotAdapter;
    RecyclerView recyclerViewHot, recyclerViewNew ;

    SanPhamAdapter sanPhamAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Anhxa();
        EventButton();
        // gọi hàm truyền dữ liệu quảng cáo
        getData();
        // gọi hàm truyền dữ liệu sản phẩm mới nhất
        getNewSP();
        // gọi hàm truyền dữ liệu sản phẩm hot
        getHotSP();
        return view;
    }

     private void EventButton() {
         xemthem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), TatCaSanPhamActivity.class);
                 startActivity(intent);
             }
         });
         xemthem2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), TatCaSanPhamActivity.class);
                 startActivity(intent);
             }
         });
     }

     private void Anhxa() {

         // ánh xạ viewPager quảng cáo
         viewPager = view.findViewById(R.id.viewpager);
         // ánh xạ gridView sản phảm mới nhất
         recyclerViewNew = view.findViewById(R.id.recyclerViewNew);
         recyclerViewNew.setHasFixedSize(true);
         recyclerViewNew.setLayoutManager(new GridLayoutManager(getContext(),2));
         // ánh xạ horizontalScrollView sản phẩm hot
         cardView = view.findViewById(R.id.cardView);
         recyclerViewHot =  view.findViewById(R.id.recyclerViewHot);
         xemthem = view.findViewById(R.id.textViewXemThem);
         xemthem2 = view.findViewById(R.id.textViewXemThem2);
     }

     private void getHotSP() {
         DataService dataService = ApiService.getService();
         Call<List<SanPham>> callbackHot = dataService.getSPHot();
         callbackHot.enqueue(new Callback<List<SanPham>>() {
             @Override
             public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                 sanPhams = (ArrayList<SanPham>) response.body();
                 sanPhamHotAdapter = new SanPhamHotAdapter(getContext(),sanPhams);
                 LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                 linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                 recyclerViewHot.setLayoutManager(linearLayoutManager);
                 recyclerViewHot.setAdapter(sanPhamHotAdapter);
             }

             @Override
             public void onFailure(Call<List<SanPham>> call, Throwable t) {

             }
         });
     }

     private void getNewSP() {

         DataService dataService = ApiService.getService();
         Call<List<SanPham>> callback = dataService.getSPNew();
         callback.enqueue(new Callback<List<SanPham>>() {
             @Override
             public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams = (ArrayList<SanPham>) response.body();
                 sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhams);
                 recyclerViewNew.setAdapter(sanPhamAdapter);
             }

             @Override
             public void onFailure(Call<List<SanPham>> call, Throwable t) {

             }
         });
     }

     private void getData() {
         DataService dataService = ApiService.getService();
         Call<List<Banner>> callback = dataService.getBanner();
         callback.enqueue(new Callback<List<Banner>>() {
             @Override
             public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                 ArrayList<Banner> banners = (ArrayList<Banner>) response.body();
                 bannerAdapter = new BannerAdapter(getContext(),banners);
                 viewPager.setAdapter(bannerAdapter);
                 // tao tu dong chay quang cao
                 handler = new Handler();
                 runnable =  new Runnable() {
                     @Override
                     public void run() {
                         currentItem = viewPager.getCurrentItem();
                         currentItem++;
                         if(currentItem >=viewPager.getAdapter().getCount()){
                             currentItem = 0;
                         }
                         viewPager.setCurrentItem(currentItem,true);
                         handler.postDelayed(runnable,5000);
                     }
                 };
                 handler.postDelayed(runnable,5000);
             }

             @Override
             public void onFailure(Call<List<Banner>> call, Throwable t) {

             }
         });
     }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
     }

 }