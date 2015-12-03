package com.cjj.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.cjj.adapter.JJBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ListActivity extends AppCompatActivity {

    private ListView lv;

    private MyLvAdapter mMyBaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Swipe for listView");
        lv = (ListView) this.findViewById(R.id.lv);
        final List<String> list = new ArrayList<>();
        for(int i = 1; i<=100; i++)
        {
            list.add("("+i+")");
        }

        mMyBaseAdapter = new MyLvAdapter(this,list);
        lv.setAdapter(mMyBaseAdapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mMyBaseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
}
