package com.lzq.commlibs.baselayout;

import android.app.Application;

import com.lzq.commlibs.bean.User;
import com.lzq.commlibs.util.UserLocalData;

/**
 * Created by lizhiqiang on 2017/5/14.
 */

public class BaseApplication_lib extends Application {

    private static BaseApplication_lib baseApp ;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }

    public static BaseApplication_lib getBaseApp(){
        return baseApp;
    }

    private void initUser(){

        this.user = UserLocalData.getUser(this);
    }


    public User getUser(){

        return user;
    }


    public void putUser(User user,String token){
        this.user = user;
        UserLocalData.putUser(this,user);
        UserLocalData.putToken(this,token);
    }

    public void clearUser(){
        this.user =null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);


    }


    public String getToken(){

        return  UserLocalData.getToken(this);
    }

}
