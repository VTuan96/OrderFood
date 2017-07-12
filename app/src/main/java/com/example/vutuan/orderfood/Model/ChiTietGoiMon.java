package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 08/07/2017.
 */

public class ChiTietGoiMon {
    private int MaGoiMon, MaMonAn, SoLuong;

    public ChiTietGoiMon(int maMonAn, int soLuong) {
        MaMonAn = maMonAn;
        SoLuong = soLuong;
    }

    public ChiTietGoiMon(int maGoiMon, int maMonAn, int soLuong) {
        MaGoiMon = maGoiMon;
        MaMonAn = maMonAn;
        SoLuong = soLuong;
    }

    public int getMaGoiMon() {
        return MaGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        MaGoiMon = maGoiMon;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        MaMonAn = maMonAn;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return " ma goi mon: "+MaGoiMon+", ma mon an: "+MaMonAn+", so luong: "+SoLuong;
    }
}
