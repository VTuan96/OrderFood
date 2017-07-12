package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vutuan.orderfood.Model.ChiTietGoiMon;
import com.example.vutuan.orderfood.Model.GoiMonAn;
import com.example.vutuan.orderfood.Model.ThanhToan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 09/07/2017.
 */

public class DBChiTietGoiMon {
    SQLiteDatabase database;
    private DBMonAn dbMonAn;

    public DBChiTietGoiMon (Context context){
        database=new CreateDatabase(context).open();
        dbMonAn=new DBMonAn(context);
    }

    public long themChiTietGoiMonAn(ChiTietGoiMon chiTietGoiMon){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_CHITIETGOIMON_MAGOIMON,chiTietGoiMon.getMaGoiMon());
        values.put(CreateDatabase.TB_CHITIETGOIMON_MAMONAN,chiTietGoiMon.getMaMonAn());
        values.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG,chiTietGoiMon.getSoLuong());

        return database.insert(CreateDatabase.TB_CHITIETGOIMON,null,values);
    }

    public boolean checkMaMonAn(int maGoiMonAn, int maMonAn){
        String query="SELECT * FROM "+CreateDatabase.TB_CHITIETGOIMON+ " WHERE "+CreateDatabase.TB_CHITIETGOIMON_MAGOIMON+ " = '"+
                maGoiMonAn+"'"+ " AND "+CreateDatabase.TB_CHITIETGOIMON_MAMONAN+ " = '"+maMonAn+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()!=0){
            return true; // co ma mon an trong database
        } else {
            return false; //khong co ma mon an trong database
        }
    }


    public void updateSoLuong(int maGoiMonAn, int maMonAn, int soLuong){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG,soLuong);
        int result=database.update(CreateDatabase.TB_CHITIETGOIMON,values,CreateDatabase.TB_CHITIETGOIMON_MAGOIMON+ " = '"+
                maGoiMonAn+"'"+ " AND "+CreateDatabase.TB_CHITIETGOIMON_MAMONAN+ " = '"+maMonAn+"'",null);
    }

    public int getSoLuong(int maGoiMon, int maMonAn){
        int soLuong=0;
        String query="SELECT * FROM "+CreateDatabase.TB_CHITIETGOIMON+ " WHERE "+CreateDatabase.TB_CHITIETGOIMON_MAGOIMON+ " = '"+
                maGoiMon+"'"+ " AND "+CreateDatabase.TB_CHITIETGOIMON_MAMONAN+ " = '"+maMonAn+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                soLuong=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));
            }
        }

        return soLuong;
    }

    public List<ChiTietGoiMon> getListChiTietGoiMon(){
        List<ChiTietGoiMon> mList=new ArrayList<>();
        String query="SELECT * FROM "+CreateDatabase.TB_CHITIETGOIMON ;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int maGoiMon=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_MAGOIMON));
                int maMonAn=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_MAMONAN));
                int soLuong=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));
                ChiTietGoiMon chiTietGoiMon=new ChiTietGoiMon(maGoiMon,maMonAn,soLuong);
                mList.add(chiTietGoiMon);
            }
            cursor.close();
        }
        return mList;
    }

    public List<ChiTietGoiMon> getListChiTietGoiMon(int maGoiMon){
        List<ChiTietGoiMon> mList=new ArrayList<>();
        String query="SELECT * FROM "+CreateDatabase.TB_CHITIETGOIMON + " WHERE "+CreateDatabase.TB_CHITIETGOIMON_MAGOIMON+" = '"+maGoiMon+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            Log.d("count",cursor.getCount()+"");
            while (cursor.moveToNext()){
                int maMonAn=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_MAMONAN));
                int soLuong=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));
                ChiTietGoiMon chiTietGoiMon=new ChiTietGoiMon(maGoiMon,maMonAn,soLuong);
                mList.add(chiTietGoiMon);
            }
            cursor.close();
        }
        return mList;
    }

    public int deleteChiTietGoiMon(int maGoiMon){
        int result= database.delete(CreateDatabase.TB_CHITIETGOIMON,CreateDatabase.TB_CHITIETGOIMON_MAGOIMON+" = '"+maGoiMon+"'",null);
        if (result!=0){
            return result;
        } else {
            return 0;
        }
    }

    public int deleteAllChiTietGoiMon(){
        return database.delete(CreateDatabase.TB_CHITIETGOIMON,null,null);
    }

}
