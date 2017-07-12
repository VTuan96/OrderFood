package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 11/07/2017.
 */

public class ThanhToan {
    private String TenMonAn;
    private int SoLuong, GiaCa;

    public ThanhToan(String tenMonAn, int soLuong, int giaCa) {
        TenMonAn = tenMonAn;
        SoLuong = soLuong;
        GiaCa = giaCa;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGiaCa() {
        return GiaCa;
    }

    public void setGiaCa(int giaCa) {
        GiaCa = giaCa;
    }

    @Override
    public String toString() {
        return "ten mon an: "+TenMonAn+", so luong: "+SoLuong+", gia ca: "+GiaCa;
    }

    public int giaThanh(){
        return SoLuong*GiaCa;
    }
}
