package com.baway.monthtest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.monthtest.R;
import com.baway.monthtest.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Bean> list;
    private Context context;

    public MyAdapter(ArrayList<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.tv = convertView.findViewById(R.id.tv);
            holder.img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = list.get(position);
        holder.tv.setText(bean.getName());
        Glide.with(context).load(bean.getImageUrl()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        ImageView img;
    }
}
