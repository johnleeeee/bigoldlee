package com.example.lizhiqiang.bigoldlee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lizhiqiang.bigoldlee.R;
import com.lzq.commlibs.baselayout.BaseFragment_libs;

/**
 * Created by lizhiqiang on 2017/3/12.
 */

public class TwoFragment extends BaseFragment_libs{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two,container,false);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
