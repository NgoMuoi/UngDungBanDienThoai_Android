package com.example.phoneshop.model;

public class GioHang {
    public int soluong;
    public String id,tensp,hinhanh;
    public long gia;

    public GioHang(String id, int soluong, String tensp, String hinhanh, long gia) {
        this.id = id;
        this.soluong = soluong;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.gia = gia;
    }

    public GioHang() {
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "id=" + id +
                ", soluong=" + soluong +
                ", tensp='" + tensp + '\'' +
                ", hinhanh='" + hinhanh + '\'' +
                ", gia=" + gia +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
}
