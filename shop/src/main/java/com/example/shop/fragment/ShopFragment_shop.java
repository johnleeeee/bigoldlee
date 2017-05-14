package com.example.shop.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.activity.GoodsDetailInfo;
import com.example.shop.bean.DataVO;
import com.lzq.commlibs.baselayout.BaseFragment_libs;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.http.SpotsCallBack;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.PullToRefreshSwipeMenuListView;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.pulltorefresh.interfaces.IXListViewListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenu;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenuItem;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.OnMenuItemClickListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.SwipeMenuCreator;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhiqiang on 2017/5/14.
 */

public class ShopFragment_shop extends BaseFragment_libs implements IXListViewListener {
    private PullToRefreshSwipeMenuListView listView;//下拉列表
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private TextView title ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_shop,container,false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    @Override
    protected void initView(View view) {
        listView = (PullToRefreshSwipeMenuListView) view.findViewById(R.id.shopList);
        title = (TextView) view.findViewById(R.id.title);
    }

    @Override
    protected void initListener() {
        createSwipe();
        swipeListviewListener();
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        title.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.title) {
            Intent intent = new Intent();
            intent.setClass(mActivity, GoodsDetailInfo.class);
            startActivity(intent);
        }
    }

    /**
     * 下拉刷新
     **/
    @Override
    public void onRefresh() {

    }
    /**
     * 上拉加载
     **/
    @Override
    public void onLoadMore() {
        //上一页刷新后才允许刷新下一页的

    }
    /**
     * 刷新时间显示及刷新加载动画停止
     * */
    private void onLoad() {

    }
    /**
     *侧滑按钮的创建
     **/
    public void createSwipe() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem approveItem = new SwipeMenuItem(mActivity.getApplicationContext());
                approveItem.setWidth(dp2px(60));
                approveItem.setTitle("更新");
                approveItem.setTitleSize(15);
                approveItem.setTitleColor(Color.WHITE);
                approveItem.setBackground(R.drawable.menu_green_selector_ma);

                SwipeMenuItem backItem = new SwipeMenuItem(mActivity.getApplicationContext());
                backItem.setWidth(dp2px(60));
                backItem.setTitle("删除");
                backItem.setTitleSize(15);
                backItem.setTitleColor(Color.WHITE);
                backItem.setBackground(R.drawable.menu_red_selector_ma);

                menu.addMenuItem(approveItem);
                menu.addMenuItem(backItem);

            }
        };
        // 侧滑按钮添加
        listView.setMenuCreator(creator);
    }
    /*
     * listview上的监听事件
     * */
    public void swipeListviewListener() {
        //行点击事件（进入详情页）
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

            }
        });
        //侧滑按钮点击事件
        listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                /*index：从左到右侧滑按钮按钮*/
                //通过得到item的Type判断点击事件的类型
                //第一个按钮

            }
        });

    }


    /*
    * dp转px
    * */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());}

}
