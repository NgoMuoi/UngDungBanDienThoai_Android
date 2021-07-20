package com.example.phoneshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phoneshop.R;
import com.example.phoneshop.api.ApiService;
import com.example.phoneshop.api.DataService;
import com.example.phoneshop.model.TaiKhoan;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edemail,name,username,password,confimpassword,phone,address;
    Button btnDangky, btnHuy;
    String email;
    String hoten;
    String taikhoan;
    String matkhau;
    String diachi;
    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Anhxa();
        EventButton();


    }

    private void EventButton() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isValid(edemail.getText().toString())){
                    edemail.setError("Địa chỉ email không hợp lệ");
                    return;
                }
                if(name.getText().toString().isEmpty()){
                    name.setError("Hãy nhập tên của bạn");
                    return;
                }
                if(username.getText().toString().isEmpty()){
                    username.setError("Tên người dùng không được để trống");
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Mật khẩu không được để trống");
                    return;
                }
                if(password.getText().toString().length() != 6) {
                    password.setError("Mật khẩu bao gồm 6 ký tự");
                    return;
                }
                if(confimpassword.getText().toString().isEmpty()){
                    confimpassword.setError("Hãy xác nhận mật khẩu");
                    return;
                }
                if(address.getText().toString().isEmpty()){
                    address.setError("Hãy nhập địa chỉ giao hàng");
                    return;
                }
                if(phone.getText().toString().isEmpty()){
                    phone.setError("Hãy nhập số điện thoại");
                    return;
                }
                if(password.getText().toString().equals(confimpassword.getText().toString()))
                {
                    email = edemail.getText().toString();
                    taikhoan = username.getText().toString();
                    matkhau = password.getText().toString();
                    hoten = name.getText().toString();
                    diachi = address.getText().toString();
                    sdt = phone.getText().toString();

                    DataService dataClient = ApiService.getService();
                    Call<String> callback = dataClient.dangky(email,hoten,taikhoan,matkhau,diachi,sdt);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if(result.equals("ok"))
                            {
                                Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,
                                        LoginActivity.class);
                                intent.putExtra("username",
                                        username.getText().toString());
                                intent.putExtra("password",
                                        password.getText().toString());
                                setResult(101, intent);
                                finish();
                            }
                            if(result.equals("Lỗi"))
                            {
                                Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                else {
                    confimpassword.setError("Mật khẩu và mật khẩu xác nhận không khớp");
                    confimpassword.setText("");
                    return;
                }
            }
        });
    }

    private void Anhxa() {
        edemail = findViewById(R.id.editTextEmail);
        name = findViewById(R.id.editTextHoTen);
        username = findViewById(R.id.editTextTenDN);
        password = findViewById(R.id.editTextMatKhau);
        confimpassword = findViewById(R.id.editTextNhapLaiMK);
        phone = findViewById(R.id.editTextSDT);
        address = findViewById(R.id.editTextDiaChi);
        btnDangky = findViewById(R.id.btnDangky);
        btnHuy = findViewById(R.id.btnHuy);
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}