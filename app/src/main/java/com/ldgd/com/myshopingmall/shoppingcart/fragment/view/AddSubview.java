package com.ldgd.com.myshopingmall.shoppingcart.fragment.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bletext.ldgd.com.myshopingmall.R;

/**
 * Created by ldgd on 2017/8/9.
 */

public class AddSubview extends LinearLayout implements View.OnClickListener {
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView count;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;


    public AddSubview(Context context) {
        this(context, null);
    }

    public AddSubview(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public AddSubview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //把布局和当前类形成整体
        View.inflate(context, R.layout.number_add_sub_layout, this);
        iv_sub = (ImageView) this.findViewById(R.id.iv_sub);
        iv_add = (ImageView) this.findViewById(R.id.iv_add);
        count = (TextView) this.findViewById(R.id.tv_count);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);


    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sub:
                break;
            case R.id.iv_add:
                break;
        }
    }
}