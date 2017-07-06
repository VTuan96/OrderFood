package com.example.vutuan.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vutuan.orderfood.Database.CreateDatabase;
import com.example.vutuan.orderfood.Database.DBNhanVien;

/**
 * Created by vutuan on 26/06/2017.
 */
public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTenDangNhap, edtMatKhauDangNhap;
    private Button btnDangNhap, btnDangKy;

    private String tenDangNhap, matKhauDangNhap;
    public static final String VALUE_DANGNHAP="VALUE_DANGNHAP";
    public static final String TENDANGNHAP="TENDANGNHAP";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        edtTenDangNhap= (EditText) findViewById(R.id.edtTenDangNhap);
        edtMatKhauDangNhap= (EditText) findViewById(R.id.edtMatKhauDangNhap);

        btnDangKy= (Button) findViewById(R.id.btnDangKy);
        btnDangNhap= (Button) findViewById(R.id.btnDangNhap);

        btnDangKy.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        tenDangNhap=edtTenDangNhap.getText().toString();
        matKhauDangNhap=edtMatKhauDangNhap.getText().toString();

        DBNhanVien dbNhanVien=new DBNhanVien(getBaseContext());
        boolean check= dbNhanVien.checkLogin(tenDangNhap,matKhauDangNhap);

        switch (v.getId()){
            case R.id.btnDangNhap:
                if (check){
                    btnDangKy.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(),"Login successfully!",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(getBaseContext(),TrangChuActivity.class);
                    Bundle values=new Bundle();
                    values.putString(TENDANGNHAP,tenDangNhap);
                    intent.putExtra(VALUE_DANGNHAP,values);
                    startActivity(intent);
                    finish();
                } else {
                    btnDangNhap.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(),"Please register account!",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnDangKy:
                Intent intent=new Intent(getBaseContext(), DangKyActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
