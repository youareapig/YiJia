package com.yijia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijia.data.KongjianBean;
import com.yijia.zhenglei.KjxqActivity;
import com.yijia.zhenglei.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2018/3/14.
 */

public class KongJianRecycleyAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<KongjianBean> list;

    public KongJianRecycleyAdapter(Activity activity, List<KongjianBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kongjian_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        KongjianBean bean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.kongjianItemTitle.setText(bean.getTitle());
        viewHolder.kongjianItemImage.setImageResource(bean.getImage());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KjxqActivity.class);
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
        private TextView kongjianItemTitle;
        private ImageView kongjianItemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            kongjianItemTitle = (TextView) itemView.findViewById(R.id.item_title);
            kongjianItemImage = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
