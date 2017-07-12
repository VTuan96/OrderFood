package com.example.vutuan.orderfood.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vutuan.orderfood.Adapter.AdapterThanhToan;
import com.example.vutuan.orderfood.DangNhapActivity;
import com.example.vutuan.orderfood.Database.DBBanAn;
import com.example.vutuan.orderfood.Database.DBChiTietGoiMon;
import com.example.vutuan.orderfood.Database.DBGoiMonAn;
import com.example.vutuan.orderfood.Database.DBMonAn;
import com.example.vutuan.orderfood.Model.ChiTietGoiMon;
import com.example.vutuan.orderfood.Model.ThanhToan;
import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanFragment extends Fragment implements View.OnClickListener {
    private AdapterThanhToan adapterThanhToan;
    private List<ThanhToan> listThanhToan;
    private List<ChiTietGoiMon> listChiTietGoiMon;
    private ListView lvThanhToan;
    private TextView txtTongCong;
    private Button btnThanhToan;

    private int maGoiMon, maNV, maBanAn;
    private DBChiTietGoiMon dbChiTietGoiMon;
    private DBMonAn dbMonAn;

    private int TongCong=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        lvThanhToan= (ListView) view.findViewById(R.id.lvThanhToan);
        txtTongCong= (TextView) view.findViewById(R.id.txtTongCong);
        btnThanhToan= (Button) view.findViewById(R.id.btnThanhToan);

        getArgument();
        setupAdapter();
        txtTongCong.setText(TongCong+"đ");

        btnThanhToan.setOnClickListener(this);
        return view;
    }

    private void setupAdapter(){
        dbMonAn=new DBMonAn(getContext());
        dbChiTietGoiMon=new DBChiTietGoiMon(getContext());
        listThanhToan=new ArrayList<>();
        listChiTietGoiMon=dbChiTietGoiMon.getListChiTietGoiMon(maGoiMon);
        int length=listChiTietGoiMon.size();
        Log.d("size", listChiTietGoiMon.size()+"");
        for (int i=0;i<length;i++){
            ChiTietGoiMon chiTietGoiMon=listChiTietGoiMon.get(i);
            int maMonAn=chiTietGoiMon.getMaMonAn();
            Log.d("ma goi mon", chiTietGoiMon.getMaGoiMon()+"");
            String tenMonAn=dbMonAn.getTenMonAn(maMonAn);
            Log.d("ten mon an",tenMonAn);
            int giaCa=dbMonAn.getGiaTienMonAn(maMonAn);
            ThanhToan thanhToan=new ThanhToan(tenMonAn,chiTietGoiMon.getSoLuong(),giaCa);
            listThanhToan.add(thanhToan);
            TongCong+=thanhToan.giaThanh();
        }

        adapterThanhToan=new AdapterThanhToan(getContext(),listThanhToan);
        lvThanhToan.setAdapter(adapterThanhToan);
        adapterThanhToan.notifyDataSetChanged();
    }

    private void getArgument(){
        Bundle bundle=getArguments();
        if (bundle!=null){
            maBanAn=bundle.getInt(ThucDonFragment.MA_BAN);
            maGoiMon=bundle.getInt(HienThiBanAnFragment.MAGOIMONAN);
            Log.d("MAGOIMON",maGoiMon+"");
            maNV=bundle.getInt(DangNhapActivity.MANV);
        }
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnThanhToan:
                //Cap nhat trang thai ban an thanh false
                DBBanAn dbBanAn=new DBBanAn(getContext());
                dbBanAn.updateTrangThaiBanAn(maBanAn,"false");
                DBGoiMonAn dbGoiMonAn=new DBGoiMonAn(getContext());
                dbGoiMonAn.updateTrangThaiGoiMon(maGoiMon,"true");
                Log.d("ma goi mon: ",maGoiMon+"");
                Toast.makeText(getContext(),getResources().getString(R.string.daThanhToan),Toast.LENGTH_LONG).show();
                if (dbGoiMonAn.checkTrangThaiGoiMon(maGoiMon).equals("true")){
                    int delete=dbChiTietGoiMon.deleteChiTietGoiMon(maGoiMon);
                    setupAdapter();
                    if (delete!=0){
                        Intent intent=new Intent(getContext(),DangNhapActivity.class);
                        startActivity(intent);
                    }
                }

                break;
        }
    }
}
