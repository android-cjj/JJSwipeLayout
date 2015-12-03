package com.cjj.demo;

import android.view.View;
import android.widget.Toast;

import com.cjj.adapter.JJBaseAdapter;
import com.cjj.swipe.JJSwipeLayout;

import java.util.List;

/**
 *  J （J代表cjj,说笑，别打我，J是model的类型）
 *  T （T代表Viewholder）Created by Administrator on 2015/11/30.
 */
public class MyBaseAdapter extends JJBaseAdapter<String,MyViewHolder> {

    private boolean isOpen = false;


    public MyBaseAdapter(List<String> list) {
        super(list, R.layout.item_list, MyViewHolder.class);
    }

    @Override
    public void onBindData(final int pos, final MyViewHolder holder, String item) {
        holder.tv_text.setText(item + "要有最樸素的生活和最遙遠的夢想，即使明天天寒地凍，山高水遠，路遠馬亡。");
        holder.swipelayout.setOnSwipeBackListener(new JJSwipeLayout.SwipeListener() {
            @Override
            public void onOpen() {
                isOpen = true;
                holder.swipelayout.setTag(pos, isOpen);
            }

            @Override
            public void onClose() {
                isOpen = false;
                holder.swipelayout.setTag(pos, isOpen);
            }

            @Override
            public void onSwipe(float per) {

            }

        });
        holder.swipelayout.setTag(pos, isOpen);




        holder.tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipelayout.isOpen()) {
                    holder.swipelayout.close();
                } else {
                    Toast.makeText(v.getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
