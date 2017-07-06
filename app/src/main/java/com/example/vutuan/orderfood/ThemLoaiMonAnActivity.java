package com.example.vutuan.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vutuan.orderfood.Database.DBLoaiMonAn;

public class ThemLoaiMonAnActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTenLoaiMonAn;
    private Button btnDongYThemLoaiMonAn;
    private String loaiMonAn;
    public static String LOAIMONAN="LOAIMONAN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_mon_an);

        edtTenLoaiMonAn= (EditText) findViewById(R.id.edtTenLoaiMonAn);
        btnDongYThemLoaiMonAn= (Button) findViewById(R.id.btnDongYThemLoaiLoaiMonAn);
        btnDongYThemLoaiMonAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnDongYThemLoaiLoaiMonAn){
            loaiMonAn=edtTenLoaiMonAn.getText().toString();
            if (loaiMonAn!=null || !loaiMonAn.equals("")){
                DBLoaiMonAn dbLoaiMonAn=new DBLoaiMonAn(getBaseContext());
                boolean checkLoaiMonAn=dbLoaiMonAn.checkLoaiMonAn(loaiMonAn);
                if (!checkLoaiMonAn){
                    dbLoaiMonAn.themLoaiMonAn(loaiMonAn);
                    Intent intent=new Intent();
                    intent.putExtra(LOAIMONAN,loaiMonAn);
                    setResult(RESULT_OK,intent);
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.themLoaiMonAnThanhCong)+ " "+loaiMonAn,Toast.LENGTH_LONG).show();
                    finish();

                }else {
                    Toast.makeText(getBaseContext(),R.string.monAnDaTonTai,Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getBaseContext(),R.string.yeuCauThemLoaiMonAn,Toast.LENGTH_LONG).show();
            }

        }
    }
}
