package com.yijia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijia.data.ShejiBean;
import com.yijia.myview.ImagePagerActivity;
import com.yijia.utils.SpacesItemDecoration1;
import com.yijia.zhenglei.DesignerActivity;
import com.yijia.zhenglei.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/3/14.
 */

public class SheJiRecycleyAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<ShejiBean> list;

    public SheJiRecycleyAdapter(Activity activity, List<ShejiBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shejiitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ShejiBean bean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.shejiName.setText(bean.getName());
        viewHolder.shejiStyle.setText(bean.getStyle());
        viewHolder.shejiHead.setImageResource(bean.getHead());
        GridLayoutManager linearLayoutManager = new GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        viewHolder.shejiList.setLayoutManager(linearLayoutManager);
        viewHolder.shejiList.addItemDecoration(new SpacesItemDecoration1(1));
        viewHolder.shejiList.setAdapter(new SheJiListAdapter(activity, bean.getList()));
        viewHolder.shejiHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DesignerActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shejiName, shejiStyle;
        private ImageView shejiHead;
        private RecyclerView shejiList;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            shejiName = (TextView) itemView.findViewById(R.id.item_name);
            shejiStyle = (TextView) itemView.findViewById(R.id.item_style);
            shejiHead = (ImageView) itemView.findViewById(R.id.item_head);
            shejiList = (RecyclerView) itemView.findViewById(R.id.item_recycle);
        }
    }
}
