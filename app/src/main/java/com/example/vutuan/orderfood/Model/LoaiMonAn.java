package com.example.vutuan.orderfood.Model;

/**
 * Created by vutuan on 05/07/2017.
 */

public class LoaiMonAn {
    private int MaMonAn;
    private String TenMonAn;

    public LoaiMonAn(int maMonAn, String tenMonAn) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }
}
