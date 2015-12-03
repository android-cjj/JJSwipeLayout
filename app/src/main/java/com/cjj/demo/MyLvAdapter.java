package com.cjj.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.swipe.JJSwipeLayout;

import java.util.List;

/**
 * Created by cjj on 2015/12/3.
 */
public class MyLvAdapter extends BaseAdapter {

    private Context mContext;

    private List<String> mStringList;

    private boolean isHaseOpen = false;

    public MyLvAdapter(Context context,List<String> list)
    {
        this.mContext = context;
        this.mStringList = list;
    }



    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public String getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder holder;
        if(convertView == null)
        {
            holder = new Viewholder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,null);
            convertView.setTag(holder);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            holder.swipelayout= (JJSwipeLayout) convertView.findViewById(R.id.swipelayout);
            holder.star2= (ImageView) convertView.findViewById(R.id.star2);

        }else
        {
            holder = (Viewholder) convertView.getTag();
        }
        holder.star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "你点击了star,如果要想关闭菜单，可以调用JJSwipelayout.close()这个方法", Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_text.setText(getItem(position) + "要有最樸素的生活和最遙遠的夢想，即使明天天寒地凍，山高水遠，路遠馬亡。");
        holder.swipelayout.setAlphaAnim(true);
        holder.swipelayout.setOnSwipeBackListener(new JJSwipeLayout.SwipeListener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {

            }

            @Override
            public void onSwipe(float per) {

            }

        });

        holder.tv_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipelayout.isOpen()) {
                    holder.swipelayout.close();
                } else {
                    Toast.makeText(v.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(holder.swipelayout.isOpen())
        {
            holder.swipelayout.close();
        }
        return convertView;
    }

    public static class Viewholder
    {
        private TextView tv_text;
        private ImageView star2;
        private JJSwipeLayout swipelayout;
    }
}
