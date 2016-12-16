package com.example.devel.jweather.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Description:省份，地级市，县级市抽取出来的适配器
 * Created by devel on 12/10/2016.
 */

public abstract class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List mList;

    public MyAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
