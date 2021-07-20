package com.example.phoneshop.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoneshop.R;
import com.example.phoneshop.SharedPreferences.DataLocalManager;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin,btnOK,btnCancel;
    EditText edtUsername, edtPassword;
    TextView btnRegister;
    SharedPreferences sharedPreferences;
    boolean status = false;
    String taikhoan;
    String matkhau;
    String strUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        Anhxa();
        EventButton();

    }

    private void Anhxa() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtUsername = findViewById(R.id.userName);
        edtPassword = findViewById(R.id.passWord);
    }

    private void EventButton() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUsername.getText().toString().isEmpty() ||
                        edtPassword.getText().toString().isEmpty()){
                    final Dialog dialog = new Dialog(LoginActivity.this);
                    dialog.setContentView(R.layout.dialog_custom);
                    btnOK = dialog.findViewById(R.id.btnOK);
                    btnCancel = dialog.findViewById(R.id.btnCancel);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(LoginActivity.this,
                                    RegisterActivity.class);
                            startActivityForResult(intent, 100);
                            dialog.dismiss();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }
                else
                {
                    if(edtPassword.getText().toString().length() != 6) {
                        edtPassword.setError("Hãy nhập 6 ký tự");
                    }
                    else {
                        taikhoan = edtUsername.getText().toString();
                        matkhau = edtPassword.getText().toString();

                        DataService dataClient = ApiService.getService();
                        Call<List<TaiKhoan>> callback = dataClient.login(taikhoan,matkhau);
                        callback.enqueue(new Callback<List<TaiKhoan>>() {
                            @Override
                            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                                ArrayList<TaiKhoan> arrtaikhoan = (ArrayList<TaiKhoan>) response.body();
                                if(arrtaikhoan.size() >0)
                                {
//                                    DataLocalManager.setListTaikhoan(arrtaikhoan);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("status", "true");
                                    editor.putString("username", taikhoan);
                                    editor.putString("password", matkhau);
                                    editor.commit();

                                    Intent intent = new Intent(LoginActivity.this,
                                            MainActivity.class);
                                    intent.putExtra("username", arrtaikhoan);
                                    startActivity(intent);

                                }
                            }
                            @Override
                            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 101){
            edtUsername.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
        if(requestCode == 102 && resultCode == 101){
            edtUsername.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPreferences.getString("status", "").equals("true")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}