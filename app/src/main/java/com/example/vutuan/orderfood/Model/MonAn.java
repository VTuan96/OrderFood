package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 06/07/2017.
 */

public class MonAn {
    private int MaMonAn, MaLoaiMonAn;
    private String TenMonAn;
    private String GiaTien, HinhAnh;

    public MonAn(int maLoaiMonAn, String tenMonAn, String giaTien, String hinhAnh) {
        MaLoaiMonAn = maLoaiMonAn;
        TenMonAn = tenMonAn;
        GiaTien = giaTien;
        HinhAnh = hinhAnh;
    }

    public MonAn(int maMonAn, int maLoaiMonAn, String tenMonAn, String giaTien, String hinhAnh) {
        MaMonAn = maMonAn;
        MaLoaiMonAn = maLoaiMonAn;
        TenMonAn = tenMonAn;
        GiaTien = giaTien;
        HinhAnh = hinhAnh;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        MaMonAn = maMonAn;
    }

    public int getMaLoaiMonAn() {
        return MaLoaiMonAn;
    }

    public void setMaLoaiMonAn(int maLoaiMonAn) {
        MaLoaiMonAn = maLoaiMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
