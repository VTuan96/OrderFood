package com.example.vutuan.orderfood;

import android.app.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vutuan.orderfood.Database.DBNhanVien;
import com.example.vutuan.orderfood.Model.NhanVien;


public class DangKyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTenDangKy, edtMatKhau, edtNgaySinh, edtCMND, edtTenNhanVien;
    private RadioButton rbNam, rbNu;
    private Button btnDongY, btnThoat;
    private RadioGroup rgGioiTinh;
    private boolean gioiTinh;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edtTenDangKy= (EditText) findViewById(R.id.edtTenDangKy);
        edtMatKhau= (EditText) findViewById(R.id.edtMatKhau);
        edtNgaySinh= (EditText) findViewById(R.id.edtNgaySinh);
        edtCMND= (EditText) findViewById(R.id.edtCMND);
        edtTenNhanVien= (EditText) findViewById(R.id.edtTenNhanVien);
        rbNam= (RadioButton) findViewById(R.id.rbNam);
        rbNu= (RadioButton) findViewById(R.id.rbNu);
        btnDongY= (Button) findViewById(R.id.btnDongY);
        btnThoat= (Button) findViewById(R.id.btnThoat);
        rgGioiTinh= (RadioGroup) findViewById(R.id.rgGioiTinh);

        btnDongY.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

        edtNgaySinh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                switch (v.getId()){
                    case R.id.edtNgaySinh:
                        if (hasFocus){
                            Calendar calendar=Calendar.getInstance();
                            datePicker=new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    Calendar newCalendar=Calendar.getInstance();
                                    newCalendar.set(year,month,dayOfMonth);
                                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                                    edtNgaySinh.setText(dateFormat.format(newCalendar.getTime()));

                                }
                            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                            datePicker.show();
                        }

                        break;
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnDongY:
                dangKyNhanVien(v);
                break;
            case R.id.btnThoat:
                thoatDangKy();
                break;

        }
    }

    private void thoatDangKy() {
        Intent intent=new Intent(getBaseContext(), DangNhapActivity.class);
        startActivity(intent);
        finish();
    }


    private void dangKyNhanVien(View v) {

        String tenDangNhap=edtTenDangKy.getText().toString();
        String matKhau=edtMatKhau.getText().toString();
        String tenNhanVien=edtTenNhanVien.getText().toString();
        int id=rgGioiTinh.getCheckedRadioButtonId();
        switch (id){
            case R.id.rbNam:
                gioiTinh=true;
                Log.e("GioiTinh","nam");
                break;
            case R.id.rbNu:
                gioiTinh=false;
                Log.e("GioiTinh","nu");
                break;
        }
        String ngaySinh=edtNgaySinh.getText().toString();
        int CMND=Integer.parseInt(edtCMND.getText().toString());
        if (tenDangNhap==""||tenDangNhap==null){
            Toast.makeText(getBaseContext(),"Please enter full field! ",Toast.LENGTH_LONG).show();
        }
        else{
            NhanVien nhanVien=new NhanVien(tenNhanVien,tenDangNhap,matKhau,gioiTinh,ngaySinh,CMND);
            DBNhanVien database=new DBNhanVien(this);
            long index=database.insertNhanVien(nhanVien);
            if (index!=0){
                Toast.makeText(this,"Register successfully!",Toast.LENGTH_LONG).show();
                thoatDangKy();
            }
        }

    }

}
