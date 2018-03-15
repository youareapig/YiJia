package com.yijia.zhenglei;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.yijia.adapter.TabAdapter;
import com.yijia.designer.Jie;
import com.yijia.designer.Ping;
import com.yijia.designer.Zuo;
import com.zhy.autolayout.AutoLayoutActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DesignerActivity extends AutoLayoutActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xtab)
    XTabLayout xtab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private Unbinder unbinder;
    private List<String> stringList;
    private List<Fragment> fragmentList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer);
        unbinder = ButterKnife.bind(this);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.t0));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        makeInfo();
    }

    private void makeInfo() {
        stringList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        stringList.add("简介");
        stringList.add("作品");
        stringList.add("评价");
        fragmentList.add(new Jie());
        fragmentList.add(new Zuo());
        fragmentList.add(new Ping());
        viewpager.setAdapter(new TabAdapter(getSupportFragmentManager(), stringList, fragmentList));
        viewpager.setOffscreenPageLimit(3);
        xtab.setupWithViewPager(viewpager);
        xtab.getTabAt(0).select();
        xtab.getTabAt(1).select();
        viewpager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
