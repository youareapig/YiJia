package com.yijia.designer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yijia.adapter.KongJianRecycleyAdapter;
import com.yijia.data.KongjianBean;
import com.yijia.zhenglei.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DELL on 2018/3/15.
 */

public class Zuo extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private Unbinder unbinder;
    private List<KongjianBean> list;
    private KongjianBean bean1, bean2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zuo, container, false);
        unbinder = ButterKnife.bind(this, view);
        makeInfo();
        return view;
    }

    private void makeInfo() {
        list = new ArrayList<>();
        bean1 = new KongjianBean("#生活到极致是素与简#", R.mipmap.kj1);
        bean2 = new KongjianBean("#生活到极致是素与简#", R.mipmap.kj2);
        list.add(bean1);
        list.add(bean2);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(new KongJianRecycleyAdapter(getActivity(), list));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
