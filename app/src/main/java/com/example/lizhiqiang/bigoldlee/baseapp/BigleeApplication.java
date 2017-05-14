package com.example.lizhiqiang.bigoldlee.baseapp;

import android.content.Context;
import android.content.Intent;

import com.lzq.commlibs.baselayout.BaseApplication_lib;


/**
 *
 */
public class BigleeApplication extends BaseApplication_lib {


    private static  BigleeApplication mInstance;

    public static  BigleeApplication getInstance(){

        return  mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }


    private Intent intent;
    public void putIntent(Intent intent){
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void jumpToTargetActivity(Context context){

        context.startActivity(intent);
        this.intent =null;
    }

}
