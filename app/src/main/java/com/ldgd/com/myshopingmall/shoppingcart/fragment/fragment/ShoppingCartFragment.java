package com.ldgd.com.myshopingmall.shoppingcart.fragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldgd.com.myshopingmall.base.BaseFragment;
import com.ldgd.com.myshopingmall.home.bean.GoodsBean;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.Adapter.ShoppingCartAdapter;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.util.CartStorage;

import java.util.List;

import bletext.ldgd.com.myshopingmall.R;

/**
 * Created by ldgd on 2017/7/25.
 * 购物车
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private Button btnDelete;
    private Button btnCollection;


    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        findViews(view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        // 从本地读取数据
        List<GoodsBean>  goodsBeens = CartStorage.getInstance().getDataFromLocal();
        // 如果有显示数据，如果没有显示为空界面
        if(goodsBeens != null && goodsBeens.size() > 0){
            ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(mContext,goodsBeens);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerview.setAdapter(shoppingCartAdapter);

        }else{
            // 显示为空页面

        }


    }

    /**
     * ShoppingCartAdapter
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-08-14 10:25:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-14 10:25:55 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        }
    }
}