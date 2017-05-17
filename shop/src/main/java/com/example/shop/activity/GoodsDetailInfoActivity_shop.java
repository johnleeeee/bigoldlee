package com.example.shop.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shop.R;
import com.example.shop.bean.OneGoodsVO;
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

public class GoodsDetailInfoActivity_shop extends BaseActivity_libs {

    private static  final  String TAG="GoodsDetailInfoActivity_shop";

    private SliderLayout mSliderLayout;
    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();

    private TextView goodsName;
    private EditText goodsPrice;
    private EditText goodsMarketPrice;
    private TextView goodsUnit;
    private TextView goodsStatus;
    private EditText goodsDetail;
    private EditText goodsSkuPrice;
    private EditText goodsStore;
    private EditText goodsSales;
    private Button goodsSave;
    private Button goodsUpdate;
    private List<String> albumList = new ArrayList<>();
    private String goodsID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        initView();
        initData();
        initListener();

    }
    @Override
    protected void initView() {

        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        goodsName = (TextView) findViewById(R.id.goods_name);
        goodsPrice = (EditText) findViewById(R.id.goods_price);
        goodsMarketPrice = (EditText) findViewById(R.id.goods_market_price);
        goodsUnit = (TextView) findViewById(R.id.goods_unit);
        goodsStatus = (TextView) findViewById(R.id.goods_status);
        goodsDetail = (EditText) findViewById(R.id.goods_detail);
        goodsSkuPrice = (EditText) findViewById(R.id.goods_sku_price);
        goodsStore = (EditText) findViewById(R.id.goods_store);
        goodsSales = (EditText) findViewById(R.id.goods_sales);
        goodsSave = (Button) findViewById(R.id.goods_save);
        goodsUpdate = (Button) findViewById(R.id.goods_update);
    }

    @Override
    protected void initListener() {
        goodsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsPrice.setEnabled(true);
                goodsMarketPrice.setEnabled(true);
                goodsDetail.setEnabled(true);
//                goodsSkuPrice.setEnabled(true);
//                goodsStore.setEnabled(true);
//                goodsSales.setEnabled(true);


            }
        });
        goodsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsPrice.setEnabled(false);
                goodsMarketPrice.setEnabled(false);
                goodsDetail.setEnabled(false);
//                goodsSkuPrice.setEnabled(false);
//                goodsStore.setEnabled(false);
//                goodsSales.setEnabled(false);
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("id",goodsID);
                params.put("price",goodsPrice.getText());
                params.put("market_price",goodsMarketPrice.getText());
                String goodupdateurl = "http://106.14.165.193:18081/admin/goods/update";
                //params.put("price",goodsSkuPrice.getText());
                httpHelper.post(goodupdateurl, params, new SpotsCallBack<OneGoodsVO>(GoodsDetailInfoActivity_shop.this) {
                    @Override
                    public void onSuccess(Response response, OneGoodsVO o) {
                        if (o.getErr().equals("0")){
                            new  AlertDialog.Builder(GoodsDetailInfoActivity_shop.this)
                                    .setTitle("提示" )
                                    .setMessage("修改成功" )
                                    .setPositiveButton("确定" ,  null)
                                    .show();
                        }else{
                            new  AlertDialog.Builder(GoodsDetailInfoActivity_shop.this)
                                    .setTitle("提示" )
                                    .setMessage("修改失败")
                                    .setPositiveButton("确定" ,  null)
                                    .show();
                        }

                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }
                });



            }
        });
    }

    @Override
    protected void initData() {
        goodsID = getIntent().getExtras().getString("goodsID");
        callService();
    }

    @Override
    public void onClick(View v) {

    }

    private void callService(){
        Log.e("shop", "callService");
        Map<String,Object> params = new HashMap<>(2);
        params.put("id",goodsID);
        String goodinfourl = "http://106.14.165.193:18081/admin/goods/info";
        httpHelper.get(goodinfourl, params, new SpotsCallBack<OneGoodsVO>(this) {

            @Override
            public void onSuccess(Response response, OneGoodsVO goodsDetailInfo) {
                initInfo(goodsDetailInfo);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                Log.e("shop",response + "");
            }
        });
    }


    private  void initInfo(OneGoodsVO goodsDetailInfo){
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
