package com.yijia.fragment;

import android.graphics.Color;
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

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.yijia.adapter.KongJianRecycleyAdapter;
import com.yijia.adapter.PopRecycleAdapter;
import com.yijia.data.KongjianBean;
import com.yijia.utils.SpacesItemDecoration;
import com.yijia.utils.ToastUtils;
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
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

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
    private List<String> list1,list2;
    private PopupWindow popupWindow,popupWindow1;
    private String address;
    private TextView cityname,homename;
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
        list2=new ArrayList<>();
        list1.add("田园");
        list1.add("现代");
        list1.add("欧式");
        list1.add("地中海");
        list1.add("混搭");
        list2.add("万科理想城");
        list2.add("环球中心");
        list2.add("欧城");
        list2.add("城南1号");
        list2.add("南城都汇");
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
                showChooseCity();
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
    private void showChooseCity() {
        final View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        final int width = getResources().getDisplayMetrics().widthPixels;
        final int height = (getResources().getDisplayMetrics().heightPixels) / 2;
        View popView = View.inflate(getActivity(), R.layout.citychoose, null);
        popupWindow1 = new PopupWindow(popView, width, height-100);
        cityname= (TextView) popView.findViewById(R.id.cityname);
        homename= (TextView) popView.findViewById(R.id.homename);
        popView.findViewById(R.id.choseCity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method();
            }
        });
        popView.findViewById(R.id.chooseHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method1();
            }
        });
        popView.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        popView.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
                ToastUtils.showShort(getActivity(),"提交成功");
            }
        });
        darkenBackground(0.5f);
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.t0));
        popupWindow1.setBackgroundDrawable(dw);
        popupWindow1.setAnimationStyle(R.style.take_photo_anim);
        popupWindow1.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }
    private void method(){
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(14)
                .title("区域选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#7aa195")
                .cancelTextColor("#515151")
                .province("四川省")
                .city("成都市")
                .district("武侯区")
                .textColor(Color.parseColor("#7aa195"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                address=province+"-"+city+"-"+district;
                cityname.setText(address);
            }
        });
    }
    private void method1(){
        SinglePicker<String> picker = new SinglePicker<>(getActivity(), list2);
        picker.setCanLoop(true);//不禁用循环
        picker.setTopBackgroundColor(Color.parseColor("#ffffff"));
        picker.setTopHeight(50);
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setHeight(600);
        picker.setTopLineColor(Color.parseColor("#eeeeee"));
        picker.setTopLineHeight(1);
        picker.setTitleTextColor(Color.BLACK);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(Color.parseColor("#000000"));
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(Color.parseColor("#7aa195"));
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(Color.parseColor("#7aa195"));
        picker.setUnSelectedTextColor(Color.parseColor("#888888"));
        LineConfig config = new LineConfig();
        config.setColor(Color.parseColor("#7aa195"));//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(100);
        picker.setBackgroundColor(Color.parseColor("#ffffff"));
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                homename.setText(item);
            }
        });
        picker.show();
    }
}
