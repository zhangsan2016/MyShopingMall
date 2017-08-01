package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldgd.com.myshopingmall.home.bean.TypeListBean;

import java.util.List;

/**
 * Created by ldgd on 2017/7/31.
 */

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    private static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;
    /**
     * 活动
     */
    public static final int ACT = 2;
    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    /**
     * 当前类型
     */
    public int currentType = BANNER;
    private final LayoutInflater mLayoutInflater;


    private TypeListBean.ResultBean resultBean;
    private Context mContext;

    public HomeRecycleAdapter(Context mContext, TypeListBean.ResultBean resultBean) {

        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
          //  return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext, resultBean);

        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;

            case SECKILL:
                currentType = SECKILL;
                break;

            case RECOMMEND:
                currentType = RECOMMEND;
                break;

            case HOT:
                currentType = HOT;
                break;
        }

        return currentType;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
      //  public Banner banner;
        public Context mContext;
        public TypeListBean.ResultBean resultBean;

        public BannerViewHolder(View inflate, Context mContext, TypeListBean.ResultBean resultBean) {
            super(inflate);
        }

        public void setData(final List<TypeListBean.ResultBean.BannerInfoBean> banner_info) {

        }
    }
}
