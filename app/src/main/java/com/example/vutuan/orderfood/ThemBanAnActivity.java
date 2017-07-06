package com.example.vutuan.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vutuan.orderfood.Database.DBBanAn;
import com.example.vutuan.orderfood.R;

/**
 * Created by vutuan on 04/07/2017.
 */

public class ThemBanAnActivity extends AppCompatActivity{
    private EditText edtBanAn;
    private Button btnThemBanAn;
    String tenBanAn="";

    public static final String TENBANAN="TENBANAN";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them_ban_an);

        edtBanAn= (EditText) findViewById(R.id.edtBanAn);
        btnThemBanAn= (Button) findViewById(R.id.btnThemBanAn);

        btnThemBanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenBanAn=edtBanAn.getText().toString();
                tenBanAn=tenBanAn.toLowerCase();
                if (tenBanAn!=null || !tenBanAn.equals("")){
                    DBBanAn dbBanAn=new DBBanAn(getBaseContext());
                    boolean checkBanAn=dbBanAn.checBanAn(tenBanAn);
                    if (!checkBanAn){
                        long themBanAn= dbBanAn.themBanAn(tenBanAn);
                        if (themBanAn!=0){
                            Intent intent=new Intent();
                            intent.putExtra(TENBANAN,tenBanAn);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                    }else {
                        Toast.makeText(getBaseContext(),R.string.banAnDaTonTai,Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

}
