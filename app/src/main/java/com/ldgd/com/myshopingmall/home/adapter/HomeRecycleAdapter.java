package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.TypeListBean;
import com.ldgd.com.myshopingmall.util.Constants;
import com.ldgd.com.myshopingmall.util.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bletext.ldgd.com.myshopingmall.R;

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
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext, resultBean);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }

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
        public Banner banner;
        public Context mContext;
        public TypeListBean.ResultBean resultBean;

        public BannerViewHolder(View itemView, Context context, TypeListBean.ResultBean resultBean) {
            super(itemView);

            banner = (Banner) itemView.findViewById(R.id.banner);
            this.mContext = context;
            this.resultBean = resultBean;
        }

        public void setData(final List<TypeListBean.ResultBean.BannerInfoBean> banner_info) {

            // 设置圆点指示器
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 动画效果
            banner.setBannerAnimation(Transformer.Accordion);

            //如果你想用自己项目的图片加载,那么----->自定义图片加载框架
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                imageUris.add(Constants.BASE_URl_IMAGE + banner_info.get(i).getImage());
                LogUtil.e("                imageUris.add(banner_info.get(i).getImage());  == " + banner_info.get(i).getImage());
            }


            banner.setImages(imageUris)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new MyOnBannerListener())
                    .start();


        }

        private class MyOnBannerListener implements OnBannerListener {

            @Override
            public void OnBannerClick(int position) {

                Toast.makeText(mContext, "你点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext)
                    .load(path)
                    .crossFade()
                    .into(imageView);


        }
    }


}
