package com.example.vutuan.orderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vutuan.orderfood.Database.DBLoaiMonAn;
import com.example.vutuan.orderfood.Model.LoaiMonAn;
import com.example.vutuan.orderfood.R;

import java.util.List;

/**
 * Created by vutuan on 06/07/2017.
 */

public class AdapterThucDon extends BaseAdapter {
    public Context context;
    public List<LoaiMonAn> listLoaiMonAn;
    public String hinhAnh;
    public DBLoaiMonAn dbLoaiMonAn;

    public AdapterThucDon(Context context, List<LoaiMonAn> listLoaiMonAn) {
        this.context = context;
        this.listLoaiMonAn = listLoaiMonAn;
        dbLoaiMonAn=new DBLoaiMonAn(context);
    }

    @Override
    public int getCount() {
        return listLoaiMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listLoaiMonAn.get(position).getMaMonAn();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_custom_listview_thucdon,parent,false);
        }
        ViewHolderThucDon holderThucDon=new ViewHolderThucDon(convertView);
        LoaiMonAn loaiMonAn=listLoaiMonAn.get(position);
        hinhAnh=dbLoaiMonAn.getImageLoaiMonAn(loaiMonAn.getMaMonAn());
        Uri uri=Uri.parse(hinhAnh);
        holderThucDon.imgLoaiMonAn.setImageURI(uri);
        holderThucDon.txtTenLoaiMonAn.setText(loaiMonAn.getTenMonAn());

        return convertView;
    }

    public class ViewHolderThucDon{
        ImageView imgLoaiMonAn;
        TextView txtTenLoaiMonAn;
        public ViewHolderThucDon(View view){
            imgLoaiMonAn= (ImageView) view.findViewById(R.id.imgLoaiMonAn);
            txtTenLoaiMonAn= (TextView) view.findViewById(R.id.txtTenLoaiMonAn);
        }
    }
}
