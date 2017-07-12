package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 08/07/2017.
 */

public class GoiMonAn {
    private int MaGoiMon, MaBanAn, MaNV;
    private String NgayGio, TrangThai; //TrangThai: true: da thanh toan, false: chua thanh toan

    public int getMaGoiMon() {
        return MaGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        MaGoiMon = maGoiMon;
    }

    public int getMaBanAn() {
        return MaBanAn;
    }

    public void setMaBanAn(int maBanAn) {
        MaBanAn = maBanAn;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getNgayGio() {
        return NgayGio;
    }

    public void setNgayGio(String ngayGio) {
        NgayGio = ngayGio;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public GoiMonAn(int maBanAn, int maNV, String ngayGio, String trangThai) {

        MaBanAn = maBanAn;
        MaNV = maNV;
        NgayGio = ngayGio;
        TrangThai = trangThai;
    }

    public GoiMonAn(int maGoiMon, int maBanAn, int maNV, String ngayGio, String trangThai) {

        MaGoiMon = maGoiMon;
        MaBanAn = maBanAn;
        MaNV = maNV;
        NgayGio = ngayGio;
        TrangThai = trangThai;
    }
}
