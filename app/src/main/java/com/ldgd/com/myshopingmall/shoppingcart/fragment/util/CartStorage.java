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

    private static CartStorage instance;
    private SparseArray<GoodsBean> sparseArray;
    private Context mContext;

    private CartStorage(Context context) {
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

    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyAppliction.getmContext());
        }
        return instance;
    }


    public List<GoodsBean> getAllData() {
        return getDataFromLocal();
    }

    /**
     * 本地获取json数据，并且通过Gson解析成list列表数据
     *
     * @return
     */
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


    // 增删改方法
    public void addData(GoodsBean cart) {
        GoodsBean tempCart = sparseArray.get(Integer.parseInt(cart.getProduct_id()));
        if (tempCart != null) {
            tempCart.setCount(tempCart.getCount() + 1);
        } else {
            tempCart = cart;
            tempCart.setCount(1);
        }

        sparseArray.put(Integer.parseInt(tempCart.getProduct_id()), tempCart);

        // 数据从内存保存到本地
        commit();
    }

    public void deleteData(GoodsBean cart) {
        //删除数据
        sparseArray.delete(Integer.parseInt(cart.getProduct_id()));

        // 数据从内存保存到本地
        commit();
    }

    public void updataData(GoodsBean cart) {
        //修改数据
        sparseArray.put(Integer.parseInt(cart.getProduct_id()), cart);
        //保存数据
        commit();
    }


    //保存数据
    private void commit() {

        // 把sparseArray转换成list
        List<GoodsBean> carts = parsesToList();
        // 把list转换成json
        String json = new Gson().toJson(carts);
        // 保存到本地
        CacheUtils.putString(mContext, JSON_CART, json);
    }

    /**
     * sparseArray数据转换成list
     */
    private List<GoodsBean> parsesToList() {
        List<GoodsBean> carts = new ArrayList<>();
        if (sparseArray != null && sparseArray.size() > 0) {
            for (int i = 0; i < sparseArray.size(); i++) {
                carts.add(sparseArray.valueAt(i));
            }
        }
        return carts;
    }


}
