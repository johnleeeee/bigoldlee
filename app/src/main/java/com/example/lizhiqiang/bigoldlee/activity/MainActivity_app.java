package com.example.lizhiqiang.bigoldlee.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lizhiqiang.bigoldlee.R;
import com.example.lizhiqiang.bigoldlee.fragment.FourFragment_app;
import com.example.lizhiqiang.bigoldlee.fragment.MainFragment_app;
import com.example.lizhiqiang.bigoldlee.fragment.ThreeFragment_app;
import com.example.lizhiqiang.bigoldlee.fragment.TwoFragment_app;
import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.miniprogram.view.BadgeView;

/**
 * Created by lizhiqiang on 2017/3/10.
 */

public class MainActivity_app extends BaseActivity_libs {
    private LinearLayout ly_one,ly_two,ly_three,ly_four;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4,txt_topbar;
    private TextView mTextNum1,mTextNum2,mTextNum3,mTxetNum3;
    private TextView mTextNum4;
    private BadgeView badgeView1,badgeView2,badgeView3,badgeView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        initView();
        initListener();
        initData();

        ly_one.performClick();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MainFragment_app fg1 = new MainFragment_app();
        transaction.replace(R.id.fragment_container,fg1);
        transaction.commit();

    }

    @Override
    protected void initView() {
        ly_one = (LinearLayout)findViewById(R.id.ly_tab_menu_deal);
        ly_two = (LinearLayout)findViewById(R.id.ly_tab_menu_poi);
        ly_three = (LinearLayout)findViewById(R.id.ly_tab_menu_more);
        ly_four = (LinearLayout)findViewById(R.id.ly_tab_menu_user);

        mTextView1 = (TextView)findViewById(R.id.tab_menu_deal);
        mTextView2 = (TextView)findViewById(R.id.tab_menu_poi);
        mTextView3 = (TextView)findViewById(R.id.tab_menu_more);
        mTextView4 = (TextView)findViewById(R.id.tab_menu_user);
        txt_topbar = (TextView)findViewById(R.id.txt_topbar);
        txt_topbar.setText("主界面");

        mTextNum1 = (TextView)findViewById(R.id.tab_menu_deal_num);
        mTextNum2 = (TextView)findViewById(R.id.tab_menu_poi_num);
        mTextNum3 = (TextView)findViewById(R.id.tab_menu_more_num);
        mTextNum4 = (TextView) findViewById(R.id.tab_menu_setting_partner);

        badgeView1 = new BadgeView(this,mTextNum1);
        badgeView1.setBadgeBackgroundColor(getResources().getColor(R.color.point_red));
        badgeView1.setTextColor(getResources().getColor(R.color.text_white));
        badgeView1.setTextSize(12);
        badgeView1.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView1.setBadgeMargin(5);

        mTextNum1.setText("11");
        mTextNum1.setVisibility(View.VISIBLE);

        mTextNum2.setText("99");
        mTextNum2.setVisibility(View.VISIBLE);

        mTextNum3.setText("999+");
        mTextNum3.setVisibility(View.VISIBLE);

        mTextNum4.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
        ly_one.setOnClickListener(this);
        ly_two.setOnClickListener(this);
        ly_three.setOnClickListener(this);
        ly_four.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.ly_tab_menu_deal:
                txt_topbar.setText("主界面");
                transaction.replace(R.id.fragment_container, new MainFragment_app()).commit();
                setSelected();
                mTextView1.setSelected(true);
                mTextNum1.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_poi:
                txt_topbar.setText("第二个");
                transaction.replace(R.id.fragment_container, new TwoFragment_app()).commit();
                setSelected();
                mTextView2.setSelected(true);
                mTextNum2.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_more:
                txt_topbar.setText("第三个");
                transaction.replace(R.id.fragment_container, new ThreeFragment_app()).commit();
                setSelected();
                mTextView3.setSelected(true);
                mTextNum3.setVisibility(View.INVISIBLE);
                break;
            case R.id.ly_tab_menu_user:
                txt_topbar.setText("第四个");
                transaction.replace(R.id.fragment_container, new FourFragment_app()).commit();
                setSelected();
                mTextView4.setSelected(true);
                mTextNum4.setVisibility(View.INVISIBLE);
                break;
        }
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextView1.setSelected(false);
        mTextView2.setSelected(false);
        mTextView3.setSelected(false);
        mTextView4.setSelected(false);
    }
}
