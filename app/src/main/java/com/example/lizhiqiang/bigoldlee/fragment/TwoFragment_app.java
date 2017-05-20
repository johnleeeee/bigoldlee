package com.example.lizhiqiang.bigoldlee.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.lizhiqiang.bigoldlee.R;
import com.lzq.commlibs.baselayout.BaseFragment_libs;

/**
 * Created by lizhiqiang on 2017/3/12.
 */

public class TwoFragment_app extends BaseFragment_libs{

    private FrameLayout frame_appshop ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_app,container,false);
        frame_appshop = (FrameLayout) view.findViewById(R.id.fragment_shop_app);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

        //frame_appmain.removeAllViews();
        //必须有（事物）
        FragmentTransaction beginTransaction = mActivity.getSupportFragmentManager().beginTransaction();
        //String appfragment = "com.example.shop.fragment.ShopFragment_shop";
        String appfragment = "com.example.shop.fragment.GoodsFragment_shop";

        try {
            Class<BaseFragment_libs> demo = (Class<BaseFragment_libs>) Class.forName(appfragment);
            BaseFragment_libs currentFragment = demo.newInstance();
            beginTransaction.add(R.id.fragment_shop_app, currentFragment).commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }
}
