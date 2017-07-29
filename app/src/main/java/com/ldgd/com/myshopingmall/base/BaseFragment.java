package com.ldgd.com.myshopingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ldgd on 2017/7/25.
 */

public abstract class BaseFragment extends Fragment {
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext    = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

   // onActivityCreated 比 onCreateView后执行
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    /**
     * 由子类实现，实现特有效果
     * @return
     */
    public abstract View initView();


    /**
     * 初始化data
     * @return
     */
    public void initData() {

    }

}
