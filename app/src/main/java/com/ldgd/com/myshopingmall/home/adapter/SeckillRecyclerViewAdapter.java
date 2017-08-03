package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.TypeListBean;
import com.ldgd.com.myshopingmall.util.Constants;

import bletext.ldgd.com.myshopingmall.R;

/**
 * Created by ldgd on 2017/8/3.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final Context mContext;
    private final TypeListBean.ResultBean.SeckillInfoBean data;

    public SeckillRecyclerViewAdapter(Context mContext, TypeListBean.ResultBean.SeckillInfoBean data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return data.getList().size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFigure;
        private TextView tvCoverPrice;
        private TextView tvOriginPrice;
        private LinearLayout llRoot;

        public MyViewHolder(View inflate) {
            super(inflate);
            ivFigure = (ImageView) inflate.findViewById(R.id.iv_figure);
            tvCoverPrice = (TextView) inflate.findViewById(R.id.tv_cover_price);
            tvOriginPrice = (TextView) inflate.findViewById(R.id.tv_origin_price);
            llRoot = (LinearLayout) inflate.findViewById(R.id.ll_root);
        }

        public void setData(final int position) {
            TypeListBean.ResultBean.SeckillInfoBean.ListBean listBean = data.getList().get(position);

            Glide.with(mContext).load(Constants.BASE_URl_IMAGE + listBean.getFigure()).into(ivFigure);
            tvCoverPrice.setText("￥" + listBean.getCover_price());
            tvOriginPrice.setText("￥" + listBean.getOrigin_price());

            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onClick(position);
                    }
                }
            });

        }
    }

    public interface OnSeckillRecyclerView{
        void onClick(int position);
    }

    public  void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView){
         this.onSeckillRecyclerView = onSeckillRecyclerView;
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;
}
