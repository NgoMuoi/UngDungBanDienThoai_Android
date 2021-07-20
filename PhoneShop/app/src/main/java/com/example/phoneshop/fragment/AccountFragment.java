package com.example.phoneshop.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoneshop.R;
import com.example.phoneshop.activity.DonHangActivity;
import com.example.phoneshop.activity.LoginActivity;
import com.example.phoneshop.activity.MainActivity;
import com.example.phoneshop.activity.RegisterActivity;
import com.example.phoneshop.activity.XacNhanDonHang;
import com.example.phoneshop.adapter.BannerAdapter;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.Banner;
import com.example.phoneshop.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    View view;
    Button btnLogOut, btnLogIn, btnOK, btnXemDH, btnDoiMK;
    TextView textView, lienhe;
    LinearLayout haveaccount, noaccount;
    SharedPreferences sharedPreferences;
    public String idkh = "";
    String taikhoan = MainActivity.strTaikhoan;
    String matkhau = MainActivity.strmatkhau;
    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        view = inflater.inflate(R.layout.fragment_account, container, false);
        Anhxa();

        if(taikhoan == "") {
            Log.d("mn","noo");
            noaccount.setVisibility(View.VISIBLE);
            haveaccount.setVisibility(View.INVISIBLE);
        }
        else{
            Log.d("mn","yep");
            noaccount.setVisibility(View.INVISIBLE);
            haveaccount.setVisibility(View.VISIBLE);
            getData();
        }

        return view;
    }

    private void Anhxa() {
        btnLogOut =view.findViewById(R.id.btnDangXuat);
        btnLogIn = view.findViewById(R.id.btnDangNhap);
        btnXemDH = view.findViewById(R.id.btnXemDonHang);
        btnDoiMK = view.findViewById(R.id.btnDoiMK);
        textView = view.findViewById(R.id.textViewTenKH);
        haveaccount = view.findViewById(R.id.haveaccount);
        noaccount = view.findViewById(R.id.noaccount);
        lienhe = view.findViewById(R.id.lienhe);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EvenButton();

    }

    private void EvenButton() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnXemDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonHangActivity.class);
                intent.putExtra("MaKH", idkh);
                startActivity(intent);
            }
        });
        lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_lienhe);
                btnOK = dialog.findViewById(R.id.btnOK);
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
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
                    textView.setText("Xin chào "+taiKhoans.get(0).getHoTen());
                }
                else{
                    textView.setText("");
                }
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

            }
        });
    }
}