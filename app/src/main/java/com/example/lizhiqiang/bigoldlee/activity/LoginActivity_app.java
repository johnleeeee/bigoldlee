package com.example.lizhiqiang.bigoldlee.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.lizhiqiang.bigoldlee.R;
import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.commlibs.baseutils.ToastUtils;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.view.CNiaoToolBar;
import com.lzq.commlibs.view.ClearEditText;

/**
 * Created by lizhiqiang on 2017/5/14.
 */

public class LoginActivity_app extends BaseActivity_libs {

    private Button btnLogin;
    private CNiaoToolBar toolbar;
    private ClearEditText etxtUser;
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
        etxtUser = (ClearEditText) findViewById(R.id.etxt_user);
        etxtPwd = (ClearEditText) findViewById(R.id.etxt_pwd);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_user);
        drawable.setBounds(0,0,32,32);
        etxtUser.setCompoundDrawables(drawable,null,null,null);

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
                String user = etxtUser.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    ToastUtils.show(this, "请输入账户名");
                    return;
                }

                String pwd = etxtPwd.getText().toString().trim();
                if(TextUtils.isEmpty(pwd)){
                    ToastUtils.show(this,"请输入密码");
                    return;
                }

                if (user.equals("admin")&&pwd.equals("admin")){
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity_app.this,MainActivity_app.class);
                    startActivity(intent);
                }else {
                    new  AlertDialog.Builder(this)
                            .setTitle("提示" )
                            .setMessage("账户名或密码错误" )
                            .setPositiveButton("确定" ,  null)
                            .show();
                }

//                Map<String,Object> params = new HashMap<>(2);
//                params.put("user",user);
//                params.put("password", DESUtil.encode(Contants.DES_KEY,pwd));
//                okHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {
//
//                    @Override
//                    public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {
//
//                        BigleeApplication application =  BigleeApplication.getInstance();
//                        application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());
//
//                        Intent intent = new Intent();
//                        intent.setClass(LoginActivity_app.this,MainActivity_app.class);
//                        startActivity(intent);
//
//                    }
//
//                    @Override
//                    public void onError(Response response, int code, Exception e) {
//
//                    }
//                });

                break;
        }
    }
}
