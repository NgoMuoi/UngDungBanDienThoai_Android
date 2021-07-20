package com.example.phoneshop.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Locale;

@Generated("jsonschema2pojo")
public class SanPham implements Serializable {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("TenSP")
    @Expose
    private String tenSP;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("GiaBan")
    @Expose
    private String giaBan;
    @SerializedName("MoTa")
    @Expose
    private String moTa;
    @SerializedName("IDLoai")
    @Expose
    private String iDLoai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getIDLoai() {
        return iDLoai;
    }

    public void setIDLoai(String iDLoai) {
        this.iDLoai = iDLoai;
    }

    public SanPham(String id, String tenSP, String hinhAnh, String giaBan, String moTa, String iDLoai) {
        this.id = id;
        this.tenSP = tenSP;
        this.hinhAnh = hinhAnh;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.iDLoai = iDLoai;
    }
    public SanPham(String id, String tenSP, String hinhAnh, String giaBan) {
        this.id = id;
        this.tenSP = tenSP;
        this.hinhAnh = hinhAnh;
        this.giaBan = giaBan;
    }
    public SanPham() {
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id='" + id + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", giaBan='" + giaBan + '\'' +
                ", moTa='" + moTa + '\'' +
                ", iDLoai='" + iDLoai + '\'' +
                '}';
    }
}