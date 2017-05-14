package com.example.shop.activity;

import android.os.Bundle;
import android.view.View;

import android.support.v4.app.FragmentTransaction;

import com.example.shop.R;
import com.example.shop.fragment.ShopFragment_shop;
import com.lzq.commlibs.baselayout.BaseActivity_libs;

public class MainActivity_shop extends BaseActivity_libs {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ShopFragment_shop mfg1 = new ShopFragment_shop();
        transaction.add(R.id.fragment_container,mfg1);
        transaction.commit();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
