package com.example.vutuan.orderfood.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vutuan.orderfood.Model.ThanhToan;
import com.example.vutuan.orderfood.R;

import java.util.List;

/**
 * Created by vutuan on 11/07/2017.
 */

public class AdapterThanhToan extends BaseAdapter {
    private Context context;
    private List<ThanhToan> listThanhToan;

    public AdapterThanhToan(Context context, List<ThanhToan> listThanhToan) {
        this.context = context;
        this.listThanhToan = listThanhToan;
    }

    @Override
    public int getCount() {
        return listThanhToan.size();
    }

    @Override
    public Object getItem(int position) {
        return listThanhToan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderThanhToan holderThanhToan;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_layout_thanh_toan,parent,false);
        }
        holderThanhToan=new HolderThanhToan(convertView);
        holderThanhToan.txtSoLuongThanhToan.setText(listThanhToan.get(position).getSoLuong()+"");
        holderThanhToan.txtGiaMonAnThanhToan.setText(listThanhToan.get(position).getGiaCa()+"");
        holderThanhToan.txtTenMonAnThanhToan.setText(listThanhToan.get(position).getTenMonAn());

        return convertView;
    }

    public class HolderThanhToan{
        private TextView txtTenMonAnThanhToan, txtSoLuongThanhToan, txtGiaMonAnThanhToan;
        public HolderThanhToan(View view){
            txtTenMonAnThanhToan= (TextView) view.findViewById(R.id.txtTenMonAnThanhToan);
            txtGiaMonAnThanhToan= (TextView) view.findViewById(R.id.txtGiaMonAnThanhToan);
            txtSoLuongThanhToan= (TextView) view.findViewById(R.id.txtSoLuongThanhToan);
        }

    }
}
