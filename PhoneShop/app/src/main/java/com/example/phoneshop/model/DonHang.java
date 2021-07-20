package com.example.phoneshop.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class DonHang implements Serializable {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("NguoiNhan")
    @Expose
    private String nguoiNhan;
    @SerializedName("SDTgiaohang")
    @Expose
    private String sDTgiaohang;
    @SerializedName("DiaChigiaohang")
    @Expose
    private String diaChigiaohang;
    @SerializedName("NgayDat")
    @Expose
    private String ngayDat;
    @SerializedName("NgayGiao")
    @Expose
    private String ngayGiao;
    @SerializedName("TinhTrang")
    @Expose
    private String tinhTrang;
    @SerializedName("MaKH")
    @Expose
    private String maKH;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getSDTgiaohang() {
        return sDTgiaohang;
    }

    public void setSDTgiaohang(String sDTgiaohang) {
        this.sDTgiaohang = sDTgiaohang;
    }

    public String getDiaChigiaohang() {
        return diaChigiaohang;
    }

    public void setDiaChigiaohang(String diaChigiaohang) {
        this.diaChigiaohang = diaChigiaohang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public DonHang() {
    }

    public DonHang(String id, String nguoiNhan, String sDTgiaohang, String diaChigiaohang, String ngayDat, String ngayGiao, String tinhTrang, String maKH) {
        this.id = id;
        this.nguoiNhan = nguoiNhan;
        this.sDTgiaohang = sDTgiaohang;
        this.diaChigiaohang = diaChigiaohang;
        this.ngayDat = ngayDat;
        this.ngayGiao = ngayGiao;
        this.tinhTrang = tinhTrang;
        this.maKH = maKH;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "id='" + id + '\'' +
                ", nguoiNhan='" + nguoiNhan + '\'' +
                ", sDTgiaohang='" + sDTgiaohang + '\'' +
                ", diaChigiaohang='" + diaChigiaohang + '\'' +
                ", ngayDat='" + ngayDat + '\'' +
                ", ngayGiao='" + ngayGiao + '\'' +
                ", tinhTrang='" + tinhTrang + '\'' +
                ", maKH='" + maKH + '\'' +
                '}';
    }
}