package com.yijia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijia.adapter.SheJiRecycleyAdapter;
import com.yijia.data.ShejiBean;
import com.yijia.utils.SpacesItemDecoration;
import com.yijia.zhenglei.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DELL on 2018/3/14.
 */

public class Sheji extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Unbinder unbinder;
    private List<ShejiBean> list;
    private List<Integer> imgList1;
    private ShejiBean shejiBean1, shejiBean2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheji, container, false);
        unbinder = ButterKnife.bind(this, view);
        makeInfo();
        getInfo();
        return view;
    }

    private void makeInfo() {
        list = new ArrayList<>();
        imgList1 = new ArrayList<>();
        imgList1.add(R.mipmap.sheji2);
        imgList1.add(R.mipmap.sheji3);
        imgList1.add(R.mipmap.sheji4);
        imgList1.add(R.mipmap.sheji5);
        imgList1.add(R.mipmap.sheji6);
        imgList1.add(R.mipmap.sheji7);
        shejiBean1 = new ShejiBean(R.mipmap.head, "远远", "日式/北欧风", imgList1);
        shejiBean2 = new ShejiBean(R.mipmap.head1, "Stefen", "北欧/美式风", imgList1);
        list.add(shejiBean1);
        list.add(shejiBean2);
    }
    private void getInfo(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler.setLayoutManager(linearLayoutManager);
        recycler.addItemDecoration(new SpacesItemDecoration(120));
        recycler.setAdapter(new SheJiRecycleyAdapter(getActivity(),list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
