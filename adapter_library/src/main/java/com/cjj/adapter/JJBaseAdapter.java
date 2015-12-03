package com.cjj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by cjj on 2015/11/30.
 *  J （J代表cjj,说笑，别打我，J是model的类型）
 *  T （T代表Viewholder）
 */
public abstract class JJBaseAdapter<J,T> extends BaseAdapter implements ViewHandler,DataHandler<J>{

    private int mLayout;

    private Class<T> mHolderClass;

    private List<J> mList;

    private JJBaseAdapter(){}

    public JJBaseAdapter(List<J> list, int layout, Class<T> holderClass) {
        this.mLayout = layout;
        this.mList = list;
        this.mHolderClass = holderClass;
    }

    public JJBaseAdapter(J[] mArray, int layout, Class<T> holderClass) {
        this.mLayout = layout;
        this.mList = CastUtil.arrayToList(mArray);
        this.mHolderClass = holderClass;
    }


    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public J getItem(int position) {
        return mList==null?null:mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = onBindView(convertView,parent);
        onBindData(position, (T) v.getTag(),getItem(position));
        return v;
    }

    @Override
    public View onBindView(View v, ViewGroup p) {
        if(v == null)
        {
            v = InflateViewHolder.inflateConvertView(p.getContext(), v, mLayout, mHolderClass);
        }
        return  v;
    }

    @Override
    public void updateData(List<J> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public void updateData(J[] array) {
        this.mList = CastUtil.arrayToList(array);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeData(J item) {
        if(mList == null)return;
        this.mList.remove(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeData(int pos) {
        if(mList == null)return;
        mList.remove(pos);
        this.notifyDataSetChanged();
    }

    @Override
    public void addData(J[] arrays) {
        if(mList == null)return;
        int length = arrays.length;
        for(int i=0;i<length;i++){
            this.mList.add(arrays[i]);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public void addData(J item) {
        if(mList == null)return;
        this.mList.add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void addData(List<J> list) {
        if(mList == null)return;
        this.mList.addAll(mList);
        this.notifyDataSetChanged();
    }

    public abstract void onBindData(int pos, T holder, J item);
}
