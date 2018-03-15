package com.yijia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijia.data.ShejiBean;
import com.yijia.data.WuBanBean;
import com.yijia.utils.SpacesItemDecoration1;
import com.yijia.zhenglei.DesignerActivity;
import com.yijia.zhenglei.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2018/3/14.
 */

public class WuBanRecycleyAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<WuBanBean> list;

    public WuBanRecycleyAdapter(Activity activity, List<WuBanBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wubanitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final WuBanBean bean = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.wubanname.setText(bean.getName());
        viewHolder.wubansale.setText(bean.getSale());
        viewHolder.wubanimg.setImageResource(bean.getImage());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wubanname, wubansale;
        private ImageView wubanimg;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            wubanname = (TextView) itemView.findViewById(R.id.item_name);
            wubansale = (TextView) itemView.findViewById(R.id.item_sale);
            wubanimg = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
