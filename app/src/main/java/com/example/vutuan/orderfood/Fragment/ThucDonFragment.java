package com.example.vutuan.orderfood.Fragment;

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

import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.ThemThucDonActivity;

/**
 * Created by vutuan on 04/07/2017.
 */

public class ThucDonFragment extends Fragment {
    public static final int REQUEST_CODE_THUC_DON=111;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_thucdon,container,false);
        setHasOptionsMenu(true);
        return view;
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
}
