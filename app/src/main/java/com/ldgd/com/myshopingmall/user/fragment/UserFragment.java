package com.ldgd.com.myshopingmall.user.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ldgd.com.myshopingmall.base.BaseFragment;

/**
 * Created by ldgd on 2017/7/25.
 * 用户
 */

public class UserFragment extends BaseFragment {
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
        textView.setText("用户Fragment");
        super.initData();
    }
}
