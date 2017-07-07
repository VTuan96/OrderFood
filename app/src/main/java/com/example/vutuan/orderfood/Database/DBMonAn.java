package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vutuan.orderfood.Model.MonAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 06/07/2017.
 */

public class DBMonAn {
    SQLiteDatabase database;
    public DBMonAn(Context context){
        database=new CreateDatabase(context).open();
    }

    public long themMonAn(MonAn monAn){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_MONAN_MALOAIMONAN,monAn.getMaLoaiMonAn());
        values.put(CreateDatabase.TB_MONAN_TENMONAN,monAn.getTenMonAn());
        values.put(CreateDatabase.TB_MONAN_GIATIEN,monAn.getGiaTien());
        values.put(CreateDatabase.TB_MONAN_HINHANH,monAn.getHinhAnh());
        long resutl=database.insert(CreateDatabase.TB_MONAN,null,values);
        return resutl;
    }


    //get list MonAn
    public List<MonAn> getListMonAn(){
        List<MonAn> listMonAn=new ArrayList<>();
        String query="SELECT * FROM "+CreateDatabase.TB_MONAN;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int maMonAn=cursor.getInt(0);
                String tenMonAn=cursor.getString(1);
                int maLoaiMonAn=cursor.getInt(2);
                String hinhAnh=cursor.getString(3);
                String giaTien=cursor.getString(4);
                MonAn monAn=new MonAn(maMonAn,maLoaiMonAn,tenMonAn,giaTien,hinhAnh);
                listMonAn.add(monAn);
            }
        }
        return listMonAn;
    }

    //get list MonAn theo ma loai mon an
    public List<MonAn> getListMonAn(int maLoaiMonAn){
        List<MonAn> listMonAn=new ArrayList<>();
        String query="SELECT * FROM "+CreateDatabase.TB_MONAN + " WHERE "+CreateDatabase.TB_MONAN_MALOAIMONAN+ " = '"+maLoaiMonAn+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int maMonAn=cursor.getInt(0);
                String tenMonAn=cursor.getString(1);
                String hinhAnh=cursor.getString(3);
                String giaTien=cursor.getString(4);
                MonAn monAn=new MonAn(maMonAn,maLoaiMonAn,tenMonAn,giaTien,hinhAnh);
                listMonAn.add(monAn);
            }
        }
        return listMonAn;
    }
}
