package com.ldgd.com.myshopingmall.app;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ldgd.com.myshopingmall.base.BaseFragment;
import com.ldgd.com.myshopingmall.community.fragment.CommunityFragment;
import com.ldgd.com.myshopingmall.home.fragment.HomeFragment;
import com.ldgd.com.myshopingmall.shoppingcart.fragment.fragment.ShoppingCartFragment;
import com.ldgd.com.myshopingmall.type.fragment.TypeFragment;
import com.ldgd.com.myshopingmall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import bletext.ldgd.com.myshopingmall.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ldgd on 2017/7/25.
 */

public class MainActivity extends FragmentActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    private int position = 0;
    private List<BaseFragment> fragments;

    private BaseFragment mContext; // 记录当前显示的Fragment

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
        initListener();

    }

    private void initFragment() {
        fragments = new ArrayList<BaseFragment>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());

    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {

        if (fragments.size() > 0 && fragments != null) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {

        if (mContext != nextFragment) {

            mContext = nextFragment;
            if (nextFragment != null) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {

                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();

                } else {

                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
