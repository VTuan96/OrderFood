package com.example.vutuan.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemSoLuongActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtSoLuong;
    private Button btnDongYThemSoLuong;

    public static final String SO_LUONG="SO_LUONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_so_luong);

        edtSoLuong= (EditText) findViewById(R.id.edtSoLuong);
        btnDongYThemSoLuong= (Button) findViewById(R.id.btnDongYThemSoLuong);

        btnDongYThemSoLuong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnDongYThemSoLuong:
                String soLuong=edtSoLuong.getText().toString();
                if (soLuong!=null && !soLuong.equals("")){
                    Intent intent=new Intent();
                    intent.putExtra(SO_LUONG,Integer.parseInt(soLuong));
                    setResult(Activity.RESULT_OK,intent);
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.themSoLuongThanhCong),Toast.LENGTH_LONG).show();
                    finish();
                }

                break;
        }
    }
}
