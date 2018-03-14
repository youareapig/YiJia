package com.yijia.zhenglei;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijia.fragment.Kongjian;
import com.yijia.fragment.Sheji;
import com.yijia.fragment.Wode;
import com.yijia.fragment.Wuban;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AutoLayoutActivity {


    @BindView(R.id.main_Fragment)
    LinearLayout mainFragment;
    @BindView(R.id.kongjianimg)
    ImageView kongjianimg;
    @BindView(R.id.kongjiantext)
    TextView kongjiantext;
    @BindView(R.id.kongjian)
    RelativeLayout kongjian;
    @BindView(R.id.shejiimg)
    ImageView shejiimg;
    @BindView(R.id.shejitext)
    TextView shejitext;
    @BindView(R.id.sheji)
    RelativeLayout sheji;
    @BindView(R.id.wubanimg)
    ImageView wubanimg;
    @BindView(R.id.wubantext)
    TextView wubantext;
    @BindView(R.id.wuban)
    RelativeLayout wuban;
    @BindView(R.id.wodeimg)
    ImageView wodeimg;
    @BindView(R.id.wodetext)
    TextView wodetext;
    @BindView(R.id.wode)
    RelativeLayout wode;
    private Unbinder unbinder;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment fragment = new Fragment();
    private List<Fragment> list = new ArrayList<>();
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        //TODO 改变状态栏颜色
        Window window = getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.t0));
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
        //TODO 状态栏文字亮色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            list.removeAll(list);
            list.add(fragmentManager.findFragmentByTag(0 + ""));
            list.add(fragmentManager.findFragmentByTag(1 + ""));
            list.add(fragmentManager.findFragmentByTag(2 + ""));
            list.add(fragmentManager.findFragmentByTag(3 + ""));
            restoreFragment();
        } else {
            list.add(new Kongjian());
            list.add(new Sheji());
            list.add(new Wuban());
            list.add(new Wode());

            showFragment();
        }


    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!list.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.main_Fragment, list.get(currentIndex), "" + currentIndex);
        } else {
            transaction
                    .hide(fragment)
                    .show(list.get(currentIndex));
        }
        fragment = list.get(currentIndex);
        transaction.commit();
    }

    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < list.size(); i++) {

            if (i == currentIndex) {
                mBeginTreansaction.show(list.get(i));
            } else {
                mBeginTreansaction.hide(list.get(i));
            }
        }
        mBeginTreansaction.commit();
        fragment = list.get(currentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.kongjian, R.id.sheji, R.id.wuban, R.id.wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kongjian:
                currentIndex = 0;
                showFragment();
                kongjiantext.setTextColor(getResources().getColor(R.color.t1));
                shejitext.setTextColor(getResources().getColor(R.color.t2));
                wubantext.setTextColor(getResources().getColor(R.color.t2));
                wodetext.setTextColor(getResources().getColor(R.color.t2));

                kongjianimg.setImageResource(R.mipmap.kongjian);
                shejiimg.setImageResource(R.mipmap.sheji1);
                wubanimg.setImageResource(R.mipmap.wuban1);
                break;
            case R.id.sheji:
                currentIndex = 1;
                showFragment();
                kongjiantext.setTextColor(getResources().getColor(R.color.t2));
                shejitext.setTextColor(getResources().getColor(R.color.t1));
                wubantext.setTextColor(getResources().getColor(R.color.t2));
                wodetext.setTextColor(getResources().getColor(R.color.t2));

                kongjianimg.setImageResource(R.mipmap.kongjian1);
                shejiimg.setImageResource(R.mipmap.sheji);
                wubanimg.setImageResource(R.mipmap.wuban1);
                break;
            case R.id.wuban:
                currentIndex = 2;
                showFragment();
                kongjiantext.setTextColor(getResources().getColor(R.color.t2));
                shejitext.setTextColor(getResources().getColor(R.color.t2));
                wubantext.setTextColor(getResources().getColor(R.color.t1));
                wodetext.setTextColor(getResources().getColor(R.color.t2));

                kongjianimg.setImageResource(R.mipmap.kongjian1);
                shejiimg.setImageResource(R.mipmap.sheji1);
                wubanimg.setImageResource(R.mipmap.wuban);
                break;
            case R.id.wode:
                currentIndex = 3;
                showFragment();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
