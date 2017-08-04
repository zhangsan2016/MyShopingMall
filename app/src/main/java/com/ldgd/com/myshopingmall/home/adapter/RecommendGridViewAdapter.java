package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldgd.com.myshopingmall.home.bean.TypeListBean;

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
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TypeListBean.ResultBean.RecommendInfoBean bean = recommendData.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_price.setText(bean.getCover_price());

        return convertView;
    }

    private  class ViewHolder {
        private ImageView iv_recommend;
        private TextView tv_name;
        private TextView tv_price;


    }
}
