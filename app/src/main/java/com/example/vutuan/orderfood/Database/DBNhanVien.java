package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vutuan.orderfood.Model.NhanVien;

/**
 * Created by vutuan on 04/07/2017.
 */

public class DBNhanVien {
    SQLiteDatabase database;
    public DBNhanVien(Context context){
        database=new CreateDatabase(context).open();
    }

    //kiem tra dang nhap
    public boolean checkLogin(String tenNhanVien, String matKhau) {
        String query = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TENDANGNHAP + " = '" + tenNhanVien +
                "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + matKhau + "'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
    //insert NhanVien
    public long insertNhanVien(NhanVien nhanVien){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_NHANVIEN_TENNV,nhanVien.getTenNV());
        values.put(CreateDatabase.TB_NHANVIEN_TENDANGNHAP,nhanVien.getTenDangNhap());
        values.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVien.getMatKhau());
        values.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVien.isGioiTinh());
        values.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVien.getNgaySinh());
        values.put(CreateDatabase.TB_NHANVIEN_CMND,nhanVien.getCMND());
        long index= database.insert(CreateDatabase.TB_NHANVIEN,null,values);
        return index;
    }

}
