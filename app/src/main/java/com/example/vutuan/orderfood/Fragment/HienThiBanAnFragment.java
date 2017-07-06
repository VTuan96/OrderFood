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
import android.widget.Toast;

import com.example.vutuan.orderfood.Adapter.AdapterBanAn;
import com.example.vutuan.orderfood.Model.BanAn;
import com.example.vutuan.orderfood.ThemBanAnActivity;
import com.example.vutuan.orderfood.Database.DBBanAn;
import com.example.vutuan.orderfood.R;

import java.util.ArrayList;

/**
 * Created by vutuan on 04/07/2017.
 */

public class HienThiBanAnFragment extends Fragment {
    private static final int REQUESTCODE =111;
    private GridView gvBanAn;
    private ArrayList<BanAn> mListBanAn;
    private AdapterBanAn adapterBanAn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_hien_thi_ban_an,container,false);
        setHasOptionsMenu(true);

        gvBanAn= (GridView) view.findViewById(R.id.gvBanAn);
        setupAdapter();

        return view;
    }

    private void setupAdapter(){
        mListBanAn=new ArrayList<>();
        DBBanAn dbBanAn=new DBBanAn(getContext());
        mListBanAn= (ArrayList<BanAn>) dbBanAn.getListBanAn();
        adapterBanAn=new AdapterBanAn(getContext(),mListBanAn);
        gvBanAn.setAdapter(adapterBanAn);
        adapterBanAn.notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trangchu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mnu_themBanAn){
            Intent intent=new Intent(getActivity(), ThemBanAnActivity.class);
            startActivityForResult(intent,REQUESTCODE);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUESTCODE && resultCode== Activity.RESULT_OK){
            setupAdapter();
            Toast.makeText(getContext(),R.string.themBanAnThanhCong,Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getContext(),R.string.themBanAnThatBai,Toast.LENGTH_LONG).show();
        }
    }
}
