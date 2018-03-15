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
import com.yijia.data.ShejiBean;
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

public class Sheji extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.add)
    ImageView add;
    private Unbinder unbinder;
    private List<ShejiBean> list;
    private List<Integer> imgList1;
    private ShejiBean shejiBean1, shejiBean2;
    private PopupWindow popupWindow;
    private List<String> list1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheji, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        makeInfo();
        getInfo();
        return view;
    }

    private void makeInfo() {
        list1 = new ArrayList<>();
        list = new ArrayList<>();
        imgList1 = new ArrayList<>();
        imgList1.add(R.mipmap.sheji2);
        imgList1.add(R.mipmap.sheji3);
        imgList1.add(R.mipmap.sheji4);
        imgList1.add(R.mipmap.sheji5);
        imgList1.add(R.mipmap.sheji6);
        imgList1.add(R.mipmap.sheji7);
        list1.add("全部");
        list1.add("田园");
        list1.add("中式");
        list1.add("地中海");
        list1.add("欧式");
        list1.add("东南亚");
        shejiBean1 = new ShejiBean(R.mipmap.head, "远远", "日式/北欧风", imgList1);
        shejiBean2 = new ShejiBean(R.mipmap.head1, "Stefen", "北欧/美式风", imgList1);
        list.add(shejiBean1);
        list.add(shejiBean2);
    }

    private void getInfo() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler.setLayoutManager(linearLayoutManager);
        recycler.addItemDecoration(new SpacesItemDecoration(120));
        recycler.setAdapter(new SheJiRecycleyAdapter(getActivity(), list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void showPopwindow() {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (getResources().getDisplayMetrics().heightPixels) / 2;
        View popView = View.inflate(getActivity(), R.layout.popwindow, null);
        TextView poptitle = (TextView) popView.findViewById(R.id.poptitle);
        poptitle.setText("#风格#");
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

    @OnClick(R.id.add)
    public void onViewClicked() {
        showPopwindow();
    }
}
