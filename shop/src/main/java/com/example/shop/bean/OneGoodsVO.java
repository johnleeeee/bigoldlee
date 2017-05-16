package com.example.shop.bean;

/**
 *
 * 单件商品的所有信息
 *
 * Created by lizhiqiang on 2017/5/14.
 */

public class OneGoodsVO {
    private String err;
    private GoodsInfoDataVO data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public GoodsInfoDataVO getData() {
        return data;
    }

    public void setData(GoodsInfoDataVO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OneGoodsVO{" +
                "err='" + err + '\'' +
                ", data=" + data +
                '}';
    }


}
