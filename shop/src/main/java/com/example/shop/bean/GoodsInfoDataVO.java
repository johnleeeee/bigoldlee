package com.example.shop.bean;

import java.util.List;

/**
 *
 * 商品的信息
 *
 * Created by lizhiqiang on 2017/5/16.
 */

public class GoodsInfoDataVO {
    private String id;
    private String shop_id;
    private String cat_id;
    private String sn;
    private String name;
    private String brief;
    private String labels;
    private String price;
    private String market_price;
    private String unit;
    private String image;
    private List<String> album;
    private String detail;
    private String status;
    private Sku sku;

    public class Sku{
        private String price;
        private String store;
        private String sales;
        private String supplier_id;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        @Override
        public String toString() {
            return "Sku{" +
                    "price='" + price + '\'' +
                    ", store='" + store + '\'' +
                    ", sales='" + sales + '\'' +
                    ", supplier_id='" + supplier_id + '\'' +
                    '}';
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getDetail() {
        return detail;
    }

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "GoodsInfoDataVO{" +
                "id='" + id + '\'' +
                ", shop_id='" + shop_id + '\'' +
                ", cat_id='" + cat_id + '\'' +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", brief='" + brief + '\'' +
                ", labels='" + labels + '\'' +
                ", price='" + price + '\'' +
                ", market_price='" + market_price + '\'' +
                ", unit='" + unit + '\'' +
                ", image='" + image + '\'' +
                ", album=" + album +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", sku=" + sku +
                '}';
    }
}
