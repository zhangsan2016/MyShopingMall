package com.ldgd.com.myshopingmall.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldgd.com.myshopingmall.home.adapter.HomeRecycleAdapter;
import com.ldgd.com.myshopingmall.home.bean.GoodsBean;
import com.ldgd.com.myshopingmall.util.Constants;
import com.ldgd.com.myshopingmall.util.LogUtil;

import bletext.ldgd.com.myshopingmall.R;

import static bletext.ldgd.com.myshopingmall.R.id.tv_good_info_name;

/**
 * Created by ldgd on 2017/8/7.
 * 商品信息列表
 */


public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView textView;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        findViews();

        GoodsBean goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeRecycleAdapter.GOODS_BEAN);

        if (goodsBean != null) {
            LogUtil.e("goodsBean = " + goodsBean.toString());

            setDataFormView(goodsBean);

        } else {
            LogUtil.e("goodsBean = Null");
        }

    }

    private void setDataFormView(GoodsBean goodsBean) {
        String name = goodsBean.getName();
        String cover_price = goodsBean.getCover_price();
        String figure = goodsBean.getFigure();
        String product_id = goodsBean.getProduct_id();

        Glide.with(this).load(Constants.BASE_URl_IMAGE + figure).into(ivGoodInfoImage);
        if (name != null) {
            tvGoodInfoName.setText(name);
        }
        if (cover_price != null) {
            tvGoodInfoPrice.setText("￥" + cover_price);
        }

        setWebView(product_id);

    }


    private void findViews() {
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        textView = (TextView) findViewById(R.id.textView);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        tvMoreShare = (TextView) findViewById(R.id.tv_more_share);
        tvMoreSearch = (TextView) findViewById(R.id.tv_more_search);
        tvMoreHome = (TextView) findViewById(R.id.tv_more_home);
        btnMore = (Button) findViewById(R.id.btn_more);

        btnMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-08 14:52:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnGoodInfoAddcart) {
        } else if (v == btnMore) {
        }
    }

    private void setWebView(String product_id) {

        if (product_id != null) {
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //设置支持JavaScript
            WebSettings webSettings = wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//支持双击页面变大变小
            webSettings.setJavaScriptEnabled(true);//设置支持JavaScript
            //优先使用缓存
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        view.loadUrl(request.getUrl().toString());
//                    }
//                    return true;
//                }
            });
        }
    }


}
