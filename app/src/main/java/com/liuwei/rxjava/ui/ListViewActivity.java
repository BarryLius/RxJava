package com.liuwei.rxjava.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.liuwei.rxjava.BaseActivity;
import com.liuwei.rxjava.R;
import com.liuwei.rxjava.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {
    private Context mContext;
    private ListView listView;
    ListViewAdapter adapter;
    private Button allSel;
    private Button allNoSel;
    private Button del;
    //
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_list_view);
        //
        ActionBar ab = getSupportActionBar();
        ab.setElevation(20);
        ab.setTitle(" RxList");
        //
        initView();
        initDate();
        initListener();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getIsSelected().get(position)) {
                    adapter.getIsSelected().put(position, false);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.getIsSelected().put(position, true);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        registerForContextMenu(listView);
    }

    private void initListener() {
        allSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    adapter.getIsSelected().put(i, true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        allNoSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    adapter.getIsSelected().put(i, false);
                }
                adapter.notifyDataSetChanged();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList tempList = new ArrayList();
                //
                int selNum = 0;
                for (int i = 0; i < adapter.getIsSelected().size(); i++) {
                    if (adapter.getIsSelected().get(i)) {
                        tempList.add(list.get(i));
                        selNum++;
                    }
                }
                //
                if (selNum > 0) {
                    list.removeAll(tempList);
                    adapter = new ListViewAdapter(mContext, list);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(ListViewActivity.this, "没有选中项", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bighand_menu, menu);
    }

    private void initView() {
        mContext = ListViewActivity.this;
        listView = (ListView) findViewById(R.id.listView);
        allSel = (Button) findViewById(R.id.allSel);
        allNoSel = (Button) findViewById(R.id.allNoSel);
        del = (Button) findViewById(R.id.del);
    }

    private void initDate() {
        list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        adapter = new ListViewAdapter(mContext, list);
        listView.setAdapter(adapter);
    }
}
