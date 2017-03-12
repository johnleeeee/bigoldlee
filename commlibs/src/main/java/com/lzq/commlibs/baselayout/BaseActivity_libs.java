package com.lzq.commlibs.baselayout;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by lizhiqiang on 2017/3/10.
 */

public abstract class BaseActivity_libs extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initData();
}
