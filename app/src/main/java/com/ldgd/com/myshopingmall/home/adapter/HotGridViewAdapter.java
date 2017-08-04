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

public class HotGridViewAdapter extends BaseAdapter {


    private Context mContext;
    private List<TypeListBean.ResultBean.HotInfoBean> hotInfoBean;

    public HotGridViewAdapter(Context mContext, List<TypeListBean.ResultBean.HotInfoBean> hotInfoBean) {
        this.mContext = mContext;
        this.hotInfoBean = hotInfoBean;
    }

    @Override
    public int getCount() {
        return hotInfoBean.size();
    }

    @Override
    public Object getItem(int position) {
        return hotInfoBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TypeListBean.ResultBean.HotInfoBean bean = hotInfoBean.get(position);
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + bean.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText("Y" + bean.getName());
        viewHolder.tv_price.setText("Y" + bean.getCover_price());

        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;

    }
}
