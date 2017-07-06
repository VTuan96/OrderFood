package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vutuan.orderfood.Model.NhanVien;

/**
 * Created by vutuan on 21/06/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String TB_NHANVIEN="NHANVIEN";
    public static final String TB_BANAN="BANAN";
    public static final String TB_MONAN="MONAN";
    public static final String TB_LOAIMONAN="LOAIMONAN";
    public static final String TB_GOIMONAN="GOIMONAN";
    public static final String TB_CHITIETGOIMON="CHITIETGOIMON";

    public static final String TB_NHANVIEN_MANV="MANV";
    public static final String TB_NHANVIEN_TENNV="TENNV";
    public static final String TB_NHANVIEN_GIOITINH="GIOITINH";
    public static final String TB_NHANVIEN_NGAYSINH="NGAYSINH";
    public static final String TB_NHANVIEN_CMND="CMND";
    public static final String TB_NHANVIEN_TENDANGNHAP="TENDANGNHAP";
    public static final String TB_NHANVIEN_MATKHAU="MATKHAU";

    public static final String TB_BANAN_MABANAN="MABANAN";
    public static final String TB_BANAN_TENBANAN="TENBANAN";
    public static final String TB_BANAN_TRANGTHAI="TRANGTHAI";

    public static final String TB_MONAN_MAMONAN="MAMONAN";
    public static final String TB_MONAN_TENMONAN="TENMONAN";
    public static final String TB_MONAN_MALOAIMONAN="MALOAIMONAN";
    public static final String TB_MONAN_GIATIEN="GIATIEN";
    public static final String TB_MONAN_HINHANH="HINHANH";

    public static final String TB_LOAIMONAN_MALOAIMONAN="MALOAIMONAN";
    public static final String TB_LOAIMONAN_TENLOAIMONAN="TENLOAIMONAN";

    public static final String TB_GOIMONAN_MAGOIMONAN="MAGOIMONAN";
    public static final String TB_GOIMONAN_MABANAN="MABANAN";
    public static final String TB_GOIMONAN_NGAYGOI="NGAYGOI";
    public static final String TB_GOIMONAN_TRANGTHAI="TRANGTHAI";

    public static final String TB_CHITIETGOIMON_MAGOIMON="MAGOIMON";
    public static final String TB_CHITIETGOIMON_MAMONAN="MAMONAN";
    public static final String TB_CHITIETGOIMON_SOLUONG="SOLUONG";


    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_NhanVien="CREATE TABLE "+TB_NHANVIEN+ " ( "+TB_NHANVIEN_MANV+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+TB_NHANVIEN_TENNV+ " TEXT, "+
                TB_NHANVIEN_TENDANGNHAP+ " TEXT, "+TB_NHANVIEN_MATKHAU+ " TEXT, "+TB_NHANVIEN_GIOITINH+ " BOOLEAN, "+TB_NHANVIEN_NGAYSINH+ " TEXT, "+
                TB_NHANVIEN_CMND+ " INTEGER ) ";
        String db_BanAn="CREATE TABLE "+TB_BANAN+ " ( "+TB_BANAN_MABANAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+TB_BANAN_TENBANAN+ " TEXT, "+
                TB_BANAN_TRANGTHAI+ " TEXT )";
        String db_MonAn="CREATE TABLE "+TB_MONAN+ " ( "+TB_MONAN_MAMONAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+TB_MONAN_TENMONAN+" TEXT, "+
                TB_MONAN_MALOAIMONAN+ " INTGER, "+TB_MONAN_HINHANH+ " TEXT, "+TB_MONAN_GIATIEN+ " TEXT )";
        String db_LoaiMonAn="CREATE TABLE "+TB_LOAIMONAN+ " ( "+TB_LOAIMONAN_MALOAIMONAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+TB_LOAIMONAN_TENLOAIMONAN+ " TEXT )";
        String db_GoiMonAn="CREATE TABLE "+TB_GOIMONAN+ " ( "+TB_GOIMONAN_MAGOIMONAN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+TB_GOIMONAN_MABANAN+" INTEGER, "+
                TB_GOIMONAN_NGAYGOI+ " TEXT, "+TB_GOIMONAN_TRANGTHAI+ "BOOLEAN )";
        String db_ChiTietGoiMon="CREATE TABLE "+TB_CHITIETGOIMON+ " ( "+TB_CHITIETGOIMON_MAGOIMON+ "INTEGER, "+TB_CHITIETGOIMON_MAMONAN+ " INTEGER, "+
                TB_CHITIETGOIMON_SOLUONG+ " INTEGER )";

        db.execSQL(db_NhanVien);
        db.execSQL(db_BanAn);
        db.execSQL(db_MonAn);
        db.execSQL(db_LoaiMonAn);
        db.execSQL(db_GoiMonAn);
        db.execSQL(db_ChiTietGoiMon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open(){
        SQLiteDatabase database=this.getWritableDatabase();
        return database;
    }

}
