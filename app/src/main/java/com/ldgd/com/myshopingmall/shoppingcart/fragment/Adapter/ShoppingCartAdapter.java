package com.ldgd.com.myshopingmall.shoppingcart.fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.GoodsBean;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.util.CartStorage;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.view.AddSubview;
import com.ldgd.com.myshopingmall.util.Constants;

import java.text.DecimalFormat;
import java.util.List;

import bletext.ldgd.com.myshopingmall.R;

/**
 * Created by ldgd on 2017/8/14.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final Context mContext;
    private final List<GoodsBean> goodsBeens;

    /**
     * 选中结算总价
     */
    private TextView tvShopcartTotal;
    /**
     * 全选反选
     */
    private final CheckBox checkboxAll;


    /**
     * @param mContext        上下文
     * @param goodsBeens      购物车数据
     * @param tvShopcartTotal 购物总价
     * @param checkboxAll     全选反选
     */
    public ShoppingCartAdapter(final Context mContext, final List<GoodsBean> goodsBeens, TextView tvShopcartTotal, final CheckBox checkboxAll) {
        this.mContext = mContext;
        this.goodsBeens = goodsBeens;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;

        //首次加载数据
        showTotalPrice();

        //设置点击事件
        setListener();

        //校验是否全选
        checkAll();

    }

    private void setListener() {
        setOnItemClickListener(new ShoppingCartOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

                //1.根据位置找到对应的Bean对象
                GoodsBean goodsBean = goodsBeens.get(position);
                //2.设置取反状态
                goodsBean.setChildSelected(!goodsBean.isChildSelected());
                //3.更新本地存储
                CartStorage.getInstance().updataData(goodsBean);
                //4.校验是否全选
                checkAll();
                //5.刷新状态
                notifyItemChanged(position);
                //6.计算总价格
                showTotalPrice();

            }
        });

        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 根据状态设置全选和非全选
                checkAll_none(checkboxAll.isChecked());

                // 计算总价格
                showTotalPrice();
            }
        });


    }

    /**
     * 设置全选和非全选
     *
     * @param isCheck
     */
    private void checkAll_none(boolean isCheck) {
        if (goodsBeens != null && goodsBeens.size() > 0) {
            for (int i = 0; i < goodsBeens.size(); i++) {
                GoodsBean goodsBean = goodsBeens.get(i);
                goodsBean.setChildSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }


    private void checkAll() {
        if (goodsBeens != null && goodsBeens.size() > 0) {
            for (int i = 0; i < goodsBeens.size(); i++) {
                if (!goodsBeens.get(i).isChildSelected()) {
                    checkboxAll.setChecked(false);
                    return;
                } else {
                    checkboxAll.setChecked(true);
                }

            }
        }

    }

    private void showTotalPrice() {
        DecimalFormat df = new DecimalFormat("######0.00");
        tvShopcartTotal.setText(df.format(getTotalPrice()) + "");

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsBean goodsBean = goodsBeens.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(goodsBean);

    }


    @Override
    public int getItemCount() {
        return goodsBeens.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbGov;
        private ImageView ivGov;
        private TextView tvDescGov;
        private TextView tvPriceGov;
        private AddSubview addSubview;

        public ViewHolder(final View itemView) {
            super(itemView);

            cbGov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            ivGov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tvDescGov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tvPriceGov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubview = (AddSubview) itemView.findViewById(R.id.AddSubview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shoppingCartOnItemClickListener != null) {
                        shoppingCartOnItemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });


        }

        public void setData(final GoodsBean goodsBean) {

            cbGov.setChecked(goodsBean.isChildSelected());
            Glide.with(mContext).load(Constants.BASE_URl_IMAGE + goodsBean.getFigure()).into(ivGov);
            tvDescGov.setText(goodsBean.getName());
            tvPriceGov.setText(goodsBean.getCover_price() + "");
            addSubview.setValue(goodsBean.getCount());

            addSubview.setMaxValue(10);
            addSubview.setMinValue(1);
            addSubview.setOnNumberChangeListener(new AddSubview.OnNumberChangeListener() {
                @Override
                public void onNumberChange(int value) {
                    goodsBean.setCount(value);
                    // 更新本地数据
                    CartStorage.getInstance().updataData(goodsBean);
                    showTotalPrice();

                }
            });

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
