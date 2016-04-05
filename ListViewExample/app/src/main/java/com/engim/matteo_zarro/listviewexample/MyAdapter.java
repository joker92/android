package com.engim.matteo_zarro.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matteo_zarro on 17/03/16.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;

    public MyAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
    }

    public void addItem(String name){
        list.add(name);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText((String)getItem(position));
        return convertView;
    }
}
