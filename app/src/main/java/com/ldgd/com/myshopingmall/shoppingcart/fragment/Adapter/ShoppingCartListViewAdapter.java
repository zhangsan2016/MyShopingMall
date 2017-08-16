package com.ldgd.com.myshopingmall.shoppingcart.fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.GoodsBean;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.view.AddSubview;
import com.ldgd.com.myshopingmall.util.Constants;
import com.ldgd.com.myshopingmall.util.LogUtil;

import java.util.List;

import bletext.ldgd.com.myshopingmall.R;


/**
 * Created by ldgd on 2017/8/16.
 */

public class ShoppingCartListViewAdapter extends BaseAdapter {


    private final Context mContext;
    private final List<GoodsBean> goodsBean;

    public ShoppingCartListViewAdapter(Context context, List<GoodsBean> goodsBeen) {
        this.mContext = context;
        this.goodsBean = goodsBeen;

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(mContext, "onItemClickListener position = " + position, Toast.LENGTH_SHORT).show();
                LogUtil.e("position = " + position);

                GoodsBean aa = goodsBean.get(position);
                aa.setChildSelected(!aa.isChildSelected());

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getCount() {
        return goodsBean.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_shop_cart, null);
            viewHolder = new ViewHolder();

            viewHolder.cbGov = (CheckBox) convertView.findViewById(R.id.cb_gov);
            viewHolder.ivGov = (ImageView) convertView.findViewById(R.id.iv_gov);
            viewHolder.tvDescGov = (TextView) convertView.findViewById(R.id.tv_desc_gov);
            viewHolder.tvPriceGov = (TextView) convertView.findViewById(R.id.tv_price_gov);
            viewHolder.addSubview = (AddSubview) convertView.findViewById(R.id.AddSubview);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onItemClickListener != null){
                        onItemClickListener.onItemClickListener(v,position);
                    }
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GoodsBean gb = goodsBean.get(position);
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + gb.getFigure()).into(viewHolder.ivGov);

        viewHolder.cbGov.setChecked(gb.isChildSelected());
        viewHolder.tvDescGov.setText(gb.getName());
        viewHolder.tvPriceGov.setText(gb.getCover_price() + "");
        viewHolder.addSubview.setValue(gb.getCount());


        return convertView;
    }


    private class ViewHolder {
        private CheckBox cbGov;
        private ImageView ivGov;
        private TextView tvDescGov;
        private TextView tvPriceGov;
        private AddSubview addSubview;
    }


    //回调点击事件的监听
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
