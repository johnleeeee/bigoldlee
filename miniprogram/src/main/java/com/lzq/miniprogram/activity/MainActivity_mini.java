package com.lzq.miniprogram.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.miniprogram.R;
import com.lzq.miniprogram.fragment.MiniProgramFragment_mini;

public class MainActivity_mini extends BaseActivity_libs {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main_mini);

        /*FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        MiniProgramFragment_mini mfg1 = new MiniProgramFragment_mini();
        transaction.add(R.id.fragment_container,mfg1);
        transaction.commit();
*/

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
