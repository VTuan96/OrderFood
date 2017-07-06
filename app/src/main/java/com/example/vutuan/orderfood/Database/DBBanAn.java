package com.example.vutuan.orderfood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vutuan.orderfood.Model.BanAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 04/07/2017.
 */

public class DBBanAn {
    SQLiteDatabase database;
    public DBBanAn(Context context){
        database=new CreateDatabase(context).open();
    }

    public long themBanAn(String tenBanAn){
        ContentValues values=new ContentValues();
        values.put(CreateDatabase.TB_BANAN_TENBANAN,tenBanAn);
        values.put(CreateDatabase.TB_BANAN_TRANGTHAI,"false");

        return database.insert(CreateDatabase.TB_BANAN,null,values);
    }

    //kiem tra ban an co trong database
    public boolean checBanAn(String tenBanAn){
        String query="SELECT * FROM "+CreateDatabase.TB_BANAN+ " WHERE "+CreateDatabase.TB_BANAN_TENBANAN+ " = '"+
                tenBanAn+"'";
        Cursor cursor=database.rawQuery(query,null);
        if (cursor.getCount()!=0){
            return true; //neu co ban an trong database
        } else {
            return false; //neu khong co ban an trong database
        }
    }

    //get List Ban An trong database
    public List<BanAn> getListBanAn(){
        List<BanAn> mList=new ArrayList<>();
        String query="SELECT * FROM "+CreateDatabase.TB_BANAN;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int maBanAn=cursor.getInt(0);
                String tenBanAn=cursor.getString(1);
                String trangThai=cursor.getString(2);

                BanAn banAn=new BanAn(maBanAn,tenBanAn,trangThai);
                mList.add(banAn);
            }
        }
        return mList;
    }


}
