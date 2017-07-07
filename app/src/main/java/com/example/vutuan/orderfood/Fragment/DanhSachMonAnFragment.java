package com.example.vutuan.orderfood.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vutuan.orderfood.Adapter.AdapterDanhSachMonAn;
import com.example.vutuan.orderfood.Database.DBMonAn;
import com.example.vutuan.orderfood.Model.MonAn;
import com.example.vutuan.orderfood.R;

import java.util.List;

/**
 * Created by vutuan on 07/07/2017.
 */

public class DanhSachMonAnFragment extends Fragment {
    private ListView lvDanhSachMonAn;
    private List<MonAn> listMonAn;
    private AdapterDanhSachMonAn adapterDanhSachMonAn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_danh_sach_mon_an,container,false);
        setHasOptionsMenu(true);

        lvDanhSachMonAn= (ListView) view.findViewById(R.id.lvDanhSachMonAn);
        setupAdapter();
        return view;
    }

    private void setupAdapter(){
        Bundle bundle=this.getArguments();
        int maLoaiMonAn=bundle.getInt(ThucDonFragment.MA_LOAI_MON_AN);
        DBMonAn dbMonAn=new DBMonAn(getContext());
        listMonAn=dbMonAn.getListMonAn(maLoaiMonAn);
        adapterDanhSachMonAn=new AdapterDanhSachMonAn(getContext(),listMonAn);
        lvDanhSachMonAn.setAdapter(adapterDanhSachMonAn);
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }
}
