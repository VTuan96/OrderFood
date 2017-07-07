package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vutuan.orderfood.Model.LoaiMonAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 05/07/2017.
 */

public class DBLoaiMonAn {
    SQLiteDatabase database;
    public DBLoaiMonAn(Context context){
        database=new CreateDatabase(context).open();
    }

    //them loai mon an
    public long themLoaiMonAn(String tenLoaiMonAn){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_LOAIMONAN_TENLOAIMONAN,tenLoaiMonAn);
        return database.insert(CreateDatabase.TB_LOAIMONAN,null,values);
    }

    //kiem tra loai mon an trong database
    public boolean checkLoaiMonAn(String tenLoaiMonAn){
        String query="SELECT * FROM "+CreateDatabase.TB_LOAIMONAN+ " WHERE "+CreateDatabase.TB_LOAIMONAN_TENLOAIMONAN+ " = '"+tenLoaiMonAn + "'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()!=0){
            return true; //neu co loai mon an nay trong database
        } else {
            return false; // neu chua co loai mon an nay
        }
    }

    //lay danh sach cac loai mon an
    public List<LoaiMonAn> getListLoaiMonAn(){
        List<LoaiMonAn> mList=new ArrayList<>();
        String query="SELECT * FROM "+ CreateDatabase.TB_LOAIMONAN;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int maLoaiMonAn=cursor.getInt(0);
                String tenLoaiMonAn=cursor.getString(1);
                LoaiMonAn loaiMonAn=new LoaiMonAn(maLoaiMonAn,tenLoaiMonAn);
                mList.add(loaiMonAn);
            }
        }
        return mList;
    }

    //lay danh sach cac loai mon an co hinh anh
    public String getImageLoaiMonAn(int maLoaiMonAn){
        String hinhAnh="";
        String query="SELECT * FROM "+CreateDatabase.TB_MONAN+ " WHERE "+CreateDatabase.TB_MONAN_MALOAIMONAN+ " = '"+maLoaiMonAn + "'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                hinhAnh=cursor.getString(3);
            }
        }
        return hinhAnh;
    }
}
