package com.ldgd.com.myshopingmall.shoppingcart.fragment.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ldgd.com.myshopingmall.app.MyAppliction;
import com.ldgd.com.myshopingmall.home.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldgd on 2017/8/9.
 */

public class CartStorage {
    public static final String JSON_CART = "json_cart";

    private CartStorage instance;
    private SparseArray<GoodsBean> sparseArray;
    private Context mContext;

    public CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray<>(100);
        listToSparse();

    }

    private void listToSparse() {
        List<GoodsBean> carts = getAllData();

        //放到sparseArry中
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                GoodsBean goodsBean = carts.get(i);
                sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
            }
        }
    }

    public CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyAppliction.getmContext());
        }
        return instance;
    }


    public List<GoodsBean> getAllData() {
        return getDataFromLocal();
    }

    //本地获取json数据，并且通过Gson解析成list列表数据
    public List<GoodsBean> getDataFromLocal() {
        List<GoodsBean> carts = new ArrayList<>();
        //从本地获取缓存数据
        String savaJson = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(savaJson)) {
            //把数据转换成列表
            carts = new Gson().fromJson(savaJson, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }

        return carts;
    }
}
