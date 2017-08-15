package com.ldgd.com.myshopingmall.shoppingcart.fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

import static bletext.ldgd.com.myshopingmall.R.id.tv_desc_gov;

/**
 * Created by ldgd on 2017/8/14.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {


    private final Context mContext;
    private final List<GoodsBean> goodsBeens;
    private CheckBox cbGov;
    private ImageView ivGov;
    private TextView tvDescGov;
    private AddSubview AddSubview;
    private TextView tvPriceGov;

    private TextView tvShopcartTotal;


    /**
     * @param mContext        上下文
     * @param goodsBeens      购物车数据
     * @param tvShopcartTotal 购物总价
     */
    public ShoppingCartAdapter(final Context mContext, final List<GoodsBean> goodsBeens, TextView tvShopcartTotal) {
        this.mContext = mContext;
        this.goodsBeens = goodsBeens;
        this.tvShopcartTotal = tvShopcartTotal;


        //首次加载数据
        showTotalPrice();

        setOnItemClickListener(new ShoppingCartOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

                LogUtil.e("      goodsBeens.size() = " + goodsBeens.toString());
                Toast.makeText(mContext, "onItemClickListener position = " + position, Toast.LENGTH_SHORT).show();
                GoodsBean goodsBean = goodsBeens.get(position);
                goodsBean.setChildSelected(!goodsBean.isChildSelected());
                notifyItemChanged(position);
                showTotalPrice();

               // notifyItemRangeChanged(position,goodsBeens.size());
            }
        });
    }


    private void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "");

    }

    private double getTotalPrice() {
        double total = 0;
        if (goodsBeens != null && goodsBeens.size() > 0) {
            for (int i = 0; i < goodsBeens.size(); i++) {
                GoodsBean goodsBean = goodsBeens.get(i);
                if (goodsBean.isChildSelected())
                    total += Double.parseDouble(goodsBean.getCover_price()) * Double.parseDouble(goodsBean.getCount() + "");
            }
        }
        return total;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsBean goodsBean = goodsBeens.get(position);
        holder.setData(goodsBean);

    }

    @Override
    public int getItemCount() {
        return goodsBeens.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(final View itemView) {
            super(itemView);

            cbGov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            ivGov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tvDescGov = (TextView) itemView.findViewById(tv_desc_gov);
            tvPriceGov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            AddSubview = (AddSubview) itemView.findViewById(R.id.AddSubview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingCartOnItemClickListener != null) {
                        shoppingCartOnItemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });

        }

        public void setData(GoodsBean goodsBean) {
            cbGov.setChecked(goodsBean.isChildSelected());
            Glide.with(mContext).load(Constants.BASE_URl_IMAGE + goodsBean.getFigure()).into(ivGov);
            tvDescGov.setText(goodsBean.getName());
            tvPriceGov.setText(goodsBean.getCover_price() + "");
            AddSubview.setValue(goodsBean.getCount());

        }
    }

    private ShoppingCartOnItemClickListener shoppingCartOnItemClickListener;

    public void setOnItemClickListener(ShoppingCartOnItemClickListener onItemClickListener) {
        this.shoppingCartOnItemClickListener = onItemClickListener;
    }

    public interface ShoppingCartOnItemClickListener {
        void onItemClickListener(View view, int position);
    }


}
