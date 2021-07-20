package com.example.phoneshop.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class LoaiSP implements Serializable {

    @SerializedName("IDLoai")
    @Expose
    private String iDLoai;
    @SerializedName("TenLoai")
    @Expose
    private String tenLoai;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;

    public String getIDLoai() {
        return iDLoai;
    }

    public void setIDLoai(String iDLoai) {
        this.iDLoai = iDLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}