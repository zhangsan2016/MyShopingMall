package com.ldgd.com.myshopingmall.shoppingcart.fragment.util;

import android.util.SparseArray;

import com.ldgd.com.myshopingmall.home.bean.GoodsBean;

/**
 * Created by ldgd on 2017/8/9.
 */

public class CartStorage {

    private CartStorage instance;
    private SparseArray<GoodsBean> sparseArray;



    public CartStorage getInstance(){
        if(instance == null){
            instance = new CartStorage();
        }
        return instance;
    }





}
