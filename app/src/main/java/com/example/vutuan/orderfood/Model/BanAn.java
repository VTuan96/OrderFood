package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 04/07/2017.
 */

public class BanAn  {
    public int MaBanAn;
    public String TenBanAN;
    public String TrangThai;

    public BanAn(int maBanAn,String tenBanAN, String trangThai) {
        TenBanAN = tenBanAN;
        TrangThai = trangThai;
        MaBanAn=maBanAn;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getMaBanAn() {
        return MaBanAn;
    }

    public void setMaBanAn(int maBanAn) {
        MaBanAn = maBanAn;
    }

    public String getTenBanAN() {
        return TenBanAN;
    }

    public void setTenBanAN(String tenBanAN) {
        TenBanAN = tenBanAN;
    }


}
