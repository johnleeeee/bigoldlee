package com.lzq.commlibs.refreshswipemenulistviewlibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class RefreshTime {

    public static String getRefreshTime(Context context , String spName , String setSpName) {//得到刷新时间
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_APPEND);
        return preferences.getString(setSpName, "");
    }

    public static void setRefreshTime(Context context, String newPasswd , String spName , String setSpName) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_APPEND);
        Editor editor = preferences.edit();
        editor.putString(setSpName, newPasswd);
        editor.commit();
    }

}