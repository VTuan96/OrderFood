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

import com.example.vutuan.orderfood.Database.DBLoaiMonAn;
import com.example.vutuan.orderfood.Model.LoaiMonAn;

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

    private String tenLoaiMonAn;

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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THEM_THUC_DON && resultCode==RESULT_OK){
            tenLoaiMonAn=data.getStringExtra(ThemLoaiMonAnActivity.LOAIMONAN);
            setupAdaper();
        } else if (requestCode==REQUEST_CODE_LOAD_IMAGE && resultCode==RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.d("TAG",picturePath);
            cursor.close();
            imgMonAn.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
