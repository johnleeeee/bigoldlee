package com.lzq.commlibs.baselayout;


import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lzq.commlibs.util.PreferencesUtils;
import com.lzq.commlibs.commconst.Contants;

/**
 * Created by lizhiqiang on 2017/3/10.
 */

public abstract class BaseActivity_libs extends AppCompatActivity implements View.OnClickListener {

    protected BaseActivity_libs mActivity ;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;
    }

    public static String getToken(Context context){
        return  PreferencesUtils.getString( context, Contants.TOKEN);
    }

    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initData();
}
