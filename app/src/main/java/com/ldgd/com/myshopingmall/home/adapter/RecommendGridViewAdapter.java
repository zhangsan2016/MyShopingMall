package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.TypeListBean;
import com.ldgd.com.myshopingmall.util.Constants;

import java.util.List;

import bletext.ldgd.com.myshopingmall.R;

/**
 * Created by ldgd on 2017/8/4.
 */

public class RecommendGridViewAdapter extends BaseAdapter {

    private final List<TypeListBean.ResultBean.RecommendInfoBean> recommendData;
    private Context mContext;

    public RecommendGridViewAdapter(Context context, List<TypeListBean.ResultBean.RecommendInfoBean> recommendData) {
        this.mContext = context;
        this.recommendData = recommendData;
    }

    @Override
    public int getCount() {
        return recommendData.size();
    }

    @Override
    public Object getItem(int position) {
        return recommendData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TypeListBean.ResultBean.RecommendInfoBean bean = recommendData.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_price.setText(bean.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + bean.getFigure()).into(holder.iv_recommend);

        return convertView;
    }

    private static class ViewHolder {
        private ImageView iv_recommend;
        private TextView tv_name;
        private TextView tv_price;


    }
}
