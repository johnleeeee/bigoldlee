package com.lzq.commlibs.commconst;


public class Contants {

    public static final String USER_JSON="user_json";
    public static final String TOKEN="token";

    public  static final String DES_KEY="Cniao5_123456";

    public  static final String REFRESH_TIME = "refresh_time";

    public  static final String GOODSLIST_REFRESH_TIME = "goodslist_refresh_time";

    public  static final String SHOPLIST_REFRESH_TIME = "shoplist_refresh_time";



    public static class API{


        public static final String BASE_URL = "http://106.14.165.193:18081/admin/";

        public static final String LOGIN=BASE_URL +"auth/login";

        public static final String GOODSLIST_URL = BASE_URL + "goods/list";

        public static final String CREATGOODS_URL = BASE_URL + "goods/create";


    }
}
