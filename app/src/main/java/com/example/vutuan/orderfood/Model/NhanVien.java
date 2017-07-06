package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 22/06/2017.
 */

public class NhanVien {
    int MaNV;
    String TenNV;
    String TenDangNhap;
    String MatKhau;
    boolean GioiTinh;
    String NgaySinh;
    int CMND;

    public NhanVien(String tenNV, String tenDangNhap, String matKhau, boolean gioiTinh, String ngaySinh, int CMND) {
        TenNV = tenNV;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        this.CMND = CMND;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public int getCMND() {
        return CMND;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }
}
