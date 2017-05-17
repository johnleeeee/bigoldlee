package com.example.shop.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.shop.R;
import com.example.shop.activity.GoodsListActivity_shop;
import com.example.shop.adapter.ShopListShowAdapter;
import com.example.shop.bean.ShopInfoDataVO;
import com.lzq.commlibs.baselayout.BaseFragment_libs;
import com.lzq.commlibs.commconst.Contants;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.PullToRefreshSwipeMenuListView;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.pulltorefresh.interfaces.IXListViewListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenu;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenuItem;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.OnMenuItemClickListener;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces.SwipeMenuCreator;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.util.RefreshTime;
import com.lzq.commlibs.util.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by lizhiqiang on 2017/5/14.
 */

public class ShopFragment_shop extends BaseFragment_libs implements IXListViewListener {

    private static  final  String TAG="ShopFragment_shop";

    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();
    private PullToRefreshSwipeMenuListView listView;//下拉列表
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private RefreshTime myRefreshTime;//刷新时间
    private Button vegetablesLeft;//蔬菜
    private Button fruitMiddle;//水果
    private Button breakfastRight;//早餐
    private int switchNode = 0;
    private boolean isExeOver = true;//刷新执行完成才能再次刷新
    private boolean isLastPage = false;//是否最后一页
    private int pageindex = 1;//页码
    private ShopListShowAdapter shopListShowAdapter;
    private List<ShopInfoDataVO> shopList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_shop,container,false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    /**
     * dp转px
     * */
    public int dip2px(float dpValue) {
        final float scale = mActivity.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void initView(View view) {
        myRefreshTime = new RefreshTime();
        int setWidth = CommonUtil.getWidthPixel(mActivity)/4+5;
        if(setWidth>dip2px(100)){setWidth = dip2px(100);}
        vegetablesLeft = (Button) view.findViewById(R.id.vegetables_left);
        vegetablesLeft.setWidth(setWidth);
        fruitMiddle = (Button) view.findViewById(R.id.fruit_middle);
        fruitMiddle.setWidth(setWidth);
        breakfastRight = (Button) view.findViewById(R.id.breakfast_right);
        breakfastRight.setWidth(setWidth);
        listView = (PullToRefreshSwipeMenuListView) view.findViewById(R.id.goods_list);
    }

    @Override
    protected void initListener() {
        createSwipe();
        swipeListviewListener();
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        vegetablesLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switchNode != 0){
                    switchNode = 0;
                    isLastPage = false;
                    pageindex =1;
                    //设置侧滑控件为关闭状态
                    listView.smoothCloseMenu();
                    createSwipe();
                    vegetablesLeft.setTextColor(getResources().getColor(R.color.white));
                    vegetablesLeft.setBackground(getResources().getDrawable(R.drawable.left_while_borde_rounded_focused_ma));
                    fruitMiddle.setTextColor(getResources().getColor(R.color.lanse));
                    fruitMiddle.setBackground(getResources().getDrawable(R.drawable.middle_while_borde_rounded_ma));
                    breakfastRight.setTextColor(getResources().getColor(R.color.lanse));
                    breakfastRight.setBackground(getResources().getDrawable(R.drawable.right_while_borde_rounded_ma));
                    /*shopList.clear();
                    if (ShopListShowAdapter != null){
                            shopListShowAdapter.updateListView(shopList);
                    }*/
                    callService();
                }

            }
        });
        fruitMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchNode != 1) {
                    switchNode = 1;
                    isLastPage = false;
                    pageindex = 1;
                    //设置侧滑控件为关闭状态
                    listView.smoothCloseMenu();
                    createSwipe();
                    fruitMiddle.setTextColor(getResources().getColor(R.color.white));
                    fruitMiddle.setBackground(getResources().getDrawable(R.drawable.middle_while_borde_rounded_focused_ma));
                    vegetablesLeft.setTextColor(getResources().getColor(R.color.lanse));
                    vegetablesLeft.setBackground(getResources().getDrawable(R.drawable.left_while_borde_rounded_ma));
                    breakfastRight.setTextColor(getResources().getColor(R.color.lanse));
                    breakfastRight.setBackground(getResources().getDrawable(R.drawable.right_while_borde_rounded_ma));
                    //先清除数据，防止网络异常时数据显示错乱
                    /*shopList.clear();
                    if (ShopListShowAdapter != null){
                            shopListShowAdapter.updateListView(shopList);
                    }*/
                    callService();
                }
            }
        });

        breakfastRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchNode != 2){
                switchNode = 2;
                isLastPage = false;
                pageindex = 1;
                //设置侧滑控件为关闭状态
                listView.smoothCloseMenu();
                createSwipe();
                breakfastRight.setTextColor(getResources().getColor(R.color.white));
                breakfastRight.setBackground(getResources().getDrawable(R.drawable.right_while_borde_rounded_focused_ma));
                fruitMiddle.setTextColor(getResources().getColor(R.color.lanse));
                fruitMiddle.setBackground(getResources().getDrawable(R.drawable.middle_while_borde_rounded_ma));
                vegetablesLeft.setTextColor(getResources().getColor(R.color.lanse));
                vegetablesLeft.setBackground(getResources().getDrawable(R.drawable.left_while_borde_rounded_ma));
                //先清除数据，防止网络异常时数据显示错乱
                    /*shopList.clear();
                    if (ShopListShowAdapter != null){
                            shopListShowAdapter.updateListView(shopList);
                    }*/
                callService();
            }
            }
        });
    }

    @Override
    protected void initData() {
        callService();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.title) {

        }
    }

    /**
     * 下拉刷新
     **/
    @Override
    public void onRefresh() {
        pageindex = 1;
        isLastPage = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        myRefreshTime.setRefreshTime(mActivity.getApplicationContext(), df.format(new Date())
                , Contants.REFRESH_TIME , Contants.SHOPLIST_REFRESH_TIME);
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
        listView.setRefreshTime(myRefreshTime.getRefreshTime(mActivity.getApplicationContext()
                , Contants.REFRESH_TIME , Contants.SHOPLIST_REFRESH_TIME));
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

                Intent intent = new Intent();
                intent.setClass(mActivity, GoodsListActivity_shop.class);
                Bundle bundle = new Bundle();
                bundle.putString("shopID",shopListShowAdapter.getItem(position-1).getId());
                bundle.putString("title",shopListShowAdapter.getItem(position-1).getName());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);

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

        shopList = new ArrayList<>();
        ShopInfoDataVO shopInfoVO = new ShopInfoDataVO();
        ShopInfoDataVO.Area arae = shopInfoVO.new Area();
        ShopInfoDataVO.Parent parent1 = shopInfoVO.new Parent();
        ShopInfoDataVO.Parent parent2 = shopInfoVO.new Parent();

        parent2.setId("310100");
        parent2.setParentid("310000");
        parent2.setShortname("上海");

        parent1.setId("310100");
        parent1.setParentid("310000");
        parent1.setShortname("上海");
        parent1.setParent(parent1);

        arae.setId("310115");
        arae.setParentid("310100");
        arae.setShortname("浦东");
        arae.setParent(parent1);

        shopInfoVO.setId("1");
        shopInfoVO.setSn("56632");
        shopInfoVO.setName("老李家鸡排奶茶店");
        shopInfoVO.setPhone("123456789000");
        shopInfoVO.setArea_id("310115");
        shopInfoVO.setAddress("陆家嘴888");
        shopInfoVO.setLogo("https://www.baidu.com/img/bd_logo1.png");
        shopInfoVO.setStart_time("00:00:00");
        shopInfoVO.setEnd_time("23:59:59");
        shopInfoVO.setInstruction("");
        shopInfoVO.setDeliver_limit("0");
        shopInfoVO.setDeliver_fee("0");
        shopInfoVO.setDeliver_free("0");
        shopInfoVO.setActive("0");
        shopInfoVO.setArea(arae);

        shopList.add(shopInfoVO);

        shopListShowAdapter = new ShopListShowAdapter(mActivity,shopList);
        listView.setAdapter(shopListShowAdapter);
        onLoad();

    }



    /*
    * dp转px
    * */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());}

}
