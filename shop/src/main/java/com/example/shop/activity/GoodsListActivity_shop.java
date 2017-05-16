package com.example.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.adapter.GoodListShowAdapter;
import com.example.shop.bean.GoodsInfoDataVO;
import com.example.shop.bean.GoodsListVO;
import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.commlibs.commconst.Contants;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.http.SpotsCallBack;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.PullToRefreshSwipeMenuListView;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.pulltorefresh.interfaces.IXListViewListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenu;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenuItem;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.OnMenuItemClickListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.SwipeMenuCreator;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.util.RefreshTime;
import com.squareup.okhttp.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lizhiqiang on 2017/5/16.
 */

public class GoodsListActivity_shop extends BaseActivity_libs implements IXListViewListener {

    private static  final  String TAG="ShopFragment_shop";

    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();
    private PullToRefreshSwipeMenuListView listView;//下拉列表
    private TextView titleText;
    private TextView backBtn;

    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private RefreshTime myRefreshTime;//刷新时间
    private boolean isExeOver = true;//刷新执行完成才能再次刷新
    private boolean isLastPage = false;//是否最后一页
    private int pageindex = 1;//页码
    private GoodListShowAdapter goodListShowAdapter;
    private List<GoodsInfoDataVO> goodListData;
    private String shopID;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_list_layout_shop);
        initView();
        initListener();
        initData();

    }

    @Override
    protected void initView() {
        myRefreshTime = new RefreshTime();
        titleText = (TextView) findViewById(R.id.title_text);
        backBtn = (TextView) findViewById(R.id.back_btn);
        listView = (PullToRefreshSwipeMenuListView) findViewById(R.id.goods_list);
    }

    @Override
    protected void initListener() {
        createSwipe();
        swipeListviewListener();
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        shopID = getIntent().getExtras().getString("shopID");
        title = getIntent().getExtras().getString("title");
        titleText.setText(title);
        goodListData = new ArrayList<>();
        callService();
    }

    /**
     * 下拉刷新
     **/
    @Override
    public void onRefresh() {
        pageindex = 1;
        isLastPage = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        myRefreshTime.setRefreshTime(GoodsListActivity_shop.this.getApplicationContext(), df.format(new Date())
                , Contants.REFRESH_TIME , Contants.GOODSLIST_REFRESH_TIME);
        callService();
    }
    /**
     * 上拉加载
     **/
    @Override
    public void onLoadMore() {
        //上一页刷新后才允许刷新下一页的
        if(isExeOver){
            isExeOver = false;
            pageindex++;
            callService();
        }
    }
    /**
     * 刷新时间显示及刷新加载动画停止
     * */
    private void onLoad() {
        listView.setRefreshTime(myRefreshTime.getRefreshTime(GoodsListActivity_shop.this.getApplicationContext()
                , Contants.REFRESH_TIME , Contants.GOODSLIST_REFRESH_TIME));
        listView.stopRefresh();
        listView.stopLoadMore(isLastPage);
    }
    /**
     *侧滑按钮的创建
     **/
    public void createSwipe() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem approveItem = new SwipeMenuItem(GoodsListActivity_shop.this.getApplicationContext());
                approveItem.setWidth(dp2px(60));
                approveItem.setTitle("更新");
                approveItem.setTitleSize(15);
                approveItem.setTitleColor(Color.WHITE);
                approveItem.setBackground(R.drawable.menu_green_selector_ma);

                SwipeMenuItem backItem = new SwipeMenuItem(GoodsListActivity_shop.this.getApplicationContext());
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

                Intent intent = new Intent();
                intent.setClass(GoodsListActivity_shop.this, GoodsDetailInfoActivity_shop.class);
                Bundle bundle = new Bundle();
                bundle.putString("goodsID",goodListShowAdapter.getItem(position-1).getId());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //侧滑按钮点击事件
        listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                /*index：从左到右侧滑按钮按钮*/

            }
        });

    }

    private void callService(){

        Map<String,Object> params = new HashMap<>(2);
        params.put("shop_id",shopID);
        params.put("page",pageindex);

        httpHelper.get(Contants.API.GOODSLIST_URL, params, new SpotsCallBack<GoodsListVO>(this) {

            @Override
            public void onSuccess(Response response, GoodsListVO goodsListVO) {
                if(goodsListVO == null||goodsListVO.getData() == null||goodsListVO.getData().getData()==null){
                    if(pageindex > 1){
                        pageindex--;//如果未请求到数据页码减回来
                    }
                    isExeOver = true;
                    onLoad();
                    return;
                }else{
                    if(goodsListVO.getData().getData().size() == 0 ){
                        if(pageindex == 1){
                            Log.e(TAG,  "没有相关商品数据");
                        }else{
                            pageindex--;
                            isLastPage = true;//是最后一页
                            isExeOver = true;
                            onLoad();
                        }
                    }else{
                        if(goodListShowAdapter == null){
                            goodListData.clear();
                            goodListData.addAll(goodsListVO.getData().getData());
                            goodListShowAdapter = new GoodListShowAdapter(GoodsListActivity_shop.this,goodListData);
                            listView.setAdapter(goodListShowAdapter);
                        }else{
                            if(pageindex == 1){
                                goodListData.clear();
                            }
                            goodListData.addAll(goodsListVO.getData().getData());
                            goodListShowAdapter.notifyUpdata(goodListData);
                        }
                        isExeOver = true;
                        onLoad();
                    }

                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {
                if(pageindex > 1){
                    pageindex--;//如果未请求到数据页码减回来
                }
                isExeOver = true;
                onLoad();
                //请求异常
                Log.e(TAG,response + "");
            }
        });
    }



    /*
    * dp转px
    * */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());}

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back_btn) {
            finish();
        }
    }
}
