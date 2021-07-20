package com.example.phoneshop.api;

import com.example.phoneshop.model.Banner;
import com.example.phoneshop.model.CTDonHang;
import com.example.phoneshop.model.DonHang;
import com.example.phoneshop.model.LoaiSP;
import com.example.phoneshop.model.SanPham;
import com.example.phoneshop.model.TaiKhoan;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("getBanner.php")
    Call<List<Banner>> getBanner();

    @GET("getLoai.php")
    Call<List<LoaiSP>> getLoai();

    @GET("getSanPhamMoiNhat.php")
    Call<List<SanPham>> getSPNew();

    @GET("getSPHot.php")
    Call<List<SanPham>> getSPHot();

    @GET("getAllSP.php")
    Call<List<SanPham>> getAllSP();

    @FormUrlEncoded
    @POST("login.php")
    Call<List<TaiKhoan>> login (@Field("taikhoan") String taikhoan,
                                @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("getUser.php")
    Call<List<TaiKhoan>> getTaikhoan(@Field("taikhoan") String taikhoan,
                                    @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("register.php")
    Call<String> dangky(
            @Field("email") String email,
            @Field("hoten") String hoten,
            @Field("taikhoan") String taikhoan,
            @Field("matkhau") String matkhau,
            @Field("diachi") String diachi,
            @Field("sdt") String sdt);

    @FormUrlEncoded
    @POST("getTest.php")
    Call<List<SanPham>> getSanPhamTheoLoai (@Field("idLoai") String idLoai);

    @FormUrlEncoded
    @POST("getSPQC.php")
    Call<List<SanPham>> getSanPhamTheoQC(@Field("idQuangcao") String idQuangcao);

    @FormUrlEncoded
    @POST("getSPDH.php")
    Call<List<SanPham>> getSanPhamTheoDH(@Field("idDH") String idDH);

    @FormUrlEncoded
    @POST("getSanPhamTheoID.php")
    Call<List<SanPham>> getSanPham(@Field("idSanPham") String idSanPham);

    @FormUrlEncoded
    @POST("searchSanpham.php")
    Call<List<SanPham>> getSearch(@Field("key") String key);

    @FormUrlEncoded
    @POST("donhang.php")
    Call<String> taodonhang(
            @Field("nguoinhan") String nguoinhan,
            @Field("sdtgiao") String sdtgiao,
            @Field("diachigiao") String diachigiao,
            @Field("ngaygiao") String ngaygiao,
            @Field("idKH") String idKH);

    @FormUrlEncoded
    @POST("getDonHang.php")
    Call<List<DonHang>> getDonHang (@Field("MaKH") String MaKH);

    @FormUrlEncoded
    @POST("getCTDonHang.php")
    Call<List<CTDonHang>> getCTDonHang (@Field("MaDH") String MaDH);
}
