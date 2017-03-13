package com.lzq.miniprogram.view.draggrid;

/**
 * Created by lizhiqiang on 2017/3/12.
 */

import android.content.Context;

public class DataTools {
    /**
     * dip转px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     *  px转dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
