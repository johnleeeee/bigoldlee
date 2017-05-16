package com.example.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lzq.commlibs.refreshswipemenulistviewlibrary.util.RefreshTime;

/**
 * Created by lzq on 2016/9/22.
 */
public class MyRefreshTime_ma extends RefreshTime {
    String PRE_NAME = "";

    final static String SET_FRESHTIME = "set_refresh_time";
    final static String OTHER_SET_FRESHTIME = "other_set_refresh_time";

    public MyRefreshTime_ma(String PRE_NAME){
        this.PRE_NAME = PRE_NAME;
    }

    public String getRefreshTime(Context context,int isAppr) {
        SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        if (isAppr == 0){
            return preferences.getString(SET_FRESHTIME, "");
        }else{
            return preferences.getString(OTHER_SET_FRESHTIME, "");
        }

    }

    public void setRefreshTime(Context context, String newPasswd, int isAppr) {
        SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = preferences.edit();
        if(isAppr == 1){
            editor.putString(SET_FRESHTIME, newPasswd);
        }else{
            editor.putString(OTHER_SET_FRESHTIME, newPasswd);
        }
        editor.commit();
    }
}
