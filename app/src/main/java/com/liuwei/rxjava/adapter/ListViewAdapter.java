package com.liuwei.rxjava.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.liuwei.rxjava.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mhl on 2016/6/27.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    private int pos = -1;

    public ListViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    /***/
    // 初始化isSelected的数据
    public void initDate() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    /***/

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        // 监听checkBox并根据原来的状态来设置新的状态
        final int tempPosition = position;
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (isSelected.get(tempPosition)) {
                    isSelected.put(tempPosition, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(tempPosition, true);
                    setIsSelected(isSelected);
                }

            }
        });
        //
        viewHolder.checkBox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }
}
