package com.ldgd.com.myshopingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.bean.TypeListBean;
import com.ldgd.com.myshopingmall.util.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_viewpager, null), mContext);
        } else if (viewType == ACT) {
            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_viewpager, null), mContext);
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_viewpager, null), mContext);
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item2, null), mContext);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }  else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (HomeRecycleAdapter.RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }
    }


    @Override
    public int getItemCount() {
        return 5;
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

    /**
     * 横幅广告 ViewHolder
     */
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

    /**
     * 引导 ViewHolder
     */
    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        public GridView gridView;
        public Context mContext;


        public ChannelViewHolder(View itemView, Context context) {
            super(itemView);
            this.gridView = (GridView) itemView.findViewById(R.id.gv_channel);
            this.mContext = context;
        }


        public void setData(List<TypeListBean.ResultBean.ChannelInfoBean> channel_info) {

            gridView.setAdapter(new ChannelAdapter(mContext, channel_info));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "当前位置 = " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private ViewPager viewPager;
        private Context mContext;

        public ActViewHolder(View inflate, Context mContext) {
            super(inflate);
            viewPager = (ViewPager) inflate.findViewById(R.id.id_viewpager);
            this.mContext = mContext;

        }

        public void setData(final List<TypeListBean.ResultBean.ActInfoBean> act_info) {

            viewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
            viewPager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            //viewPager.setPageTransformer(true, new RotateDownPageTransformer());
            viewPager.setPageTransformer(true, new ScaleInTransformer());


            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    //绑定数据
                    Glide.with(mContext).load(Constants.BASE_URl_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    container.addView(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    super.destroyItem(container, position, object);
                    container.removeView((View) object);
                }


                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tvTime.setText(sd.format(new Date(dt)));

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);

                if (dt == 0) {
                    handler.removeMessages(0);
                }
            }


        }
    };
    private long dt = 0;
    private TextView tvTime;

    private class SeckillViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvMore;
        private final RecyclerView recyclerView;
        private final Context mContext;

        public SeckillViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tvMore = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(TypeListBean.ResultBean.SeckillInfoBean data) {

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            SeckillRecyclerViewAdapter seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(mContext, data);
            recyclerView.setAdapter(seckillRecyclerViewAdapter);


            //倒计时
            dt = Integer.parseInt(data.getEnd_time()) - Integer.parseInt(data.getStart_time());
            handler.sendEmptyMessageDelayed(0, 1000);

            seckillRecyclerViewAdapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onClick(int position) {

                    Toast.makeText(mContext, "P =  " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_recommend;
        private Context mContext;
        private GridView gvhot;

        public RecommendViewHolder(View inflate, Context mContext) {
            super(inflate);
            this.mContext = mContext;
            this.gvhot = (GridView) inflate.findViewById(R.id.gv_hot);
            this.tv_more_recommend = (TextView) inflate.findViewById(R.id.tv_more_recommend);

        }

        public void setData(List<TypeListBean.ResultBean.RecommendInfoBean> data) {
            RecommendGridViewAdapter recommendGridViewAdapter = new RecommendGridViewAdapter(mContext,data);
            gvhot.setAdapter(recommendGridViewAdapter);

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
