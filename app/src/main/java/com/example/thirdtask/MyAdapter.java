package com.example.thirdtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> history_list;

    public MyAdapter(Context context, ArrayList<String> history_list) {
        this.context=context;
        this.history_list=history_list;
    }

    @Override
    public int getCount() {
        return history_list.size();
    }

    @Override
    public Object getItem(int position) {
        return history_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);
        }

        TextView textView=convertView.findViewById(R.id.textview);
        textView.setText(history_list.get(position));

        return convertView;
    }
}
