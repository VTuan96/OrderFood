package com.example.vutuan.orderfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vutuan.orderfood.Adapter.AdapterThucDon;
import com.example.vutuan.orderfood.DangNhapActivity;
import com.example.vutuan.orderfood.Database.DBLoaiMonAn;
import com.example.vutuan.orderfood.Database.DBNhanVien;
import com.example.vutuan.orderfood.Model.LoaiMonAn;
import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.ThemThucDonActivity;
import com.example.vutuan.orderfood.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vutuan on 04/07/2017.
 */

public class ThucDonFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final int REQUEST_CODE_THUC_DON=111;

    private List<LoaiMonAn> listLoaiMonAn;
    private AdapterThucDon adapterThucDon;
    private ListView lvThucDon;

    public static final String DANH_SACH_MON_AN="DANH_SACH_MON_AN";
    public static final String MA_LOAI_MON_AN="MA_LOAI_MON_AN";
    public static final String MA_BAN="MA_BAN";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_thucdon,container,false);
        setHasOptionsMenu(true);

        lvThucDon= (ListView) view.findViewById(R.id.lvThucDon);
        setupAdapter();

        lvThucDon.setOnItemClickListener(this);
        return view;
    }

    private void setupAdapter(){
        DBLoaiMonAn dbLoaiMonAn=new DBLoaiMonAn(getContext());
        listLoaiMonAn= dbLoaiMonAn.getListLoaiMonAn();
        adapterThucDon=new AdapterThucDon(getContext(),listLoaiMonAn);
        lvThucDon.setAdapter(adapterThucDon);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Bundle bundle=getArguments();
        if (bundle!=null){
            int maNV=bundle.getInt(DangNhapActivity.MANV);
            Log.d("thuc don",maNV+"");
            if (maNV==1){
                inflater.inflate(R.menu.menu_thucdon,menu);
            }
        }
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
        if (requestCode==REQUEST_CODE_THUC_DON && resultCode== Activity.RESULT_OK && data!=null){
            setupAdapter();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        DanhSachMonAnFragment danhSachMonAnFragment=new DanhSachMonAnFragment();
        LoaiMonAn loaiMonAn=listLoaiMonAn.get(position);
        int maLoaiMonAn=loaiMonAn.getMaMonAn();
        Bundle bundle=new Bundle();
        bundle.putInt(MA_LOAI_MON_AN,maLoaiMonAn);

        Bundle args=this.getArguments();
        if (args!=null){ //nguoi dung bam goi mon
            int maBanAn=args.getInt(ThucDonFragment.MA_BAN);
            int maNV=args.getInt(DangNhapActivity.MANV);
            int maGoiMon=args.getInt(HienThiBanAnFragment.MAGOIMONAN);
            bundle.putInt(MA_BAN,maBanAn);
            bundle.putInt(DangNhapActivity.MANV,maNV);
            bundle.putInt(HienThiBanAnFragment.MAGOIMONAN,maGoiMon);
            Log.d("GOI MON","Nguoi dung goi mon");
        }

        danhSachMonAnFragment.setArguments(bundle);
        transaction.replace(R.id.content,danhSachMonAnFragment,DANH_SACH_MON_AN).addToBackStack(DANH_SACH_MON_AN);
        transaction.commit();

    }
}
