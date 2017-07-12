package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vutuan.orderfood.Model.GoiMonAn;

/**
 * Created by vutuan on 08/07/2017.
 */

public class DBGoiMonAn {
    SQLiteDatabase database;
    public DBGoiMonAn(Context context){
        database=new CreateDatabase(context).open();
    }

    public long themGoiMonAn(GoiMonAn goiMonAn){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_GOIMONAN_MABANAN,goiMonAn.getMaBanAn());
        values.put(CreateDatabase.TB_GOIMONAN_MANV,goiMonAn.getMaNV());
        values.put(CreateDatabase.TB_GOIMONAN_NGAYGOI,goiMonAn.getNgayGio());
        values.put(CreateDatabase.TB_GOIMONAN_TRANGTHAI,"false");

        return database.insert(CreateDatabase.TB_GOIMONAN,null,values);
    }

    public boolean checkGoiMonAn(int maBanAn, int maNV){
        boolean result=false;
        String query="SELECT * FROM "+CreateDatabase.TB_GOIMONAN+ " WHERE "+CreateDatabase.TB_GOIMONAN_MABANAN+
                " = "+maBanAn+" AND "+CreateDatabase.TB_GOIMONAN_MANV+ " = "+maNV+"";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()==0){
            result=false;
        }else {
            result=true;
        }
        return result;
    }

    public String checkTrangThaiGoiMon(int maGoiMon){
        String result="false";
        String query="SELECT * FROM "+CreateDatabase.TB_GOIMONAN+ " WHERE "+CreateDatabase.TB_GOIMONAN_MAGOIMONAN+
                " = '"+maGoiMon+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                result=cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_GOIMONAN_TRANGTHAI));
            }
        }
        return result;
    }

    public int getMaGoiMon(int maBanAn){
        int maGoiMon=0;
        String query="SELECT * FROM "+CreateDatabase.TB_GOIMONAN+ " WHERE "+CreateDatabase.TB_GOIMONAN_MABANAN+ " = '"+maBanAn+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                maGoiMon=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMONAN_MAGOIMONAN));
            }
            cursor.close();
        }
        return  maGoiMon;
    }

    public int updateTrangThaiGoiMon(int maGoiMon,String trangThai){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_GOIMONAN_TRANGTHAI,trangThai);
        return database.update(CreateDatabase.TB_GOIMONAN,values,CreateDatabase.TB_GOIMONAN_MAGOIMONAN+" = '"+maGoiMon+"'",null);
    }

}
