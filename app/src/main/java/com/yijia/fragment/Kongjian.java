package com.yijia.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yijia.adapter.KongJianRecycleyAdapter;
import com.yijia.adapter.PopRecycleAdapter;
import com.yijia.data.KongjianBean;
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

public class Kongjian extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.loupan)
    TextView loupan;
    @BindView(R.id.huxing)
    TextView huxing;
    @BindView(R.id.kongjian)
    TextView kongjian;
    @BindView(R.id.fengge)
    TextView fengge;

    private Unbinder unbinder;
    private List<KongjianBean> list;
    private KongjianBean bean1, bean2;
    private List<String> list1;
    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kongjian, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        makeInfo();
        getInfo();
        return view;
    }

    private void makeInfo() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list1.add("田园");
        list1.add("现代");
        list1.add("欧式");
        list1.add("地中海");
        list1.add("混搭");
        bean1 = new KongjianBean("#生活到极致是素与简#", R.mipmap.kj1);
        bean2 = new KongjianBean("#生活到极致是素与简#", R.mipmap.kj2);
        list.add(bean1);
        list.add(bean2);
    }

    private void getInfo() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(new KongJianRecycleyAdapter(getActivity(), list));
    }

    private void showPopwindow(String title) {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (getResources().getDisplayMetrics().heightPixels) / 2;
        View popView = View.inflate(getActivity(), R.layout.popwindow, null);
        TextView poptitle = (TextView) popView.findViewById(R.id.poptitle);
        poptitle.setText(title);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgcolor;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);

    }

    @OnClick({R.id.loupan, R.id.huxing, R.id.kongjian, R.id.fengge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loupan:
                showPopwindow("#楼盘#");
                break;
            case R.id.huxing:
                showPopwindow("#户型#");
                break;
            case R.id.kongjian:
                showPopwindow("#空间#");
                break;
            case R.id.fengge:
                showPopwindow("#风格#");
                break;
        }
    }
}
