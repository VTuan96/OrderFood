package com.example.vutuan.orderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vutuan.orderfood.Model.MonAn;
import com.example.vutuan.orderfood.R;

import java.util.List;

/**
 * Created by vutuan on 07/07/2017.
 */

//public class AdapterDanhSachMonAn extends RecyclerView.Adapter<AdapterDanhSachMonAn.DanhSachMonAnViewHoldeer> {
//    private List<MonAn> listMonAn;
//    private Context context;
//
//    public AdapterDanhSachMonAn(List<MonAn> listMonAn, Context context) {
//        this.listMonAn = listMonAn;
//        this.context = context;
//    }
//
//    @Override
//    public DanhSachMonAnViewHoldeer onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view=LayoutInflater.from(context).inflate(R.layout.layout_custom_listview_danh_sach_mon_an,parent,false);
//        DanhSachMonAnViewHoldeer viewHoldeer=new DanhSachMonAnViewHoldeer(view);
//        return viewHoldeer;
//    }
//
//    @Override
//    public void onBindViewHolder(DanhSachMonAnViewHoldeer holder, int position) {
//        MonAn monAn=listMonAn.get(position);
//        Uri uri=Uri.parse(monAn.getHinhAnh());
//
//        holder.imgAnhMonAn.setImageURI(uri);
//        holder.txtTenMonAn.setText(R.string.tenMonAn+" "+monAn.getTenMonAn());
//        holder.txtGiaMonAn.setText(R.string.giaMonAn+ " "+monAn.getGiaTien());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listMonAn.size();
//    }
//
//    public class DanhSachMonAnViewHoldeer extends RecyclerView.ViewHolder{
//        private ImageView imgAnhMonAn;
//        private TextView txtTenMonAn, txtGiaMonAn;
//        public DanhSachMonAnViewHoldeer(View itemView) {
//            super(itemView);
//            imgAnhMonAn= (ImageView) itemView.findViewById(R.id.imgAnhMonAn);
//            txtTenMonAn= (TextView) itemView.findViewById(R.id.txtTenMonAn);
//            txtGiaMonAn= (TextView) itemView.findViewById(R.id.txtGiaMonAn);
//        }
//    }
//}

public class AdapterDanhSachMonAn extends BaseAdapter{
    private Context context;
    private List<MonAn> listMonAn;

    public AdapterDanhSachMonAn(Context context, List<MonAn> listMonAn) {
        this.context = context;
        this.listMonAn = listMonAn;
    }

    @Override
    public int getCount() {
        return listMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return listMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMonAn.get(position).getMaMonAn();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.layout_custom_listview_danh_sach_mon_an,parent,false);
        }
        DanhSachMonAnViewHoldeer holder=new DanhSachMonAnViewHoldeer(convertView);
        MonAn monAn=listMonAn.get(position);
        Uri uri=Uri.parse(monAn.getHinhAnh());

        holder.imgAnhMonAn.setImageURI(uri);
        holder.txtTenMonAn.setText(context.getResources().getString(R.string.tenMonAn)+" "+monAn.getTenMonAn());
        holder.txtGiaMonAn.setText(context.getResources().getString(R.string.giaMonAn)+ " "+monAn.getGiaTien());

        return convertView;
    }

    public class DanhSachMonAnViewHoldeer {
        private ImageView imgAnhMonAn;
        private TextView txtTenMonAn, txtGiaMonAn;
        public DanhSachMonAnViewHoldeer(View itemView) {
            imgAnhMonAn= (ImageView) itemView.findViewById(R.id.imgAnhMonAn);
            txtTenMonAn= (TextView) itemView.findViewById(R.id.txtTenMonAn);
            txtGiaMonAn= (TextView) itemView.findViewById(R.id.txtGiaMonAn);
        }
    }
}
