package com.example.phoneshop.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class CTDonHang implements Serializable {

    @SerializedName("MaDH")
    @Expose
    private String maDH;
    @SerializedName("MaSP")
    @Expose
    private String maSP;
    @SerializedName("TenSP")
    @Expose
    private String tenSP;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("SoLuong")
    @Expose
    private String soLuong;
    @SerializedName("DonGia")
    @Expose
    private String donGia;

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public CTDonHang() {
    }

    public CTDonHang(String maDH, String maSP, String tenSP, String hinhAnh, String soLuong, String donGia) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "CTDonHang{" +
                "maDH='" + maDH + '\'' +
                ", maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", donGia='" + donGia + '\'' +
                '}';
    }
}
