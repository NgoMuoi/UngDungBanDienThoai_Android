package com.example.phoneshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */
@Generated("jsonschema2pojo")
public class TaiKhoan implements Parcelable {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("HoTen")
    @Expose
    private String hoTen;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("TaiKhoan")
    @Expose
    private String taiKhoan;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("SDT")
    @Expose
    private String sdt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;

    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    protected TaiKhoan(Parcel in){
        id = in.readString();
        hoTen = in.readString();
        email = in.readString();
        taiKhoan = in.readString();
        password = in.readString();
        diaChi = in.readString();
        sdt = in.readString();
    }

    public static final Creator<TaiKhoan> CREATOR = new Creator<TaiKhoan>() {
        @Override
        public TaiKhoan createFromParcel(Parcel in) {
            return new TaiKhoan(in);
        }

        @Override
        public TaiKhoan[] newArray(int size) {
            return new TaiKhoan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id='" + id + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", email='" + email + '\'' +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", password='" + password + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}