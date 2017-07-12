package com.example.vutuan.orderfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vutuan.orderfood.Adapter.AdapterDanhSachMonAn;
import com.example.vutuan.orderfood.DangNhapActivity;
import com.example.vutuan.orderfood.Database.DBChiTietGoiMon;
import com.example.vutuan.orderfood.Database.DBMonAn;
import com.example.vutuan.orderfood.Model.ChiTietGoiMon;
import com.example.vutuan.orderfood.Model.GoiMonAn;
import com.example.vutuan.orderfood.Model.MonAn;
import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.ThemSoLuongActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 07/07/2017.
 */

public class DanhSachMonAnFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final int REQUEST_CODE_SOLUONG=444;
    private ListView lvDanhSachMonAn;
    private List<MonAn> listMonAn;
    private AdapterDanhSachMonAn adapterDanhSachMonAn;

    private int maBan,maLoaiMonAn,maNV, maGoiMon;
    private int soLuong;
    private MonAn monAn;
    private DBChiTietGoiMon dbChiTietGoiMon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_danh_sach_mon_an,container,false);
        setHasOptionsMenu(true);
        dbChiTietGoiMon=new DBChiTietGoiMon(getContext());

        lvDanhSachMonAn= (ListView) view.findViewById(R.id.lvDanhSachMonAn);
        getDataFromThucDonFragment();
        setupAdapter();
        return view;
    }

    private void setupAdapter(){
        DBMonAn dbMonAn=new DBMonAn(getContext());
        listMonAn=dbMonAn.getListMonAn(maLoaiMonAn);
        adapterDanhSachMonAn=new AdapterDanhSachMonAn(getContext(),listMonAn);
        lvDanhSachMonAn.setAdapter(adapterDanhSachMonAn);
        lvDanhSachMonAn.setOnItemClickListener(this);
    }

    private void getDataFromThucDonFragment(){
        Bundle bundle=this.getArguments();
        maGoiMon=bundle.getInt(HienThiBanAnFragment.MAGOIMONAN);
        maLoaiMonAn=bundle.getInt(ThucDonFragment.MA_LOAI_MON_AN);
        maBan=bundle.getInt(ThucDonFragment.MA_BAN);
        maNV=bundle.getInt(DangNhapActivity.MANV);

    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        monAn=listMonAn.get(position);
        if (maBan!=0){
            Intent intent=new Intent(getContext(), ThemSoLuongActivity.class);
            startActivityForResult(intent,REQUEST_CODE_SOLUONG);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_SOLUONG && resultCode== Activity.RESULT_OK && data!=null){
            soLuong=data.getIntExtra(ThemSoLuongActivity.SO_LUONG,0);
            int maMonAn=monAn.getMaMonAn();

            //kiem tra ban chi tiet mon an voi ma goi mon an va ma mon an
            boolean check=dbChiTietGoiMon.checkMaMonAn(maGoiMon,monAn.getMaMonAn());
            if (check){ //neu da co ma mon an nay
                int soLuongBefore=dbChiTietGoiMon.getSoLuong(maGoiMon,maMonAn);
                soLuong+=soLuongBefore;
            } else {
                ChiTietGoiMon chiTietGoiMon=new ChiTietGoiMon(maGoiMon,maMonAn,soLuong);
                long added=dbChiTietGoiMon.themChiTietGoiMonAn(chiTietGoiMon);
                if (added!=0){
                    Toast.makeText(getContext(),R.string.themThanhCong,Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),R.string.themThatBai,Toast.LENGTH_LONG).show();
                }
            }

            dbChiTietGoiMon.updateSoLuong(maGoiMon,monAn.getMaMonAn(),soLuong);
            Log.d("ma ban",maBan+", ma nv:"+maNV+", ma goi mon: "+maGoiMon+", ma mon an:"+ maMonAn +", so luong: "+soLuong);
        }
    }
}
