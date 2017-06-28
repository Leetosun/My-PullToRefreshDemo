package com.leomo.re.my_pulltorefreshdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LeeToSUn on 2017/6/28 0028
 */

public class Adapter extends BaseAdapter {

    private Context context;
    private List<String> dataList;

    public Adapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView msg;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_list, parent, false);
            msg = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(msg);
        } else {
            msg = (TextView) convertView.getTag();
        }
        msg.setText((String) getItem(position));
        return convertView;
    }
}
