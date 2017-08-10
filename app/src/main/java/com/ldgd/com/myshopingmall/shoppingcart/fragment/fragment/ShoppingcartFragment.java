package com.ldgd.com.myshopingmall.shoppingcart.fragment.fragment;

import android.view.View;
import android.widget.TextView;

import com.ldgd.com.myshopingmall.base.BaseFragment;

import bletext.ldgd.com.myshopingmall.R;

import static bletext.ldgd.com.myshopingmall.R.id.textView;

/**
 * Created by ldgd on 2017/7/25.
 * 购物车
 */

public class ShoppingcartFragment extends BaseFragment {

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
