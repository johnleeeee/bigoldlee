package com.example.lizhiqiang.bigoldlee.base.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

/**
 * Created by GaoHW on 2017/3/10.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initData();
}
