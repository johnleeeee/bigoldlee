package com.example.lizhiqiang.bigoldlee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.lizhiqiang.bigoldlee.R;
import com.example.lizhiqiang.bigoldlee.baseapp.BigleeApplication;
import com.lzq.commlibs.bean.User;
import com.example.lizhiqiang.bigoldlee.msg.LoginRespMsg;
import com.lzq.commlibs.baseutils.ToastUtils;
import com.lzq.commlibs.commconst.Contants;
import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.commlibs.baseutils.DESUtil;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.http.SpotsCallBack;
import com.lzq.commlibs.view.CNiaoToolBar;
import com.lzq.commlibs.view.ClearEditText;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhiqiang on 2017/5/14.
 */

public class LoginActivity_app extends BaseActivity_libs {

    private Button btnLogin;
    private CNiaoToolBar toolbar;
    private ClearEditText etxtPhone;
    private ClearEditText etxtPwd;

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act_app);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void initView() {
        toolbar = (CNiaoToolBar) findViewById(R.id.toolbar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etxtPhone = (ClearEditText) findViewById(R.id.etxt_phone);
        etxtPwd = (ClearEditText) findViewById(R.id.etxt_pwd);

    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login :
                String phone = etxtPhone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    ToastUtils.show(this, "请输入手机号码");
                    return;
                }

                String pwd = etxtPwd.getText().toString().trim();
                if(TextUtils.isEmpty(pwd)){
                    ToastUtils.show(this,"请输入密码");
                    return;
                }

                Map<String,Object> params = new HashMap<>(2);
                params.put("phone",phone);
                params.put("password", DESUtil.encode(Contants.DES_KEY,pwd));
                okHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {

                    @Override
                    public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {

                        BigleeApplication application =  BigleeApplication.getInstance();
                        application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());

                        Intent intent = new Intent();
                        intent.setClass(LoginActivity_app.this,MainActivity_app.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }
                });

                break;
        }
    }
}
