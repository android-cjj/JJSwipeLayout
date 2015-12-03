package com.cjj.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cjj.adapter.JJBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ListActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Swipe for listView");
        lv = (ListView) this.findViewById(R.id.lv);
        List<String> list = new ArrayList<>();
        for(int i = 1; i<=100; i++)
        {
            list.add("("+i+")");
        }

        lv.setAdapter(new MyBaseAdapter(list));
    }
}
