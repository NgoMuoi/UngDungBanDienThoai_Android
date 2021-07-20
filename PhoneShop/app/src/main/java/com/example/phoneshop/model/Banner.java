package com.example.phoneshop.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("jsonschema2pojo")
public class Banner implements Serializable {

@SerializedName("IDBanner")
@Expose
private String iDBanner;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;
@SerializedName("NoiDung")
@Expose
private String noiDung;
@SerializedName("IDSanPham")
@Expose
private String iDSanPham;

public String getIDBanner() {
return iDBanner;
}

public void setIDBanner(String iDBanner) {
this.iDBanner = iDBanner;
}

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

public String getNoiDung() {
return noiDung;
}

public void setNoiDung(String noiDung) {
this.noiDung = noiDung;
}

public String getIDSanPham() {
return iDSanPham;
}

public void setIDSanPham(String iDSanPham) {
this.iDSanPham = iDSanPham;
}

}