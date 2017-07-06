package com.example.vutuan.orderfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vutuan.orderfood.Fragment.ThongKeFragment;
import com.example.vutuan.orderfood.Fragment.HienThiBanAnFragment;
import com.example.vutuan.orderfood.Fragment.ThucDonFragment;

/**
 * Created by vutuan on 04/07/2017.
 */

public class TrangChuActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private NavigationView navigationView;
    private TextView txtTenDangNhap;

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        toolBar= (Toolbar) findViewById(R.id.toolBar);
        navigationView= (NavigationView) findViewById(R.id.navigationView);
        toolBar.setTitle(R.string.TrangChu);

        View view= LayoutInflater.from(this).inflate(R.layout.layout_header_navigation,navigationView);
        txtTenDangNhap= (TextView) view.findViewById(R.id.txtTenDangNhap);
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolBar, R.string.Open,R.string.Close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                toolBar.setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                switch (id){
                    case R.id.mnu_trangChu:
                        FragmentTransaction transactionTrangChu=fragmentManager.beginTransaction();
                        HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
                        transactionTrangChu.replace(R.id.content,hienThiBanAnFragment);
                        transactionTrangChu.commit();
                        break;
                    case R.id.mnu_thongKe:
                        FragmentTransaction transactionThongKe=fragmentManager.beginTransaction();
                        ThongKeFragment thongKeFragment=new ThongKeFragment();
                        transactionThongKe.replace(R.id.content,thongKeFragment);
                        transactionThongKe.commit();
                        break;
                    case R.id.mnu_thucDon:
                        FragmentTransaction transactionThucDon=fragmentManager.beginTransaction();
                        ThucDonFragment thucDonFragment=new ThucDonFragment();
                        transactionThucDon.replace(R.id.content,thucDonFragment);
                        transactionThucDon.commit();
                        break;
                    case R.id.mnu_caiDat:
                        FragmentTransaction transactionCaiDat=fragmentManager.beginTransaction();
                        ThongKeFragment caiDatFragment=new ThongKeFragment();
                        transactionCaiDat.replace(R.id.content,caiDatFragment);
                        transactionCaiDat.commit();
                        break;
                }
                return true;
            }
        });

        Bundle values=getIntent().getBundleExtra(DangNhapActivity.VALUE_DANGNHAP);
        String tenDangNhap=values.getString(DangNhapActivity.TENDANGNHAP);
        txtTenDangNhap.setText(tenDangNhap);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transactionTrangChu=fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
        transactionTrangChu.replace(R.id.content,hienThiBanAnFragment);
        transactionTrangChu.commit();

    }

}
