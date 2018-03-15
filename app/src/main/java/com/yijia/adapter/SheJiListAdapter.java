package com.yijia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijia.data.ShejiBean;
import com.yijia.myview.ImagePagerActivity;
import com.yijia.zhenglei.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/3/14.
 */

public class SheJiListAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Integer> list;

    public SheJiListAdapter(Activity activity, List<Integer> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shejilistitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.img.setImageResource(list.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImagePagerActivity.class);
                // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,  (ArrayList<Integer>)list);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
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
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_item_img);
        }
    }
}
