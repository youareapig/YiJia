package com.yijia.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijia.data.KongjianBean;
import com.yijia.utils.ToastUtils;
import com.yijia.zhenglei.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by DELL on 2018/3/14.
 */

public class PopRecycleAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private List<String> list;
    public PopRecycleAdapter(Activity activity, List<String> list){
        this.activity=activity;
        this.list=list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(list.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("0");
                ToastUtils.showShort(v.getContext(),"您选择了"+list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            name = (TextView) itemView.findViewById(R.id.popitem_name);
        }
    }
}
