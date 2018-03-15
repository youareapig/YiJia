package com.yijia.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijia.adapter.PopRecycleAdapter;
import com.yijia.adapter.SheJiRecycleyAdapter;
import com.yijia.adapter.WuBanRecycleyAdapter;
import com.yijia.data.WuBanBean;
import com.yijia.utils.SpacesItemDecoration;
import com.yijia.zhenglei.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by DELL on 2018/3/14.
 */

public class Wuban extends Fragment {
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Unbinder unbinder;
    private List<WuBanBean> list;
    private List<String> list1;
    private WuBanBean bean1, bean2, bean3, bean4;
    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wuban, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        makeInfo();
        return view;
    }

    private void makeInfo() {
        list1 = new ArrayList<>();
        list = new ArrayList<>();
        bean1 = new WuBanBean(R.mipmap.wuban01, "植物壁画", "¥98");
        bean2 = new WuBanBean(R.mipmap.wuban02, "北欧棉麻窗帘", "¥168");
        bean3 = new WuBanBean(R.mipmap.wuban03, "北欧儿织地毯", "¥126");
        bean4 = new WuBanBean(R.mipmap.wuabn04, "亚麻植物抱枕", "¥56");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list1.add("全部");
        list1.add("沙发");
        list1.add("床");
        list1.add("茶几");
        list1.add("餐桌");
        list1.add("地毯");
        list1.add("窗帘");
        list1.add("坐垫");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(new WuBanRecycleyAdapter(getActivity(), list));
    }

    private void showPopwindow() {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (getResources().getDisplayMetrics().heightPixels) / 2;
        View popView = View.inflate(getActivity(), R.layout.popwindow, null);
        TextView poptitle = (TextView) popView.findViewById(R.id.poptitle);
        poptitle.setText("#类别#");
        RecyclerView recyclerView = (RecyclerView) popView.findViewById(R.id.poprecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(100));
        recyclerView.setAdapter(new PopRecycleAdapter(getActivity(), list1));
        popupWindow = new PopupWindow(popView, width, height);
        darkenBackground(0.5f);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.t0));
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.take_photo_anim);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resultEvent(String b) {
        if (b == "0") {
            popupWindow.dismiss();
        }

    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgcolor;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.add)
    public void onViewClicked() {
        showPopwindow();
    }
}
