package com.example.vutuan.orderfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.vutuan.orderfood.Adapter.AdapterThucDon;
import com.example.vutuan.orderfood.Database.DBLoaiMonAn;
import com.example.vutuan.orderfood.Model.LoaiMonAn;
import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.ThemThucDonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 04/07/2017.
 */

public class ThucDonFragment extends Fragment {
    public static final int REQUEST_CODE_THUC_DON=111;

    private List<LoaiMonAn> listLoaiMonAn;
    private AdapterThucDon adapterThucDon;
    private GridView gvThucDon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_thucdon,container,false);
        setHasOptionsMenu(true);

        gvThucDon= (GridView) view.findViewById(R.id.gvThucDon);
        setupAdapter();
        return view;
    }

    private void setupAdapter(){
        DBLoaiMonAn dbLoaiMonAn=new DBLoaiMonAn(getContext());
        listLoaiMonAn= dbLoaiMonAn.getListLoaiMonAn();
        adapterThucDon=new AdapterThucDon(getContext(),listLoaiMonAn);
        gvThucDon.setAdapter(adapterThucDon);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_thucdon,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_themThucDon:
                Intent intent=new Intent(getContext(), ThemThucDonActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THUC_DON);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THUC_DON && requestCode== Activity.RESULT_OK && data!=null){
            setupAdapter();
        }
    }
}
