package com.example.vutuan.orderfood;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vutuan.orderfood.Database.DBLoaiMonAn;
import com.example.vutuan.orderfood.Database.DBMonAn;
import com.example.vutuan.orderfood.Model.LoaiMonAn;
import com.example.vutuan.orderfood.Model.MonAn;

import java.util.ArrayList;

/**
 * Created by vutuan on 05/07/2017.
 */

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTenMonAn, edtGiaTien;
    private ImageView imgMonAn;
    private Spinner spLoaiMonAn;
    private ImageButton btnThemLoaiMonAn;
    private Button btnDongYThucDon, btnThoatThucDon;

    private ArrayList<String> arrayLoaiMonAn;
    private ArrayList<LoaiMonAn> mList;
    private ArrayAdapter<String> adapter;

    private String tenLoaiMonAn, tenMonAn, giaTien;
    private int maLoaiMonAn;
    private String hinhAnh;

    public static int REQUEST_CODE_THEM_THUC_DON=222;
    public static int REQUEST_CODE_LOAD_IMAGE=333;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_thuc_don);

        edtTenMonAn= (EditText) findViewById(R.id.edtTenMonAn);
        edtGiaTien= (EditText) findViewById(R.id.edtGiaTien);
        imgMonAn= (ImageView) findViewById(R.id.imgMonAn);
        spLoaiMonAn= (Spinner) findViewById(R.id.spLoaiMonAn);
        btnThemLoaiMonAn= (ImageButton) findViewById(R.id.btnThemLoaiMonAn);
        btnDongYThucDon= (Button) findViewById(R.id.btnDongYThucDon);
        btnThoatThucDon= (Button) findViewById(R.id.btnThoatThucDon);

        btnThemLoaiMonAn.setOnClickListener(this);
        setupAdaper();
        imgMonAn.setOnClickListener(this);
        btnDongYThucDon.setOnClickListener(this);
        btnThoatThucDon.setOnClickListener(this);

    }

    public void setupAdaper(){
        arrayLoaiMonAn=new ArrayList<>();
        DBLoaiMonAn dbLoaiMonAn=new DBLoaiMonAn(this);
        mList= (ArrayList<LoaiMonAn>) dbLoaiMonAn.getListLoaiMonAn();
        for (int i=0;i<mList.size();i++){
            String temp=mList.get(i).getTenMonAn();
            temp=temp.trim();
            arrayLoaiMonAn.add(temp);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arrayLoaiMonAn);
        spLoaiMonAn.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnThemLoaiMonAn:
                Intent intent=new Intent(getBaseContext(),ThemLoaiMonAnActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEM_THUC_DON);
                break;
            case R.id.imgMonAn:
                Intent intentChooseImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentChooseImage,REQUEST_CODE_LOAD_IMAGE);
                break;
            case R.id.btnDongYThucDon:
                tenMonAn=edtTenMonAn.getText().toString();
                giaTien=edtGiaTien.getText().toString();
                if (tenMonAn==null || tenMonAn.equals("") || giaTien==null || giaTien.equals("")){
                    Toast.makeText(getBaseContext(),R.string.yeuCauNhapDuLieu,Toast.LENGTH_LONG).show();
                } else {
                    int pos=spLoaiMonAn.getSelectedItemPosition();
                    maLoaiMonAn=mList.get(pos).getMaMonAn();
                    MonAn monAn=new MonAn(maLoaiMonAn,tenMonAn,giaTien,hinhAnh);
                    DBMonAn dbMonAn=new DBMonAn(getBaseContext());
                    long check=dbMonAn.themMonAn(monAn);
                    if (check!=0){
                        Toast.makeText(getBaseContext(),R.string.themMonAnThanhCong,Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(),R.string.themMonAnThatBai,Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
                break;
            case R.id.btnThoatThucDon:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THEM_THUC_DON && resultCode==RESULT_OK){
            tenLoaiMonAn=data.getStringExtra(ThemLoaiMonAnActivity.LOAIMONAN);
            setupAdaper();
        } else if (requestCode==REQUEST_CODE_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            hinhAnh=uri.toString();
            imgMonAn.setImageURI(uri);
        }

    }
}
