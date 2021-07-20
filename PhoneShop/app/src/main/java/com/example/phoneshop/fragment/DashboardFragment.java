package com.example.phoneshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.phoneshop.R;
import com.example.phoneshop.activity.SPTheoLoaiActivity;
import com.example.phoneshop.adapter.LoaiSPAdapter;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.Banner;
import com.example.phoneshop.model.LoaiSP;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textView;
    GridView gridView;
    ImageView imageView;
    View view;

    LoaiSPAdapter loaiSPAdapter;
    ArrayList<LoaiSP> loaiSPS;


    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
//    int gallery_grid_Images[]={drawable.quangcao1, drawable.quangcao2};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        gridView = view.findViewById(R.id.gridView);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.txtTitle);
        getData();
        return view;
    }

    private void getData() {
        DataService dataService = ApiService.getService();
        Call<List<LoaiSP>> callback = dataService.getLoai();
        callback.enqueue(new Callback<List<LoaiSP>>() {
            @Override
            public void onResponse(Call<List<LoaiSP>> call, Response<List<LoaiSP>> response) {
                loaiSPS = (ArrayList<LoaiSP>) response.body();
//                Log.d("BBB",loaiSPS.get(0).getTenLoai());
                loaiSPAdapter = new LoaiSPAdapter(getActivity(), android.R.layout.simple_list_item_1, loaiSPS);
                gridView.setAdapter(loaiSPAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), SPTheoLoaiActivity.class);
                        intent.putExtra("loaisp",loaiSPS.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<LoaiSP>> call, Throwable t) {

            }
        });
    }
}