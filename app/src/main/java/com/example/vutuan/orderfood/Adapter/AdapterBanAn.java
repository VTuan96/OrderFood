package com.example.vutuan.orderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vutuan.orderfood.Model.BanAn;
import com.example.vutuan.orderfood.R;

import java.util.ArrayList;

/**
 * Created by vutuan on 05/07/2017.
 */

public class AdapterBanAn extends BaseAdapter {
    Context context;
    ArrayList<BanAn> mListBanAn;

    public AdapterBanAn(Context mContext,ArrayList<BanAn> listBanAn) {
        this.context = mContext;
        this.mListBanAn=listBanAn;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_custom_gridview_ban_an,parent,false);
            ViewHolder holder=new ViewHolder(convertView);

            holder.txtTenBanAn.setText(mListBanAn.get(position).getTenBanAN());
        }

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
