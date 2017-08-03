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
 * Created by ldgd on 2017/8/2.
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<TypeListBean.ResultBean.ChannelInfoBean> channel_info;


    public ChannelAdapter(Context context, List<TypeListBean.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = context;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
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
            convertView = View.inflate(mContext, R.layout.channel_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TypeListBean.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
        viewHolder.textView.setText(channelInfoBean.getChannel_name());

        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + channelInfoBean.getImage())
                .into(viewHolder.imageView);

        return convertView;
    }

    private static class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
