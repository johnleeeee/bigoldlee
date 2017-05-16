package com.example.shop.bean;

import java.util.List;

/**
 *
 * 商品列表的所有信息
 *
 * Created by lizhiqiang on 2017/5/16.
 */

public class GoodsListVO {
    private String err;
    private Data data;

    public class Data{
        private String total;
        private String per_page;
        private String current_page;
        private List<GoodsInfoDataVO> data;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public List<GoodsInfoDataVO> getData() {
            return data;
        }

        public void setData(List<GoodsInfoDataVO> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "GoodsInfoDataVO{" +
                    "total='" + total + '\'' +
                    ", per_page='" + per_page + '\'' +
                    ", current_page='" + current_page + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsListVO{" +
                "err='" + err + '\'' +
                ", data=" + data +
                '}';
    }
}
