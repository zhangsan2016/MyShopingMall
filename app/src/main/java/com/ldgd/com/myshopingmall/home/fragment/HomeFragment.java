package com.ldgd.com.myshopingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ldgd.com.myshopingmall.base.BaseFragment;
import com.ldgd.com.myshopingmall.home.adapter.HomeRecycleAdapter;
import com.ldgd.com.myshopingmall.home.bean.TypeListBean;
import com.ldgd.com.myshopingmall.util.Constants;
import com.ldgd.com.myshopingmall.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bletext.ldgd.com.myshopingmall.R;
import okhttp3.Call;

/**
 * Created by ldgd on 2017/7/25.
 * 首页
 */

public class HomeFragment extends BaseFragment {

    //  private ResultBean resultBean;
    private RecyclerView rvHome;
    private ImageView ib_top;
    //   private HomeRecycleAdapter adapter;
    private TextView tv_search_home;
    private TextView tv_message_home;


    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragmet_home, null);

        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        return view;
    }

    @Override
    public void initData() {

        // 网络获取数据
        getDataFromNet();
    }


    public void getDataFromNet() {
     /*   OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());*/

        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }


    private class MyStringCallback extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("联网成功 = " + response.toString());

            switch (id) {
                case 100:
                    if (response != null) {

                        processData(response);


                    }
                    break;
            }

        }
    }


    private void processData(String response) {
        TypeListBean typeListBean = JSON.parseObject(response, TypeListBean.class);
        TypeListBean.ResultBean resultBean = typeListBean.getResult();


        HomeRecycleAdapter homeRecycleAdapter = new HomeRecycleAdapter(mContext, resultBean);
        rvHome.setAdapter(homeRecycleAdapter);
        //设置网格布局
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        rvHome.setLayoutManager(manager);

    }


}
