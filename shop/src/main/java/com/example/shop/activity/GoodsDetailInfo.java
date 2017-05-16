package com.example.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shop.R;
import com.example.shop.bean.DataVO;
import com.lzq.commlibs.baselayout.BaseActivity_libs;
import com.lzq.commlibs.http.OkHttpHelper;
import com.lzq.commlibs.http.SpotsCallBack;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhiqiang on 2017/5/15.
 */

public class GoodsDetailInfo extends BaseActivity_libs {

    private static  final  String TAG="GoodsDetailInfo";

    private SliderLayout mSliderLayout;
    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();

    private TextView goodsName;
    private TextView goodsPrice;
    private TextView goodsMarketPrice;
    private TextView goodsUnit;
    private TextView goodsStatus;
    private TextView goodsDetail;
    private TextView goodsSkuPrice;
    private TextView goodsStore;
    private TextView goodsSales;
    private List<String> albumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        initView();
        initListener();
        initData();

    }
    @Override
    protected void initView() {

        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        goodsName = (TextView) findViewById(R.id.goods_name);
        goodsPrice = (TextView) findViewById(R.id.goods_price);
        goodsMarketPrice = (TextView) findViewById(R.id.goods_market_price);
        goodsUnit = (TextView) findViewById(R.id.goods_unit);
        goodsStatus = (TextView) findViewById(R.id.goods_status);
        goodsDetail = (TextView) findViewById(R.id.goods_detail);
        goodsSkuPrice = (TextView) findViewById(R.id.goods_sku_price);
        goodsStore = (TextView) findViewById(R.id.goods_store);
        goodsSales = (TextView) findViewById(R.id.goods_sales);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        callService();
    }

    @Override
    public void onClick(View v) {

    }

    private void callService(){
        Log.e("shop", "callService");
        Map<String,Object> params = new HashMap<>(2);
        params.put("id","1");
        String goodinfourl = "http://106.14.165.193:18081/admin/goods/info";
        httpHelper.get(goodinfourl, params, new SpotsCallBack<DataVO>(this) {

            @Override
            public void onSuccess(Response response, DataVO goodsDetailInfo) {
                initInfo(goodsDetailInfo);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                Log.e("shop",response + "");
            }
        });
    }


    private  void initInfo(DataVO goodsDetailInfo){
        goodsName.setText(goodsDetailInfo.getData().getName());
        goodsPrice.setText(goodsDetailInfo.getData().getPrice());
        goodsMarketPrice.setText(goodsDetailInfo.getData().getMarket_price());
        goodsUnit.setText(goodsDetailInfo.getData().getUnit());
        if (Integer.parseInt(goodsDetailInfo.getData().getStatus()) == 0) {
            goodsStatus.setText("下架");
        }else{
            goodsStatus.setText("上架");
        }
        goodsDetail.setText(goodsDetailInfo.getData().getDetail());
        goodsSkuPrice.setText(goodsDetailInfo.getData().getSku().getPrice());
        goodsStore.setText(goodsDetailInfo.getData().getSku().getStore());
        goodsSales.setText(goodsDetailInfo.getData().getSku().getStore());
        albumList.clear();
        albumList.addAll(goodsDetailInfo.getData().getAlbum());
        initSlider();
    }




    private void initSlider(){

        if(albumList !=null){

            for (String album : albumList){
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView.image("album");
                textSliderView.image("https://www.baidu.com/img/bd_logo1.png");
                //textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);

            }
        }

        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mSliderLayout.stopAutoCycle();
    }
}
