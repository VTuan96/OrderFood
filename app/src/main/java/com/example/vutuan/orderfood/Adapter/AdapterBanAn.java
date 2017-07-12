package com.example.vutuan.orderfood.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vutuan.orderfood.DangNhapActivity;
import com.example.vutuan.orderfood.Database.DBBanAn;
import com.example.vutuan.orderfood.Database.DBChiTietGoiMon;
import com.example.vutuan.orderfood.Database.DBGoiMonAn;
import com.example.vutuan.orderfood.Fragment.HienThiBanAnFragment;
import com.example.vutuan.orderfood.Fragment.ThanhToanFragment;
import com.example.vutuan.orderfood.Fragment.ThucDonFragment;
import com.example.vutuan.orderfood.Model.BanAn;
import com.example.vutuan.orderfood.Model.ChiTietGoiMon;
import com.example.vutuan.orderfood.Model.GoiMonAn;
import com.example.vutuan.orderfood.R;
import com.example.vutuan.orderfood.TrangChuActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vutuan on 05/07/2017.
 */

public class AdapterBanAn extends BaseAdapter {
    private Context context;
    private ArrayList<BanAn> mListBanAn;
    private DBGoiMonAn dbGoiMonAn;
    private DBBanAn dbBanAn;
    FragmentManager manager;
    FragmentTransaction transaction;
    private int maNV=0;
    private int maGoiMon=0;

    public AdapterBanAn(Context mContext,ArrayList<BanAn> listBanAn) {
        this.context = mContext;
        this.mListBanAn=listBanAn;
        dbGoiMonAn=new DBGoiMonAn(context);
        dbBanAn=new DBBanAn(context);
        manager=((TrangChuActivity) context).getSupportFragmentManager();
        transaction=manager.beginTransaction();
    }

    @Override
    public int getCount() {
        return mListBanAn.size();
    }

    @Override
    public Object getItem(int position) {
        return mListBanAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListBanAn.get(position).getMaBanAn();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_custom_gridview_ban_an,parent,false);
        }
        final ViewHolder holder=new ViewHolder(convertView);
        holder.txtTenBanAn.setText(mListBanAn.get(position).getTenBanAN());

        final int maBanAn=mListBanAn.get(position).getMaBanAn();
        final String trangThaiBanAn=dbBanAn.getTrangThaiBanAn(maBanAn);
        if (trangThaiBanAn.equals("true")){//neu ban an da goi mon ( da co ng ngoi)
            holder.imgBanAn.setImageResource(R.drawable.banantrue);
        }else { //neu ban an chua goi mon
            holder.imgBanAn.setImageResource(R.drawable.banan);
        }

        holder.imgGoiMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args=((TrangChuActivity)context).getIntent().getBundleExtra(DangNhapActivity.VALUE_DANGNHAP);
                maNV=args.getInt(DangNhapActivity.MANV);

                if (trangThaiBanAn.equals("false")){ //neu ban an chua goi mon
                    //them goi mon an
                    Calendar newCalendar=Calendar.getInstance();
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    String ngayGio=dateFormat.format(newCalendar.getTime()).toString();
                    String trangThai="false";

                    GoiMonAn goiMonAn=new GoiMonAn(maBanAn,maNV,ngayGio,trangThai);
                    maGoiMon=(int) dbGoiMonAn.themGoiMonAn(goiMonAn);

                    //cap nhat trang thai ban an
                    dbBanAn.updateTrangThaiBanAn(maBanAn,"true");
                }


                ThucDonFragment thucDonFragment=new ThucDonFragment();
                Bundle bundle=new Bundle();
                bundle.putInt(DangNhapActivity.MANV,maNV);
                bundle.putInt(ThucDonFragment.MA_BAN,maBanAn);
                maGoiMon=dbGoiMonAn.getMaGoiMon(maBanAn);
                bundle.putInt(HienThiBanAnFragment.MAGOIMONAN,maGoiMon);
                thucDonFragment.setArguments(bundle);
                transaction.replace(R.id.content,thucDonFragment).addToBackStack(ThucDonFragment.DANH_SACH_MON_AN);
                transaction.commit();

            }
        });

        holder.imgThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maGoiMon=dbGoiMonAn.getMaGoiMon(maBanAn);
                Log.d("MAGOIMON",maGoiMon+"");
                ThanhToanFragment thanhToanFragment=new ThanhToanFragment();
                Bundle bundle=new Bundle();
                bundle.putInt(DangNhapActivity.MANV,maNV);
                bundle.putInt(HienThiBanAnFragment.MAGOIMONAN,maGoiMon);
                bundle.putInt(ThucDonFragment.MA_BAN,maBanAn);
                thanhToanFragment.setArguments(bundle);
                transaction.replace(R.id.content,thanhToanFragment).addToBackStack(ThucDonFragment.DANH_SACH_MON_AN);
                transaction.commit();

                List<ChiTietGoiMon> chiTietGoiMonList=new ArrayList<>();
                DBChiTietGoiMon dbChiTietGoiMon=new DBChiTietGoiMon(context);
                chiTietGoiMonList=dbChiTietGoiMon.getListChiTietGoiMon();
                for (int i=0;i<chiTietGoiMonList.size();i++){
                    ChiTietGoiMon chiTietGoiMon=chiTietGoiMonList.get(i);
                    Log.d("Chi tiet goi mon ",chiTietGoiMon.toString());
                }
            }
        });

        return convertView;
    }


    public class ViewHolder{
        ImageView imgBanAn, imgThanhToan, imgGoiMon, imgAnButton;
        TextView txtTenBanAn;

        public ViewHolder(View v){
            imgBanAn= (ImageView) v.findViewById(R.id.imgBanAn);
            imgThanhToan= (ImageView) v.findViewById(R.id.imgThanhToan);
            imgGoiMon= (ImageView) v.findViewById(R.id.imgGoiMon);
            imgAnButton= (ImageView) v.findViewById(R.id.imgAnButton);
            txtTenBanAn= (TextView) v.findViewById(R.id.txtTenBanAn);
        }
    }
}
