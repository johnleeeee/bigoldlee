package com.lzq.miniprogram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzq.commlibs.baselayout.BaseFragment_libs;
import com.lzq.miniprogram.R;
import com.lzq.miniprogram.view.draggrid.ChannelItem;
import com.lzq.miniprogram.view.draggrid.DragAdapter;
import com.lzq.miniprogram.view.draggrid.DragGrid;

import java.util.ArrayList;

/**
 * Created by lizhiqiang on 2017/3/10.
 */

public class MiniProgramFragment_mini extends BaseFragment_libs{

    private DragGrid dragGrid;

    private int[] imgs = { R.mipmap.app_transfer, R.mipmap.app_fund,
            R.mipmap.app_phonecharge, R.mipmap.app_creditcard,
            R.mipmap.app_movie, R.mipmap.app_lottery,
            R.mipmap.app_facepay, R.mipmap.app_close, R.mipmap.app_plane };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mini,container,false);
        dragGrid = (DragGrid) view.findViewById(R.id.userGridView);
        ArrayList<ChannelItem> dragLists = new ArrayList<ChannelItem>();
        for (int i = 0; i < 9; i++) {
            ChannelItem item = new ChannelItem(i + 1, "item" + (i + 1),imgs[i]);

            if (i == 8)
                item.setName("其他");
            dragLists.add(item);
        }
        dragGrid.setAdapter(new DragAdapter(mActivity, dragLists));
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        DragAdapter.selectedPos = -1;
    }

}
