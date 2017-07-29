package com.ldgd.com.myshopingmall.type.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ldgd.com.myshopingmall.base.BaseFragment;

/**
 * Created by ldgd on 2017/7/25.
 * 分类
 */

public class TypeFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {

        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("分类Fragment");
        super.initData();
    }
}
